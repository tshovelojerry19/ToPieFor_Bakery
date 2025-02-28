package com.topiefor.controller;

import com.topiefor.models.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RemoveFromCartController")
public class RemoveFromCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        response.setContentType("text/html;charset=UTF-8");

        String productId = request.getParameter("productID");
        if (productId != null) {
            List<Product> cart_list = (ArrayList<Product>) request.getSession().getAttribute("cart-list");
            if (cart_list != null) {
                for (Product p : cart_list) {
                    if (p.getProductID() == Integer.parseInt(productId)) {
                        cart_list.remove(cart_list.indexOf(p));
                        break;
                    }
                }
            }
            request.setAttribute("openCart", "open");
            requestDispatcher = request.getRequestDispatcher("IndexController?action=get");
            requestDispatcher.forward(request, response);

        } else {
            response.sendRedirect("IndexController");
        }

    }
}
