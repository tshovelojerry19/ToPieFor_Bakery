package com.topiefor.service.impl;

import com.topiefor.dao.OrderReportDao;
import com.topiefor.dao.impl.OrderReportDaoImpl;
import com.topiefor.models.OrderReport;
import com.topiefor.service.OrderReportService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class OrderReportServiceImpl implements OrderReportService {

    private OrderReportDao orderReportDao;
    private String page;

    public OrderReportServiceImpl(OrderReportDao orderReportDao) {
        setOrderReportDao(orderReportDao);
    }

    public void setOrderReportDao(OrderReportDao orderReportDao) {
        this.orderReportDao = orderReportDao;
    }

    @Override
    public List<OrderReport> ordersPlaced(LocalDate startDate, LocalDate endDate, LocalDate singleDate) {
        return orderReportDao.ordersPlaced(startDate, endDate, singleDate) != null || !(orderReportDao.ordersPlaced(startDate, endDate, singleDate).isEmpty()) ? orderReportDao.ordersPlaced(startDate, endDate, singleDate) : null;


    }

    @Override
    public List<OrderReport> ordersOutstanding(LocalDate startDate, LocalDate endDate, LocalDate singleDate, String catName) {
        return orderReportDao.ordersOutstanding(startDate, endDate, singleDate, catName) != null || !(orderReportDao.ordersOutstanding(startDate, endDate, singleDate, catName).isEmpty()) ? orderReportDao.ordersOutstanding(startDate, endDate, singleDate, catName) : null;

    }

    @Override
    public List<OrderReport> ordersDelivered(LocalDate startDate, LocalDate endDate) {
        return orderReportDao.ordersDelivered(startDate, endDate) != null || !(orderReportDao.ordersDelivered(startDate, endDate).isEmpty()) ? orderReportDao.ordersDelivered(startDate, endDate) : null;
    }

    @Override
    public List<OrderReport> getAllOrders() {
       return orderReportDao.getAllOrders() == null || orderReportDao.getAllOrders().isEmpty() ?  null: orderReportDao.getAllOrders() ;
    }
   
    

}