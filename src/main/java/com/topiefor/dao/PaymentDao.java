package com.topiefor.dao;


import com.topiefor.models.Order;
import com.topiefor.models.Payment;
import java.util.List;

public interface PaymentDao {
    boolean addPayment(Payment payment,int orderID,double totAmount);
   List<Payment> getAllPayments();
}