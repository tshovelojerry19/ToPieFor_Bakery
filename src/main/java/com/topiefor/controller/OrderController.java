package com.topiefor.controller;


import com.topiefor.dao.OrderDao;
import com.topiefor.dao.impl.OrderDaoImpl;
import java.io.IOException;
import com.topiefor.database.manager.DatabaseManager;
import com.topiefor.models.User;
import com.topiefor.processservice.ProcessTheOrderRequest;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "OrderController", urlPatterns = {"/OrderController"})
public class OrderController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("User/LoginPage.jsp");
            requestDispatcher.forward(request, response);
        } else {
        ProcessRequest pr = OrderRequestActionFactory.process(request);
        if (pr != null) {  
            pr.processTheRequest(request, response);
            pr.processTheResponse(request, response);
          
        }}
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}

abstract class OrderRequestActionFactory {

    public static ProcessRequest process(HttpServletRequest request) {
        DatabaseManager databaseManager = null;
        ServletContext sc = request.getServletContext();
        if (sc != null) {
            databaseManager = (DatabaseManager) sc.getAttribute("dbman");
        }
        if (databaseManager == null) {
            return null;
        }
        OrderDao con = OrderDaoImpl.getInstance(databaseManager.getConnection());
        
        return new ProcessTheOrderRequest(con);
    }
}