package com.topiefor.controller;

import com.topiefor.dao.ProductDao;
import com.topiefor.dao.impl.ProductDaoImpl;
import com.topiefor.database.manager.DatabaseManager;
import com.topiefor.service.ProductService;
import com.topiefor.service.impl.ProductServiceImpl;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "IndexController", urlPatterns = {"/IndexController"})
public class IndexController extends HttpServlet {
private  RequestDispatcher requestDispatcher;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        DatabaseManager databaseManager = null;
        ServletContext sc = request.getServletContext();
        if (sc != null) {
            databaseManager = (DatabaseManager) sc.getAttribute("dbman");
        }
        if (databaseManager == null) {
            requestDispatcher = request.getRequestDispatcher("Admin/ErrorPage.jsp");
            requestDispatcher.forward(request, response);
        }
        ProductDao productDao = ProductDaoImpl.getInstance(databaseManager.getConnection());
        ProductService productService = new ProductServiceImpl(productDao);
        {
            try {
                request.setAttribute("allProducts", productService.getAllProducts());
                 requestDispatcher = request.getRequestDispatcher("User/UserHomePage.jsp");
                requestDispatcher.forward(request, response);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}