/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.topiefor.service;

import com.topiefor.models.Payment;

/**
 *
 * @author Train
 */
public interface PaymentService {
   boolean makePayment(String paymentMethod, double amount);
       boolean addPayment(Payment payment,int orderID, double totAmount);
}
