package com.topiefor.dao;

import com.topiefor.models.Order;
import java.util.List;

public interface OrderDao {

    boolean addOrder(Order order);
    boolean cancelOrder(Order order);
    List<Order> getAllOrders();
    boolean changeOrderStatus(Order order);
    List<Order> getOrderDetailsByOrderID(int orderID);
}