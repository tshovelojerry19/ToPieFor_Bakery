package com.topiefor.dao.impl;

import com.topiefor.dao.ProductDao;
import com.topiefor.models.Category;
import com.topiefor.models.Product;
import com.topiefor.models.Recipe;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static ProductDaoImpl productDaoImpl;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private ProductDaoImpl(Connection con) {
        this.con = con;
        fetchAllProductsFromDB();
    }
    //--------------------------------------------------------

    public static ProductDaoImpl getInstance(Connection dbCon) {
        if (productDaoImpl == null) {
            productDaoImpl = new ProductDaoImpl(dbCon);
        }
        return productDaoImpl;
    }

    private List<Product> fetchAllProductsFromDB() {
        List<Product> productList = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT product.ProductId, product.Name, product.Price, product.Allergies, product.LastDateUpdated, product.LastDateUpdated, product.Flag, product.Image, product.RecipeID, product.CategoryID,category.Name As CatName, recipe.RecipeName\n"
                        + "FROM product\n"
                        + "INNER JOIN category ON product.CategoryID = category.CategoryID\n"
                        + "INNER JOIN recipe ON product.RecipeID = recipe.RecipeID");
                rs = ps.executeQuery();
                while (rs.next()) {
                    productList.add(new Product(rs.getInt("ProductId"), rs.getString("Name"), rs.getDouble("Price"), rs.getString("Allergies"), new Recipe(rs.getInt("RecipeID"), rs.getString("recipeName")), new Category(rs.getInt("CategoryID"),rs.getString("CatName")), rs.getBoolean("Flag"), rs.getString("Image")));
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
        return productList;
    }

    private boolean checkProductAvailabilityFromDB(Product product) {

        return fetchAllProductsFromDB().stream().anyMatch(pro -> (pro.equals(product)));
    }

    @Override
    public boolean addProduct(Product product) {
        if (checkProductAvailabilityFromDB(product)) {
            return false;
        }
        boolean retVal = false;
        if (con != null) {
            try {

                ps = con.prepareStatement("INSERT INTO product(ProductId, Name,Price, Allergies, CategoryID, RecipeID, LastDateUpdated, Flag, Image ) values(?,?,?,?,?,?,?,?,?)");
                ps.setInt(1, 0);
                ps.setString(2, product.getName());
                ps.setDouble(3, product.getPrice());
                ps.setString(4, product.getAllergies());
                ps.setInt(5, product.getCategory().getCategoryID());
                ps.setInt(6, product.getRecipe().getRecipeID());
                Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
                ps.setTimestamp(7, currentTimestamp);
                ps.setBoolean(8, product.isStatus());
                ps.setString(9, product.getImage());
                if (ps.executeUpdate() > 0) {
                    System.out.println("here");
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
    public boolean editProduct(Product product) {
        if (checkProductAvailabilityFromDB(product)) {
            boolean retVal = false;
            if (con != null) {
                try {

                    ps = con.prepareStatement("UPDATE product SET Name = ?, Price = ?, Allergies = ?, LastDateUpdated = ?, Image = ? WHERE ProductID = ? ");
                    ps.setString(1, product.getName());
                    ps.setDouble(2, product.getPrice());
                    ps.setString(3, product.getAllergies());
                    Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
                    ps.setTimestamp(4, currentTimestamp);
                    ps.setString(5, product.getImage());
                    ps.setInt(6, product.getProductID());
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
        return false;
    }

    @Override
    public boolean productAvailability(Product product) {
        if (checkProductAvailabilityFromDB(product)) {
            boolean retVal = false;
            if (con != null) {
                try {
                    ps = con.prepareStatement("UPDATE product SET Flag = ?,LastDateUpdated =?  WHERE ProductID = ?");
                    ps.setBoolean(1, product.isStatus());
                    Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
                    ps.setTimestamp(2, currentTimestamp);
                    ps.setInt(3, product.getProductID());
                    if (ps.executeUpdate() > 0) {
                        retVal = true;
                    }
                } catch (SQLException ex) {
                    System.out.println("Error!!!!: " + ex.getMessage());
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
        return false;
    }

    @Override
    public List<Product> getAllProducts() {
        return fetchAllProductsFromDB();
    }

    
}
