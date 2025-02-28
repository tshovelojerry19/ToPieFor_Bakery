
package com.topiefor.controller;

import com.topiefor.dao.UnitDao;
import com.topiefor.dao.impl.UnitDaoImpl;
import com.topiefor.database.manager.DatabaseManager;
import com.topiefor.models.User;
import com.topiefor.processservice.processTheUnitRequest;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UnitController", urlPatterns = {"/UnitController"})
public class UnitController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("User/LoginPage.jsp");
            requestDispatcher.forward(request, response);
        } else {
        ProcessRequest pr = UnitRequestActionFactory.process(request);
        if (pr != null) {  
            pr.processTheRequest(request, response);

            pr.processTheResponse(request, response);
          
        }
    }}

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

abstract class UnitRequestActionFactory {

    public static ProcessRequest process(HttpServletRequest request) {
        DatabaseManager databaseManager = null;
        ServletContext sc = request.getServletContext();
        if (sc != null) {
            databaseManager = (DatabaseManager) sc.getAttribute("dbman");
        }
        if (databaseManager == null) {
            return null;
        }
        UnitDao unitDao = UnitDaoImpl.getInstance(databaseManager.getConnection());

        return new processTheUnitRequest(unitDao);
    }
}
