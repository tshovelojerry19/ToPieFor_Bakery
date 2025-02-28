package com.topiefor.dao.impl;

import com.topiefor.dao.PaymentDao;
import com.topiefor.models.Order;
import com.topiefor.models.Payment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PaymentDaoImpl implements PaymentDao {

    private static PaymentDao paymentDao;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private PaymentDaoImpl(Connection con) {
        this.con = con;
        fetchAllPaymentsFromDB();
    }

    //--------------------------------------------------------
    public static PaymentDao getInstance(Connection dbCon) {
        if (paymentDao == null) {
            paymentDao = new PaymentDaoImpl(dbCon);
        }
        return paymentDao;
    }

    private List<Payment> fetchAllPaymentsFromDB() {
        List<Payment> paymentList = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT PaymentID, PaymentMethod, Status, OrderID FROM payment");
            rs = ps.executeQuery();

            while (rs.next()) {
                paymentList.add(new Payment(rs.getInt("PaymentID"), rs.getString("PaymentMethod"), rs.getBoolean("Status"), new Order(rs.getInt("OrderID"))));

            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return paymentList;
    }

    @Override
    public boolean addPayment(Payment payment, int orderID, double totAmount) {

        boolean retVal = false;
        try {
            ps = con.prepareStatement("INSERT INTO payment(PaymentMethod,OrderID) VALUES (?,?)");
            ps.setString(1, payment.getPaymentMethod());
            ps.setInt(2, orderID);
            if (ps.executeUpdate() > 0) {

                ps = con.prepareStatement("UPDATE payment SET Status = ? WHERE OrderID = ?");
                ps.setBoolean(1, true);
                ps.setInt(2, orderID);
                retVal = ps.executeUpdate() > 0;

            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Could not close: " + ex.getMessage());
                }
            }

        }
        return retVal;
    }

    @Override
    public List<Payment> getAllPayments() {
        return fetchAllPaymentsFromDB();
    }

}
