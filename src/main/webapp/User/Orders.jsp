<%@page import="com.topiefor.models.Order"%>
<%@page import="com.topiefor.models.Product"%>
<%@page import="com.topiefor.models.User"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    DecimalFormat dcf = new DecimalFormat("#.##");

    User user = (User) request.getSession().getAttribute("user");

    if (user != null) {
        request.setAttribute("user", user);
    } else {
        response.sendRedirect("login.jsp");
    }
    List<Product> cart_list = (ArrayList<Product>) session.getAttribute("cart-list");
    if (cart_list != null) {
        request.setAttribute("cart_list", cart_list);
    }
    List<Order> orders = (ArrayList<Order>) request.getAttribute("orders");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="head.jsp"%>
        <link href="UserJS/OrdersCSS.css" type="text/css" rel="stylesheet" />
        <script src="UserJS/OrdersJS.js"></script>

        <title>To pie for</title>

        <style>
            body {
                background-image: url('Images/order_back.jpg');
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: cover;
                font-family: Arial, Helvetica, sans-serif;
            }
            * {
                box-sizing: border-box;
            }
            .nav{
                color: white;
                background: rgba(0, 0, 0, .1);
                display: flex;
                justify-content: space-between;
                margin-top: 1%;
                overflow: hidden;
                width: 100%;
            }
            .nav ul{


                list-style: none;
                margin-top: 2%;
                margin-right: 13px;

                font-size: 1em;
                font-weight: bold;


            }
            .nav li{

                display: inline;
                margin-right: 30px;
                padding: 15px;
            }
            .intro {
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                width: 100%;
                height: 400px;
                background: linear-gradient(to bottom, rgba(0, 0, 0, 0.5) 0%, rgba(0, 0, 0, 0.5) 100%), url("https://media.timeout.com/images/105899032/1920/1080/image.jpg");
                background-size: cover;
                background-position: center;
                background-repeat: no-repeat;
                color: white;
            }
            .row img{
                height: 30vh;
            }
            a{
                color: white;
                margin-right: 25px;
                text-decoration: none;
            }
            a:hover{
                color: black;
                background: white;

                transition: ease-in;
            }


            * {
                box-sizing: border-box;
                font-family: Arial, Helvetica, sans-serif;
            }

            /*         Full-width input fields 
                    input[type=text], input[type=password] {
                        width: 100%;
                        padding: 15px;
                        margin: 5px 0 22px 0;
                        display: inline-block;
                        background: #f1f1f1;
            
                        border: 1px solid #ccc;
                        box-sizing: border-box;
                    }*/

            /*        input[type=number] {
                        width: 5vw;
                        height: 15%;
                        padding: 5px;
                        margin: 5px 0 22px 0;
                        margin-right: 3%;
                        display: inline-block;
                        background: #f1f1f1;
            
                        border: 1px solid #ccc;
                        box-sizing: border-box;
                    }
            
            
            
                     Add a background color when the inputs get focus 
                    input[type=text]:focus, input[type=password]:focus {
                        background-color: #ddd;
                        outline: none;
                    }*/

            /* Set a style for all buttons */
            buttonnn {
                background-color: #04AA6D;
                color: white;
                padding: 8px 20px;
                /*           // margin: 8px 0;*/
                border: none;
                cursor: pointer;
                width: 100%;
                opacity: 0.9;
                border-radius: 5px;
            }

            /*set style for all button hover*/
            buttonnn:hover {
                opacity:1;
            }


            /*         Extra styles for the cancel button 
                    .cancelbtn {
                        padding: 14px 20px;
                        background-color: #f44336;
                    }
            
                     Float cancel and signup buttons and add an equal width 
                    .cancelbtn, .signupbtn {
                        float: left;
                        width: 50%;
                    }*/

            /*         Extra styles for the signup button 
                    .signupbtn {
                        padding: 14px 20px;
                        background-color: #2E8B57;
                    }
            
                     Float signup buttons and add an equal width 
                    .signupbtn, .signupbtn {
                        float: left;
                        width: 50%;
                    }*/

            /*         Extra styles for the forgot button 
                    .forgotbtn {
                        padding: 14px 20px;
                        background-color: #0096FF;
                    }
            
                     Float forgot buttons and add an equal width 
                    .forgotbtn, .forgotbtn {
                        float: left;
                        width: 50%;
                    }*/

            /*         Extra styles for the cancel button 
                    .passwordbtn {
                        padding: 14px 20px;
                        background-color:  #B21807;
                    }
            
                     Float cancel and signup buttons and add an equal width 
                    .passwordbtn {
                        float: left;
                        width: 50%;
                    }*/

            /* Center the image and position the close button */
            .imgcontainer {
                text-align: center;
                margin: 24px 0 12px 0;
                position: relative;
            }

            /*avatar img*/
            img.avatar {
                width: 40%;
                border-radius: 50%;
            }

            /*span password*/
            span.psw {
                float: right;
                padding-top: 16px;
                color:#f44336;
            }



            /* Add padding to container elements */
            .container {
                padding: 16px;
            }

            /* The Modal (background) */
            /*        .modal {
                        display: none;  Hidden by default 
                        position: fixed;  Stay in place 
                        z-index: 1;  Sit on top 
                        left: 0;
                        top: 0;
                        width: 100%;  Full width 
                        height: 100%;  Full height 
                        overflow: auto;  Enable scroll if needed 
                        background-color: #b5b8be;
                        padding-top: 50px;
                    }
                     Modal Content/Box 
                    .modal-content {
                        background-color: #fefefe;
                        margin: 5% auto 15% auto;  5% from the top, 15% from the bottom and centered 
                        border: 1px solid #888;
                        width: 80%;  Could be more or less, depending on screen size 
                    }*/

            /*Cart model*/
            .modalCart {
                display: none; /* Hidden by default */
                position: fixed; /* Stay in place */
                z-index: 1; /* Sit on top */
                left: 0;
                top: 0;
                width: 100%; /* Full width */
                height: 100vh; /* Full height */
                overflow: auto; /* Enable scroll if needed */
                background-color: #b5b8be;
                /*padding-top: 50px;*/
            }

            /* Modal Cart Content/Box */
            .modalCart-content {
                background-color: #fefefe;
                margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
                border: 1px solid #888;
                width: 80%; /* Could be more or less, depending on screen size */
            }

            /* Style the horizontal ruler */
            hr {
                border: 1px solid #f1f1f1;
                margin-bottom: 25px;
            }

            /*         The Close Button (x) 
                    .close {
                        position: absolute;
                        right: 35px;
                        top: 15px;
                        font-size: 40px;
                        font-weight: bold;
                        color: #f1f1f1;
                    }
            
                    close button hover and focus
                    .close:hover,
                    .close:focus {
                        color: #f44336;
                        cursor: pointer;
                    }*/

            /*         Clear floats 
                    .clearfix::after {
                        content: "";
                        clear: both;
                        display: table;
                    }*/

            /*         Add Zoom Animation 
                    .animate {
                        -webkit-animation: animatezoom 0.6s;
                        animation: animatezoom 0.6s
                    }
            
                    @-webkit-keyframes animatezoom {
                        from {
                            -webkit-transform: scale(0)
                        }
                        to {
                            -webkit-transform: scale(1)
                        }
                    }
            
                    @keyframes animatezoom {
                        from {
                            transform: scale(0)
                        }
                        to {
                            transform: scale(1)
                        }
                    }*/

            /* Change styles for span and cancel button on extra small screens */
            @media screen and (max-width: 300px) {
                span.psw {
                    display: block;
                    float: none;
                }
            }

            /*         Change styles for cancel button and signup button on extra small screens 
                    @media screen and (max-width: 300px) {
                        .cancelbtn, .signupbtn {
                            width: 100%;
                        }
                    }*/


            /*Cart css*/
            @media (min-width: 1025px) {
                .h-custom {
                    height: 100vh !important;
                }
            }


            /*--------------------------------------------------------------------------------*/

            .collapsible {
                background-color: #F9F6EE;
                color: #8F867C;
                cursor: pointer;
                padding: 10px;
                width: 100%;
                border: 2px solid #6E848F;
                text-align: left;
                outline: none;
                font-size: 12px;
            }

            .active, .collapsible:hover {
                background-color: #F0EAD6;
            }

            .content {
                padding: 10px 10px 10px 10px;
                display: none;
                overflow: hidden;
                background-color: #f1f1f1;
                text-align: center;
            }

            /*btn css for add to cart*/


            a{
                color:white;
            }



        </style>
    </head>
    <body>
        <div class="nav" >
            <!--top left welcome-->
            <h3 class="logo" style="margin-left: 100px; margin-top: 2%; bold">Orders</h3>
            <!--header list top right-->
            <ul>
                <li><a  href="IndexController" ><button class="btn">Home</button></a></li>
                <li><a class="btn" href="UserController?action=editForm" >Profile</a></li>

                <%if (user == null) {
                        request.setAttribute("user", user);
                %>
                <li><a  href="User/LoginPage.jsp#signup" ><buttonnn  onclick="document.getElementById('signupModal').style.display = 'block'" ">Sign Up</buttonnn></a></li>
                <li><a  href="User/LoginPage.jsp#login" ><buttonnn onclick="document.getElementById('loginModal').style.display = 'block'" ">Login</buttonnn></a></li>
                            <%} else {%>
                <li><a href="OrderController?action=getOrder&userID=<%= user.getUserID()%>" class="btn">Orders</a></li>
                <li><a href="LoginController?action=logout" class="btn">Logout</a></li>
                    <%}%>
            </ul>
        </div>

        <div class="container">
            <div class="card-header my-3">All Orders</div>
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">OrderID</th>
                        <th scope="col">Date</th>
                        <th scope="col">Date To Be Delivered</th>
                        <th scope="col">Delivery Address</th>
                        <th scope="col">Amount</th>
                    </tr>
                </thead>
                <tbody>

                    <%
                        if (orders != null) {
                            for (Order o : orders) {%>
                    <tr>
                        <td><%=o.getOrderID()%></td>
                        <td><%=o.getDate()%></td>
                        <td><%=o.getDateToBeDelivered()%></td>
                        <td><%=o.getAddress().getStreet() + ", " + o.getAddress().getSuburb() + ", " + o.getAddress().getCode()%></td>
                        <td>R <%=dcf.format(o.getTotalPrice())%></td>
                        <td><a class="btn btn-sm btn-success" href="OrderController?action=viewOrder&orderID=<%=o.getOrderID()%>">View Order</a></td>
                    </tr>
                    <%}
                        }
                    %>

                </tbody>
            </table>
        </div>

    </body>
</html>