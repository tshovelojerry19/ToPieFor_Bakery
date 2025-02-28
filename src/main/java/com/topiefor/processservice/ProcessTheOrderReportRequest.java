package com.topiefor.processservice;

import com.topiefor.service.impl.OrderServiceImpl;
import com.topiefor.controller.ProcessRequest;
import com.topiefor.dao.CategoryDao;
import com.topiefor.dao.OrderDao;
import com.topiefor.dao.OrderReportDao;
import com.topiefor.models.Category;
import com.topiefor.models.Order;
import com.topiefor.models.OrderReport;
import com.topiefor.service.impl.CategoryServiceImpl;
import com.topiefor.service.impl.OrderReportServiceImpl;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessTheOrderReportRequest implements ProcessRequest {

    private String page;
    private OrderReportServiceImpl orderReportServiceImpl;

    public ProcessTheOrderReportRequest(OrderReportDao orderReportDao) {
        orderReportServiceImpl = new OrderReportServiceImpl(orderReportDao);
    }

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {

        //Order order = null;
        List<OrderReport> allOrderReports = null;
        List<OrderReport> allOutstandingOrderReports = null;
        List<OrderReport> allDeliveredOrderReports = null;
        String catNamee = "Cakes";

        String orderReportAction = request.getParameter("action");

        LocalDate SDate = LocalDate.now();
        LocalDate EDate = LocalDate.now();
        LocalDate today = LocalDate.now();

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String singleDate = request.getParameter("singleDate");

        if (orderReportAction != null) {
            switch (orderReportAction.trim()) {


                //---------------------------------------------------------------------------------------------------------
                case "get":

                    System.out.println("Single Date: " + singleDate);
                    if (startDate != null && endDate != null) {
                        SDate = LocalDate.parse(startDate);
                        EDate = LocalDate.parse(endDate);
                    } else if (singleDate != null) {
                        today = LocalDate.parse(singleDate);

                    }
                    allOrderReports = orderReportServiceImpl.ordersPlaced(SDate, EDate, today);
                    System.out.println("list: " + allOrderReports.toString());
                    if (allOrderReports != null && !allOrderReports.isEmpty()) {
                        request.setAttribute("allOrderReports", allOrderReports);
                    } else {
                        request.setAttribute("response", "no data");
                    }
                    page = "Admin/AdminHomePage.jsp";
                    break;

 
                default:
                    request.setAttribute("response", "something went wrong");
                    break;
            }
        }
    }

    private boolean checkFlag(String orderStatus, boolean flagStatus, HttpServletRequest request) {
        switch (orderStatus) {
            case "Activate":
                flagStatus = true;
                break;
            case "Deactivate":
                flagStatus = false;
                break;
            default:
                request.setAttribute("error", "error");
                break;
        }
        return flagStatus;
    }

    @Override
    public void processTheResponse(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        try {
            requestDispatcher.forward(request, response);

        } catch (ServletException | IOException ex) {
            System.err.println("Error Dispatching View: " + ex.getMessage());
        }
    }
}
