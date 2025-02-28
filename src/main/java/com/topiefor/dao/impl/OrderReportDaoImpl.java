package com.topiefor.dao.impl;

import com.topiefor.dao.CategoryDao;
import com.topiefor.models.Category;

import com.topiefor.models.OrderReport;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.topiefor.dao.OrderReportDao;
import com.topiefor.models.Address;
import com.topiefor.models.User;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderReportDaoImpl implements OrderReportDao {

    private static OrderReportDaoImpl reportDaoImpl;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public OrderReportDaoImpl(Connection con) {
        this.con = con;

    }

    //--------------------------------------------------------
    public static OrderReportDaoImpl getInstance(Connection dbCon) {
        if (reportDaoImpl == null) {
            reportDaoImpl = new OrderReportDaoImpl(dbCon);
        }
        return reportDaoImpl;
    }

    @Override
    public List<OrderReport> getAllOrders() {
        List<OrderReport> allOrders = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT order_table.OrderID,  order_table.TotalAmount, order_table.UserID,user.Surname,user.UserName,address.AddressID, address.Street,address.Suburb,address.Code ,order_table.`Status`\n"
                    + "FROM order_table\n"
                    + "INNER JOIN address ON order_table.AddressID = address.AddressID\n"
                    + "INNER JOIN user ON order_table.UserID = user.UserID");
            rs = ps.executeQuery();
            while (rs.next()) {

                allOrders.add(new OrderReport(rs.getInt("OrderID"), rs.getDouble("TotalAmount"), rs.getString("Status"), new User(rs.getInt("UserID"), rs.getString("UserName"), rs.getString("Surname")), new Address(rs.getInt("AddressID"), rs.getString("Street"), rs.getString("Suburb"), rs.getString("Code"))));

            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return allOrders;
    }

    @Override
    public List<OrderReport> ordersPlaced(LocalDate startDate, LocalDate endDate,
            LocalDate singleDate
    ) {

        CategoryDao cd = CategoryDaoImpl.getInstance(con);
        List<Category> catList = cd.getAllCategories();
        List<OrderReport> reportList = new ArrayList<>();
        int orderID = 0;
        if ((startDate != null && endDate != null) || singleDate != null) {
            for (Category category : catList) {
                if (category.getStatus()) {
                    int totProdOrdered = 0;
                    double totAmntPerCat = 0;
                    try {
                        ps = con.prepareStatement("SELECT category.Name,order_table.OrderID,  order_table.TotalAmount, order_line.Quantity, category.Flag\n"
                                + "FROM order_table\n"
                                + "INNER JOIN order_line ON order_table.OrderID = order_line.OrderID\n"
                                + "INNER JOIN product ON order_line.ProductID = product.ProductID\n"
                                + "INNER JOIN category ON product.CategoryID = category.CategoryID\n"
                                + "WHERE DATE_FORMAT(order_table.Date,'%Y-%m-%d')  = ?\n"
                                + "OR   order_table.Date BETWEEN ? AND ?\n"
                                + "ORDER BY category.Name");
                        ps.setDate(1, java.sql.Date.valueOf(singleDate));
                        ps.setDate(2, java.sql.Date.valueOf(startDate));
                        ps.setDate(3, java.sql.Date.valueOf(endDate));
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            if (category.getName().equals(rs.getString("Name"))) {
                                totAmntPerCat += rs.getDouble("TotalAmount");
                                totProdOrdered += rs.getDouble("Quantity");
                            }
                        }
                        reportList.add(new OrderReport(startDate, endDate, singleDate, new Category(category.getName()), totAmntPerCat, totProdOrdered));
                    } catch (SQLException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                }
            }
        }
        return reportList;
    }

    @Override
    public List<OrderReport> ordersOutstanding(LocalDate startDate, LocalDate endDate, LocalDate singleDate, String catName) {

        CategoryDao cd = CategoryDaoImpl.getInstance(con);
        List<Category> catList = cd.getAllCategories();
        List<OrderReport> reportList = new ArrayList<>();
        if ((startDate != null && endDate != null) || singleDate != null) {
            int totProdOrdered = 0;
            double totAmntPerCat = 0;
            try {
                ps = con.prepareStatement("SELECT category.Name,order_table.OrderID, order_table.Status,order_table.OrderID, order_table.TotalAmount, order_line.Quantity, category.Flag, user.TelephoneNumber\n"
                        + "FROM order_table\n"
                        + "INNER JOIN order_line ON order_table.OrderID = order_line.OrderID\n"
                        + "INNER JOIN product ON order_line.ProductID = product.ProductID\n"
                        + "INNER JOIN category ON product.CategoryID = category.CategoryID\n"
                        + "INNER JOIN user ON order_table.UserID = user.UserID\n"
                        + "WHERE  category.Name = ?\n"
                        + "AND order_table.`Status` <> \"Delivered\" \n"
                        + "AND order_table.`Status` <> \"Cancelled\"\n"
                        + "AND DATE_FORMAT(order_table.Date,'%Y-%m-%d')  = ?\n"
                        + "OR   order_table.Date BETWEEN ? AND ?\n"
                        + "ORDER BY category.Name");
                ps.setString(1, catName);
                ps.setDate(2, java.sql.Date.valueOf(singleDate));
                ps.setDate(3, java.sql.Date.valueOf(startDate));
                ps.setDate(4, java.sql.Date.valueOf(endDate));
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getString("Name").equals(catName)) {
                        reportList.add(new OrderReport(startDate, endDate, singleDate, new Category(catName), rs.getDouble("TotalAmount"), rs.getInt("Quantity"), rs.getInt("OrderID"), rs.getString("Status"), new User(rs.getString("TelephoneNumber"))));
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        return reportList;
    }

    @Override
    public List<OrderReport> ordersDelivered(LocalDate startDate, LocalDate endDate
    ) {

        CategoryDao cd = CategoryDaoImpl.getInstance(con);
        List<Category> catList = cd.getAllCategories();
        List<OrderReport> reportList = new ArrayList<>();
        if ((startDate != null && endDate != null)) {
            double totAmntPerCat = 0;
            try {
                ps = con.prepareStatement("SELECT order_table.OrderID,  order_table.TotalAmount, order_table.`Status`\n"
                        + "FROM order_table\n"
                        + "WHERE  order_table.`Status` = ? \n"
                        + "AND order_table.Date BETWEEN ? AND ?");
                ps.setString(1, "Delivered");
                ps.setDate(2, java.sql.Date.valueOf(startDate));
                ps.setDate(3, java.sql.Date.valueOf(endDate));
                rs = ps.executeQuery();
                while (rs.next()) {
                    reportList.add(new OrderReport(startDate, endDate, rs.getDouble("TotalAmount"), rs.getInt("OrderID"), rs.getString("Status")));
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } else {
        
        }
        return reportList;
    }

    
}