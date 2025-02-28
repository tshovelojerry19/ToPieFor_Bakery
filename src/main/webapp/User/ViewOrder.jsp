<%@page import="com.topiefor.models.Order"%>
<%@page import="com.topiefor.models.Address"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="com.topiefor.models.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="com.topiefor.models.Category"%>
<%@page import="com.topiefor.models.Product"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    List<Product> cart_list = (ArrayList<Product>) session.getAttribute("cart-list");
    if (cart_list != null) {
        request.setAttribute("cart_list", cart_list);
    }
    User user = (User) request.getSession().getAttribute("user");

    if (user != null) {
        request.setAttribute("user", user);
    }
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE/d /yyyy");
    String formattedDate = currentDate.format(formatter);
    double totalPrice = 0;
    List<Order> orders = (List<Order>) request.getAttribute("orders");
    
    Order order = (Order) request.getAttribute("order");

    double total = 0;

%>

<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="UserCSS/ViewOrderCSS.css" type="text/css" rel="stylesheet" />
        <script src="UserJS/ViewOrderJS.js"></script>
        
        <%@include file="head.jsp"%>
        <title>JSP Page</title>

        <style>
            
        </style>
    </head>
    <body>

        <div class="d-flex flex-column justify-content-center align-items-center" id="order-heading">
            <div class="text-uppercase">
                <p>Order detail</p>
            </div>

            <div class="h4">Order #<%=order.getOrderID()%></div>
            <div class="pt-1">
                <p>Order<b class="text-dark"> Review</b></p>
            </div>

        </div>

        <div class="wrapper bg-white">
            <form action="ChekOutController">
                <div class="table-responsive">
                    <table class="table table-borderless">
                        <thead>
                            <tr class="text-uppercase text-muted">
                                <th scope="col">Ordered products</th>

                            </tr>
                        </thead>

                    </table>
                </div>
                <%

                    if (orders != null && !orders.isEmpty()) {
                        for (Order p : orders) {

                %>
                <div class="d-flex justify-content-between align-items-center list py-1">
                    <div class="order-item" style="width:10vw;"><%=p.getProd().getName()%></div>
                    <div class="order-item"><b>X <%=p.getProd().getQuantity()%></b></div>
                    <div class="d-flex justify-content-start align-items-center list py-1">
                        <div class="order-item"><%=p.getProd().getPrice()%></div>
                          <%
                              total += p.getProd().getPrice();
                          %>
                    </div>
                </div>
                <%}
                    }%>
                <div class="pt-2 border-bottom mb-3"></div>
               

                <!--            <div class="d-flex justify-content-start align-items-center pb-4 pl-3 border-bottom">
                                <div class="text-muted"> <button class="text-white btn">50% Discount</button> </div>
                                <div class="ml-auto price"> -$34.94 </div>
                            </div>-->
                <div class="d-flex justify-content-start align-items-center pl-3 py-3 mb-4 border-bottom">
                    <div class="text-muted"> Total Amount </div>
                    <div class="ml-auto h5">R <%=total%> </div>
                </div>
                <div class="row border rounded p-1 my-3">
                    <div class="col-md-6 py-3">
                        <div class="d-flex flex-column align-items start"> <b>Date</b>
                            <p class="text-justify pt-2"><%= order.getDate()%> </p>
                            <p class="text-justify"></p>
                        </div>
                    </div>

                    <div class="col-md-6 py-3">
                        <div class="d-flex flex-column align-items start"> <b>Delivery Date</b>
                            <p class="text-justify pt-2"><%= order.getDateToBeDelivered()%> </p>
                            <p class="text-justify"></p>
                        </div>
                    </div>
                    <div class="col-md-6 py-3">
                        <div class="d-flex flex-column align-items start"> <b>Delivery Address</b>
                            <p class="text-justify pt-2"><%= order.getAddress().getStreet()%>, <%= order.getAddress().getSuburb()%>,</p>
                            <p class="text-justify"><%= order.getAddress().getCode()%></p>
                        </div>
                    </div>
                </div>
                <!--            <div class="pl-3 font-weight-bold">Related Subsriptions</div>-->
                <div class="btn">
                    <button class="btn btn-success"><a href="OrderController?action=getOrder&userID=<%=user.getUserID()%>">Back To Orders</a></button>
                </div></form>

        </div>

    </body>
</html>
