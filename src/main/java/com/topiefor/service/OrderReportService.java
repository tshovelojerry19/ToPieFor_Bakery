package com.topiefor.service;

import com.topiefor.models.OrderReport;
import java.time.LocalDate;
import java.util.List;

public interface OrderReportService {
    List<OrderReport> ordersPlaced(LocalDate startDate, LocalDate endDate, LocalDate singleDate);
    List<OrderReport> ordersOutstanding(LocalDate startDate, LocalDate endDate, LocalDate singleDate, String catName);
    List<OrderReport> ordersDelivered(LocalDate startDate, LocalDate endDate);
    List<OrderReport> getAllOrders();
}