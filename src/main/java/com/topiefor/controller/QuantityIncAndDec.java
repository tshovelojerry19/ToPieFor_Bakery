package com.topiefor.controller;

import com.topiefor.models.Product;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "QuantityIncAndDec", urlPatterns = {"/QuantityIncAndDec"})
public class QuantityIncAndDec extends HttpServlet {
// increamenting/ decreament the products quqntity

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        HttpSession session = request.getSession();
        ArrayList<Product> cart_list = (ArrayList<Product>) session.getAttribute("cart-list");

        String ac = request.getParameter("ac");
        int id = Integer.parseInt(request.getParameter("id"));
        if (ac != null && id >= 1) {

            if (ac.equals("inc")) {
                for (Product p : cart_list) {
                    if (p.getProductID() == id) {
                        int quantity = p.getQuantity();
                        quantity++;
                        p.setQuantity(quantity);

                        request.setAttribute("openCart", "open");

                        requestDispatcher = request.getRequestDispatcher("IndexController?action=get");
                        requestDispatcher.forward(request, response);

                    }
                }
            }
            if (ac.equals("dec")) {
                for (Product p : cart_list) {
                    if (p.getProductID() == id && p.getQuantity() > 1) {
                        int quantity = p.getQuantity();
                        quantity--;
                        p.setQuantity(quantity);
                        break;
                    }
                }

                request.setAttribute("openCart", "open");
                 requestDispatcher = request.getRequestDispatcher("IndexController?action=get");
                requestDispatcher.forward(request, response);
            }
        } else {
            try {
                response.sendRedirect("IndexController?action=get");
            } catch (IOException ex) {
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
    }

}
