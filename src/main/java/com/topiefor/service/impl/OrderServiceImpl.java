package com.topiefor.service.impl;

import com.topiefor.dao.OrderDao;
import com.topiefor.dao.impl.OrderDaoImpl;
import com.topiefor.models.Order;
import com.topiefor.models.Product;
import com.topiefor.service.OrderService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        setOrderDaoImpl(orderDao);
    }

    @Override
    public boolean addOrder(Order order) {
        return order == null ? false : orderDao.addOrder(order);
    }

    @Override
    public boolean changeOrderStatus(Order order) {
        return order == null ? false : orderDao.changeOrderStatus(order);
    }

    @Override
    public boolean cancelOrder(Order order) {
        return order == null ? false : orderDao.cancelOrder(order);
    }

    @Override
    public List<Order> getAllOrders() {
        if (orderDao.getAllOrders() != null || !(orderDao.getAllOrders().isEmpty())) {
            return orderDao.getAllOrders();
        }
        return null;
    }

    @Override
    public List<Order> getOrdersByUserID(int userId) {
        List<Order> o = new ArrayList<>();
        getAllOrders().stream().filter(order -> (userId == order.getUser().getUserID())).forEachOrdered(order -> {
            o.add(order);
        });
        return o;
    }

   
    public Order getAOrderByOrderID(int orderID) {
       for(Order o : getAllOrders()){
           if(o.getOrderID() == orderID ){
               return o;
           }
       }
        return null;
    }

    @Override
    public List<Order> getOrderDetailsByOrderID(int orderID) {
        if (orderDao.getOrderDetailsByOrderID(orderID) != null || !(orderDao.getOrderDetailsByOrderID(orderID).isEmpty())) {
            return orderDao.getOrderDetailsByOrderID(orderID);
        }
        return null;
    }

    public void setOrderDaoImpl(OrderDao orderDaoImpl) {
        this.orderDao = orderDaoImpl;
    }

    
}
