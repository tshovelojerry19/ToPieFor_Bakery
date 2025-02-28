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
    List<Product> products = (List<Product>) request.getAttribute("allProducts");
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
    String message = (String) request.getAttribute("message");

%>

<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="UserCSS/CheckOutPageCSS.css" type="text/css" rel="stylesheet" />
        <script src="UserJS/CheckOutPageJS.js"></script>
        <%@include file="head.jsp"%>
        <title>JSP Page</title>

        <style>

        </style>
    </head>
    <body>
        <div id="popup" class="form-container">
            <div class="con">

                <% if (message != null && message.equals("approved")) {%>
                <div class="icon"><img src="check.png"></div>
                <p2>A confirmation Email was sent</p2><br>
                    <%}
                        if (message != null && message.equals("declined")) {%>
                <div class="icon"> <img src="declined.png"></div><br>
                    <%}%>
                <div id="messageContainer"></div><br>
                <a href="IndexController"><button class="btn2" onclick="closeForm()" >Continue Shopping</button></a>
                <a href=OrderController?action=getOrder&userID=<%= user.getUserID()%>> <button class="btn2" onclick="closeForm()" >View Order</button></a>
                <a href="RecipeController?action=get"></a>
            </div>
        </div>
        <div class="d-flex flex-column justify-content-center align-items-center" id="order-heading">
            <div class="text-uppercase">
                <p>Order detail</p>
            </div>

            <div class="h4"><%=formattedDate%></div>
            <div class="pt-1">
                <p>Order<b class="text-dark"> Review</b></p>
            </div>
            <div class="btn close text-white"> &times; </div>
        </div>

        <div class="wrapper bg-white">
            <form action="ChekOutController">
                <div class="table-responsive">
                    <table class="table table-borderless">
                        <thead>
                            <tr class="text-uppercase text-muted">
                                <th scope="col">products</th>

                            </tr>
                        </thead>

                    </table>
                </div>
                <%
                    if (cart_list != null && !cart_list.isEmpty()) {
                        for (Product p : cart_list) {
                            totalPrice += (p.getPrice() * p.getQuantity());
                %>
                <div class="d-flex justify-content-between align-items-center list py-1">
                    <div class="order-item" style="width:10vw;"><%=p.getName()%></div>
                    <div class="order-item"><b>X <%=p.getQuantity()%></b></div>
                    <div class="d-flex justify-content-start align-items-center list py-1">
                        <div class="order-item"><%=p.getPrice() * p.getQuantity()%></div>

                    </div>
                </div>
                <%}
                    }%>
                <div class="pt-2 border-bottom mb-3"></div>
                <div class="d-flex justify-content-start align-items-center pl-3">
                    <div class="text-muted">Payment Method</div>
                    <div class="ml-auto"> <img src="https://www.freepnglogos.com/uploads/mastercard-png/mastercard-logo-logok-15.png" alt="" width="30" height="30"> <label>Mastercard</label> </div>
                </div>

                <!--            <div class="d-flex justify-content-start align-items-center pb-4 pl-3 border-bottom">
                                <div class="text-muted"> <button class="text-white btn">50% Discount</button> </div>
                                <div class="ml-auto price"> -$34.94 </div>
                            </div>-->
                <div class="d-flex justify-content-start align-items-center pl-3 py-3 mb-4 border-bottom">
                    <div class="text-muted"> Today's Total </div>
                    <div class="ml-auto h5">R <%=totalPrice%> </div>
                </div>
                <div class="row border rounded p-1 my-3">
                    <div class="col-md-6 py-3">
                        <div class="d-flex flex-column align-items start"> <b>Billing Address</b>
                            <p class="text-justify pt-2"><%= user.getAddress().getStreet()%>, <%= user.getAddress().getSuburb()%>,</p>
                            <p class="text-justify"><%= user.getAddress().getCode()%></p>
                        </div>
                    </div>

                    <div class="col-md-6 py-3">
                        <div class="d-flex flex-column align-items start"> <b>Shipping Address</b> 

                            <select name="addressID">
                                <%
                                    List<Address> addresses = (List<Address>) request.getAttribute("allAdressesByUser");
                                    if (addresses != null && !addresses.isEmpty()) {
                                        for (Address a : addresses) {

                                %>
                                <option value="<%=a.getAddressID()%>"><%=a.getStreet() + "," + a.getSuburb() + "," + a.getCode()%></option>
                                <%}
                                    }%>
                            </select>
                            <input type="hidden" name="action" value="addOrder">
                            <input type="hidden" name="totalAmount" value="<%=totalPrice%>">
                            <!--                            <p class="text-justify"><button class="btn btn-success ">Enter a new Address</button></p>-->
                        </div>
                    </div>
                </div>
                <!--            <div class="pl-3 font-weight-bold">Related Subsriptions</div>-->
                <div class="btn">
                    <button class="btn btn-success">Proceed</button>
                </div></form>

        </div>

    </body>
</html>
<script>
    var message = '<%=message%>';
    console.log(message);
    if (message !== 'null' && message !== '') {
        var messageContainer = document.getElementById("messageContainer");
        messageContainer.innerHTML = '<h2>' + message + '</h2>';
        var popup = document.getElementById("popup");
        popup.style.display = "block";
    }
</script>