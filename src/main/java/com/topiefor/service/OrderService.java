package com.topiefor.service;

import com.topiefor.models.Order;
import java.util.List;

public interface OrderService {

    boolean addOrder(Order order);
    boolean cancelOrder(Order order);
    boolean changeOrderStatus(Order order);
    List<Order> getAllOrders();
    List<Order> getOrdersByUserID(int userId);
     List<Order> getOrderDetailsByOrderID(int orderID);

}