package com.topiefor.controller;
import com.topiefor.models.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AddToCartController", urlPatterns = {"/AddToCartController"})
public class AddToCartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && !action.isEmpty()) {
            CartRequestActionFactory.process(action, request, response);

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

}

class CartRequestActionFactory  {
// getting cart from session and send it to session
    public static void process(String action, HttpServletRequest request, HttpServletResponse response) {
       
             try (PrintWriter out = response.getWriter()) {

                List<Product> cartList = new ArrayList<>();
                int productID = Integer.parseInt(request.getParameter("productID"));
                double productPrice = Double.parseDouble(request.getParameter("productPrice"));
                String productName = request.getParameter("productName");
                int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
                String productImage = request.getParameter("productImage");
                Product product = new Product(productID, productName, productPrice, productQuantity, productImage);
                HttpSession session = request.getSession();
                ArrayList<Product> cart_list = (ArrayList<Product>) session.getAttribute("cart-list");
                if (cart_list == null) {
                    cartList.add(product);
                    session.setAttribute("cart-list", cartList);
                    response.sendRedirect("IndexController");
                } else {
                    cartList = cart_list;

                    boolean exist = false;
                    for (Product p : cart_list) {
                        if (p.getProductID() == productID) {
                            exist = true;
                            out.println("<h3> Item Already in Cart</h3>");
                        }
                    }

                    if (!exist) {
                        cartList.add(product);
                        response.sendRedirect("IndexController");
                    }
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
           

        }
    }

  

