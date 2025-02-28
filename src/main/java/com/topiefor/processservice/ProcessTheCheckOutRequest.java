package com.topiefor.processservice;

import com.topiefor.controller.ProcessRequest;

import com.topiefor.dao.OrderDao;
import com.topiefor.dao.impl.AddressDaoImpl;
import com.topiefor.dao.impl.OrderDaoImpl;
import com.topiefor.dao.impl.PaymentDaoImpl;
import com.topiefor.database.manager.DatabaseManager;
import com.topiefor.models.Address;
import com.topiefor.models.Order;
import com.topiefor.models.Payment;
import com.topiefor.models.Product;
import com.topiefor.models.User;
import com.topiefor.service.AddressService;
import com.topiefor.service.OrderService;
import com.topiefor.service.PaymentService;
import com.topiefor.service.impl.AddressServiceImpl;
import com.topiefor.service.impl.OrderServiceImpl;
import com.topiefor.service.impl.PaymentServiceImpl;
import com.topiefor.utils.EmailSender;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessTheCheckOutRequest implements ProcessRequest {

    private String page;
    private OrderService orderService;

    public ProcessTheCheckOutRequest(OrderDao orderDao) {
        orderService = new OrderServiceImpl(orderDao);
    }

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {

        DatabaseManager databaseManager = null;
        ServletContext sc = request.getServletContext();
        Connection con = null;
        List<Address> allAdressesByUser;
        User user = (User) request.getSession().getAttribute("user");

        if (sc != null) {
            databaseManager = (DatabaseManager) sc.getAttribute("dbman");
            if (databaseManager != null) {
                con = databaseManager.getConnection();
                AddressService addressService = new AddressServiceImpl(AddressDaoImpl.getInstance(con));

                String checkAction = request.getParameter("action");
                if (checkAction != null) {
                    switch (checkAction.trim()) {
                        case "getA":
                            allAdressesByUser = addressService.getAllAddressesByUserID(user.getUserID());
                            request.setAttribute("allAdressesByUser", new ArrayList<>(allAdressesByUser));
                            page = "User/CheckOutPage.jsp";
                            break;
                        case "addOrder":
                            int addressID = 0;
                            String address = request.getParameter("addressID");
                            //delivery address
                            if (address != null && !address.isEmpty()) {
                                address = address.trim();
                                try {
                                    addressID = Integer.parseInt(address);
                                } catch (NumberFormatException ex) {
                                    System.out.println("error" + ex.getMessage());
                                }
                                Address addressFromDb = addressService.getAddressByID(addressID);
                                if (addressFromDb != null) {

                                    double totalAmount = 0.00;
                                    ArrayList<Product> cartList = (ArrayList<Product>) request.getSession().getAttribute("cart-list");

                                    PaymentService paymentService = new PaymentServiceImpl(PaymentDaoImpl.getInstance(con));

                                    if (paymentService.makePayment("credit", totalAmount)) {
                                        orderService = new OrderServiceImpl(OrderDaoImpl.getInstance(con));
                                        // orderService.addOrder(new Order(new Address(street, suburb, code), user, totalAmount, cartList, new Payment(0, "Approved", true)));
                                        if (orderService.addOrder(new Order(addressFromDb, user, totalAmount, cartList, new Payment(0, "Approved", true)))) {
                                            EmailSender email = new EmailSender();
                                            email.orderPlacedEmail(user.getEmail(), user.getUserName(), cartList, addressFromDb);
                                        }
                                        request.setAttribute("message", "approved");
                                        request.getSession().removeAttribute("cart-list");
                                        page = "User/CheckOutPage.jsp";
                                      
                                    } else {
                                        request.setAttribute("message", "declined");
                                        page = "ChekOutController?action=getA";
                                       
                                    }
                                }
                            }
                    }
                }
            }
        }
    }

    @Override
    public void processTheResponse(HttpServletRequest request, HttpServletResponse response
    ) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        try {
            requestDispatcher.forward(request, response);

        } catch (ServletException | IOException ex) {
            System.err.println("Error Dispatching View: " + ex.getMessage());
        }
    }
}
