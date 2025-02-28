package com.topiefor.dao.impl;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import com.topiefor.dao.OrderDao;
import com.topiefor.dao.ProductDao;
import com.topiefor.models.Address;
import com.topiefor.models.Order;
import com.topiefor.models.Payment;
import com.topiefor.models.Product;
import com.topiefor.models.Recipe;
import com.topiefor.models.User;
import com.topiefor.service.PaymentService;
import com.topiefor.service.impl.PaymentServiceImpl;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.math3.util.Precision;

public class OrderDaoImpl implements OrderDao {

    private static OrderDao orderDaoImpl;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private OrderDaoImpl(Connection con) {
        this.con = con;
        fetchAllOrdersFromDB();
    }

    //--------------------------------------------------------
    public static OrderDao getInstance(Connection dbCon) {
        if (orderDaoImpl == null) {
            orderDaoImpl = new OrderDaoImpl(dbCon);
        }
        return orderDaoImpl;
    }

    private List<Order> fetchAllOrdersFromDB() {
        List<Order> orderList = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT order_table.OrderID, order_table.Date,  order_table.DateToBeDelivered, order_table.`Status`,TotalAmount, order_table.UserID, user.UserName,user.Title,user.Email, user.Surname,user.TelephoneNumber, order_table.AddressID, \n"
                        + "address.Street, address.Suburb, address.Code\n"
                        + "FROM user\n"
                        + "INNER JOIN order_table ON order_table.UserID = user.UserID\n"
                        + "INNER JOIN address ON order_table.AddressID = address.AddressID");
                rs = ps.executeQuery();
                while (rs.next()) {

                    orderList.add(new Order(rs.getInt("OrderID"),
                            rs.getDate("Date"), rs.getDate("DateToBeDelivered"),
                            rs.getString("Status"),
                            new Address(rs.getInt("AddressID"), rs.getString("Street"), rs.getString("Suburb"), rs.getString("Code")),
                            new User(rs.getInt("UserID"), rs.getString("UserName"), rs.getString("Surname"), rs.getString("title"), rs.getString("email"), rs.getString("telephoneNumber")), rs.getDouble("TotalAmount")
                    ));
                }
            } catch (SQLException ex) {
                System.out.println("Error!!: " + ex.getMessage());

            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }
        return orderList;
    }

    private boolean checkOrderAvailabilityFromDB(Order order) {

        return fetchAllOrdersFromDB().stream().anyMatch(ord -> (ord.equals(order)));
    }

    @Override
    public boolean changeOrderStatus(Order order) {
        boolean retVal = false;
        if (con != null) {
            try {

                ps = con.prepareStatement("UPDATE order_table SET Status = ? WHERE OrderID = ?");
                ps.setString(1, order.getStatus());
                ps.setInt(2, order.getOrderID());

                if (ps.executeUpdate() > 0) {

                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println("Error!!: " + ex.getMessage());

            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }
        return retVal;
    }

    @Override
    public boolean addOrder(Order order) {
        if (checkOrderAvailabilityFromDB(order)) {
            return false;
        }
        boolean retVal = false;
        double totAmount = 0.0;

        if (con != null) {
            try {
                con.setAutoCommit(false);
                ps = con.prepareStatement("INSERT INTO order_table(OrderID, Date, DateToBeDelivered, UserID,AddressID ) values(?,?,?,?,?)");
                ps.setInt(1, 0);
                Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
                ps.setTimestamp(2, currentTimestamp);
                LocalDate localdate = LocalDate.now().plusDays(2);
                ps.setDate(3, Date.valueOf(localdate));
                ps.setInt(4, order.getUser().getUserID());
                ps.setInt(5, order.getAddress().getAddressID());

                if (ps.executeUpdate() > 0) {
                    PreparedStatement ps1 = con.prepareStatement("SELECT DISTINCT(LAST_INSERT_ID()) AS orderID FROM order_table");
                    rs = ps1.executeQuery();
                    int orderID = 0;

                    while (rs.next()) {
                        orderID = rs.getInt("orderID");
                    }

                    ProductDao prod = ProductDaoImpl.getInstance(con);
                    List<Product> productList = prod.getAllProducts();
                    List<Product> newList = order.getProduct();
                    for (Product list : newList) {
                        for (Product product : productList) {
                            if (list.getProductID() == product.getProductID()) {
                                ps = con.prepareStatement("INSERT INTO order_line(Quantity, OrderID, ProductID) VALUES (?,?,?)");
                                ps.setInt(1, list.getQuantity());
                                ps.setInt(2, orderID);
                                ps.setInt(3, product.getProductID());
                                totAmount = (list.getQuantity() * product.getPrice()) + totAmount;
                                if (ps.executeUpdate() > 0) {
                                    ps = con.prepareStatement("UPDATE order_table SET TotalAmount = ? WHERE OrderID = ?");
                                    ps.setDouble(1, Precision.round(totAmount, 2));
                                    ps.setInt(2, orderID);
                                    if (ps.executeUpdate() > 0) {

                                    }
                                }
                            }
                        }

                        ingredientAmount(order);

                        PaymentService pd = new PaymentServiceImpl(PaymentDaoImpl.getInstance(con));

                        if (pd.addPayment(order.getPayment(), orderID, totAmount)) {
                            con.commit();
                            retVal = true;
                        } else {

                            con.rollback();
                        }

                    }
                }
            } catch (SQLException ex) {
                System.out.println("Error!!: " + ex.getMessage());

            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                        con.setAutoCommit(true);
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }
        return retVal;
    }

    @Override
    public boolean cancelOrder(Order order) {

        if (checkOrderAvailabilityFromDB(order)) {
            return false;
        }
        boolean retVal = false;

        if (con != null) {

            try {
            
                ps = con.prepareStatement("UPDATE order_table SET Status = ?  WHERE OrderID = ?");
                ps.setString(1, order.getStatus());
                ps.setInt(2, order.getOrderID());
                if (ps.executeUpdate() > 0) {
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println("Error!!: " + ex.getMessage());

            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }
        return retVal;
    }

    @Override
    public List<Order> getAllOrders() {
        return fetchAllOrdersFromDB();
    }

    //subtraction on ingredient
    private void ingredientAmount(Order order) {
        int ingredientAmountNeeded = 0;
        int ingredientAmountAvailable = 0;
        int count = 0;

        List<Product> orderProdList = order.getProduct();
        for (Product product : orderProdList) {
            System.out.println("========================");
            count = count + 1;
            try {
                ps = con.prepareStatement("SELECT product.Name AS \"Product Name\",ingredient.Name AS \"Ingredient Name\",recipe_ingredient.IngredientID, recipe_ingredient.Quantity AS \"IngredientAmountNeeded\", ingredient.Quantity AS \"IngredientAmountAvailable\", units.UnitType\n"
                        + "FROM product\n"
                        + "INNER JOIN recipe ON product.RecipeID =recipe.RecipeID\n"
                        + "INNER JOIN recipe_ingredient ON recipe.RecipeID = recipe_ingredient.RecipeID\n"
                        + "INNER JOIN ingredient ON recipe_ingredient.IngredientID = ingredient.IngredientID\n"
                        + "INNER JOIN units ON ingredient.UnitID = units.UnitID");

                rs = ps.executeQuery();
                System.out.println("/n*********************");
                System.out.println("Number of product, needed: " + product.getQuantity());
                while (rs.next()) {

                    ingredientAmountNeeded = rs.getInt("IngredientAmountNeeded");
                    ingredientAmountAvailable = rs.getInt("IngredientAmountAvailable");
                    System.out.println("/n*********************");
                    System.out.println("ingredientAmountNeeded :" + ingredientAmountNeeded);
                    System.out.println("ingredientAmountAvailable :" + ingredientAmountAvailable);

                    int leftIngredientAmnt = ingredientAmountAvailable - (ingredientAmountNeeded * product.getQuantity());
                    System.out.println("leftIngredientAmnt : " + leftIngredientAmnt);
                    ps = con.prepareStatement("UPDATE ingredient SET Quantity = ? WHERE IngredientID = ?");
                    ps.setInt(1, leftIngredientAmnt);
                    ps.setInt(2, rs.getInt("IngredientID"));
                    if (ps.executeUpdate() > 0) {
                        System.out.println("Updated");

                    }
                }

            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            
        }

    }

    @Override
    public List<Order> getOrderDetailsByOrderID(int orderID) {
        List<Order> orderList = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT product.Name, (product.Price*order_line.Quantity) AS Price,order_line.Quantity, product.Image, recipe.RecipeName, recipe.Instruction\n"
                        + "FROM order_line\n"
                        + "INNER JOIN product ON order_line.ProductID = product.ProductID\n"
                        + "INNER JOIN recipe ON product.RecipeID = recipe.RecipeID\n"
                        + "AND order_line.OrderID = ?");
                ps.setInt(1, orderID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    orderList.add(new Order(new Product(rs.getString("Name"), rs.getDouble("Price"), rs.getInt("Quantity"), rs.getString("Image"), new Recipe(rs.getString("Instruction"), rs.getString("RecipeName")))));

                }
            } catch (SQLException ex) {
                System.out.println("Error!!: " + ex.getMessage());

            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }
        return orderList;
    }

   
}
