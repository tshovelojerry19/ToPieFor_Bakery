package com.topiefor.processservice;

import com.topiefor.service.impl.OrderServiceImpl;
import com.topiefor.controller.ProcessRequest;
import com.topiefor.dao.CategoryDao;
import com.topiefor.dao.OrderDao;
import com.topiefor.models.Category;
import com.topiefor.models.Order;
import com.topiefor.models.User;
import com.topiefor.service.impl.CategoryServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessTheOrderRequest implements ProcessRequest {

    private String page;
    private OrderServiceImpl orderServiceImpl;

    public ProcessTheOrderRequest(OrderDao orderDao) {
        orderServiceImpl = new OrderServiceImpl(orderDao);
    }

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            page = "User/LoginPage.jsp";
        } else {
            //Order order = null;
            List<Order> allOrders = null;
            String orderAction = request.getParameter("action");

            int orderID = 0;
            String orderIDS;
            String orderStatus;
            boolean flagStatus = false;
            int categoryID = 0;

            if (orderAction != null) {
                switch (orderAction.trim()) {

                    //---------------------------------------------------------------------------------------------------------
                    case "edit":

                        orderIDS = request.getParameter("orderID");
                        orderStatus = request.getParameter("status");
                        if (orderIDS != null && !orderIDS.isEmpty()) {
                            try {
                                orderID = Integer.parseInt(orderIDS);
                            } catch (NumberFormatException ex) {
                                System.out.println("error" + ex.getMessage());
                            }
                            if (orderID > 0) {
                                if (orderServiceImpl.changeOrderStatus(new Order(orderID, orderStatus))) {
                                    request.setAttribute("allOrders", new ArrayList<>(orderServiceImpl.getAllOrders()));
                                    request.setAttribute("message", "Order Updated!");
                                } else {
                                    request.setAttribute("message", "Order not Updated!");
                                }
                            }

                        }
                        page = "Admin/OrderPage.jsp";
                        break;

                    case "editBaked":

                        orderIDS = request.getParameter("orderID");
                        orderStatus = request.getParameter("status");
                        if (orderIDS != null && !orderIDS.isEmpty()) {
                            try {
                                orderID = Integer.parseInt(orderIDS);
                            } catch (NumberFormatException ex) {
                                System.out.println("error" + ex.getMessage());
                            }
                            if (orderID > 0) {
                                if (orderServiceImpl.changeOrderStatus(new Order(orderID, orderStatus))) {
                                    request.setAttribute("allOrders", new ArrayList<>(orderServiceImpl.getAllOrders()));
                                    request.setAttribute("message", "Order Updated!");
                                } else {
                                    request.setAttribute("message", "Order not Updated!");
                                }
                            }

                        }
                        page = "Admin/AddressToDeliverPage.jsp";
                        break;

                    case "editDelivered":

                        orderIDS = request.getParameter("orderID");
                        orderStatus = request.getParameter("status");
                        if (orderIDS != null && !orderIDS.isEmpty()) {
                            try {
                                orderID = Integer.parseInt(orderIDS);
                            } catch (NumberFormatException ex) {
                                System.out.println("error" + ex.getMessage());
                            }
                            if (orderID > 0) {
                                if (orderServiceImpl.changeOrderStatus(new Order(orderID, orderStatus))) {
                                    request.setAttribute("allOrders", new ArrayList<>(orderServiceImpl.getAllOrders()));
                                    request.setAttribute("message", "Order Updated!");
                                } else {
                                    request.setAttribute("message", "Order not Updated!");
                                }
                            }

                        }
                        page = "Admin/DeliveredPage.jsp";
                        break;
                    //---------------------------------------------------------------------------------------------------------
                    case "get":
                        allOrders = orderServiceImpl.getAllOrders();
                        if (allOrders != null && !allOrders.isEmpty()) {
                            request.setAttribute("allOrders", allOrders);
                        } else {
                            request.setAttribute("response", "no data");
                        }
                        page = "Admin/OrderPage.jsp";
                        break;

                    case "getBaked":
                        allOrders = orderServiceImpl.getAllOrders();
                        if (allOrders != null && !allOrders.isEmpty()) {
                            request.setAttribute("allOrders", allOrders);
                        } else {
                            request.setAttribute("response", "no data");
                        }
                        page = "Admin/AddressToDeliverPage.jsp";
                        break;

                    case "getDelivered":
                        allOrders = orderServiceImpl.getAllOrders();
                        if (allOrders != null && !allOrders.isEmpty()) {
                            request.setAttribute("allOrders", allOrders);
                        } else {
                            request.setAttribute("response", "no data");
                        }
                        page = "Admin/DeliveredPage.jsp";
                        break;
                    case "getOrder":
                        String userID = request.getParameter("userID");
                        int userId = 0;
                        if (userID != null && !userID.isEmpty()) {
                            userID = userID.trim();
                            try {
                                userId = Integer.parseInt(userID);
                            } catch (NumberFormatException ex) {
                                System.out.println("error" + ex.getMessage());
                            }

                            allOrders = orderServiceImpl.getOrdersByUserID(userId);
                            if (allOrders != null && !allOrders.isEmpty()) {
                                request.setAttribute("orders", allOrders);
                            } else {
                                request.setAttribute("response", "no data");
                            }
                        }
                        page = "User/Orders.jsp";
                        break;
                    case "viewOrder":
                        orderIDS = request.getParameter("orderID");

                        if (orderIDS != null && !orderIDS.isEmpty()) {
                            orderIDS = orderIDS.trim();
                            try {
                                orderID = Integer.parseInt(orderIDS);
                            } catch (NumberFormatException ex) {
                                System.out.println("error" + ex.getMessage());
                            }

                            List<Order> orders = orderServiceImpl.getOrderDetailsByOrderID(orderID);
                           Order order = orderServiceImpl.getAOrderByOrderID(orderID);
                            if (orders != null) {
                               
                                request.setAttribute("orders", orders);
                                request.setAttribute("order", order);
                            } else {
                                request.setAttribute("response", "no data");
                            }
                        }
                        page = "User/ViewOrder.jsp";
                        break;

                    default:
                        request.setAttribute("response", "something went wrong");
                        break;
                }
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
