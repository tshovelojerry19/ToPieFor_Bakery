package com.topiefor.controller;

import com.topiefor.dao.impl.UserDaoImpl;
import com.topiefor.database.manager.DatabaseManager;
import com.topiefor.models.User;
import com.topiefor.service.UserService;
import com.topiefor.service.impl.UserServiceImpl;
import com.topiefor.utils.PasswordEncryptor;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    //user validation
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = null;
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        switch (action) {
            case "login":
                DatabaseManager databaseManager = null;
                ServletContext sc = request.getServletContext();
                if (sc != null) {
                    databaseManager = (DatabaseManager) sc.getAttribute("dbman");
                }   if (databaseManager != null) {
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    UserService userService = new UserServiceImpl(UserDaoImpl.getInstance(databaseManager.getConnection()));
                    //if user exist fetch the user
                    User user = userService.checkUser(email);
                    
                    if (user != null) {
                        PasswordEncryptor passwordEncryptor = new PasswordEncryptor();
                        if (!(passwordEncryptor.checkPassword(password, user.getPassword()))) {
                            request.setAttribute("message", "Incorrect Details");
                            requestDispatcher = request.getRequestDispatcher("User/LoginPage.jsp");
                            requestDispatcher.forward(request, response);
                        } else {
                            request.getSession().setAttribute("user", user);
                            requestDispatcher = request.getRequestDispatcher("User/go.jsp");
                            requestDispatcher.forward(request, response);
                        }
                    } else {
                        request.setAttribute("message", " User Not Registered, Please signUp ");
                        requestDispatcher = request.getRequestDispatcher("User/LoginPage.jsp");
                        requestDispatcher.forward(request, response);
                    }
                }   break;
            case "logout":
                if (request.getSession().getAttribute("user") != null) {
                    request.getSession().removeAttribute("user");
                    request.getSession().removeAttribute("cart-list");
                    requestDispatcher = request.getRequestDispatcher("User/go.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    requestDispatcher = request.getRequestDispatcher("User/go.jsp");
                    requestDispatcher.forward(request, response);
                }   break;
            default:
                 requestDispatcher = request.getRequestDispatcher("Admin/ErrorPage.jsp");
                    requestDispatcher.forward(request, response);
                break;
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
