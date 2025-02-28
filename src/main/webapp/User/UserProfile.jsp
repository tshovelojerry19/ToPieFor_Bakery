<%@page import="com.topiefor.models.Order"%>
<%@page import="com.topiefor.models.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="com.topiefor.models.User"%>
<%@page import="java.text.DecimalFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    DecimalFormat dcf = new DecimalFormat("#.##");

    User user = (User) request.getSession().getAttribute("user");
    User userF = (User) request.getAttribute("userF");

    if (user != null) {
        request.setAttribute("user", user);
    } else {
        response.sendRedirect("User/login.jsp");
    }
    List<Product> cart_list = (ArrayList<Product>) session.getAttribute("cart-list");
    if (cart_list != null) {
        request.setAttribute("cart_list", cart_list);
    }
    List<Order> orders = (ArrayList<Order>) request.getAttribute("orders");
    String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="UserCSS/UserProfileCSS.css" type="text/css" rel="stylesheet" />
        <script src="UserJS/UserProfileJS.js"></script>

        <%@include file="head.jsp"%>
        <title>User Profile Page</title>
        <style>
            body {
                background-image: url('Images/order_back.jpg');
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: cover;
                font-family: Arial, Helvetica, sans-serif;
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
            #popup {
                display: none;
                position: fixed;
                top: 0;
                left:0%;
                text-align: center;
                background: rgba(0, 0, 0, 0.8);

                height: 100vh;
                width:  100vw;
                z-index: 999;

            }
            .con{
                position: fixed;
                top: 35%;
                left: 35%;
                background: white;
                width: 30vw;
                height: 15vw;
                border-radius: 20px;
            }
            .icon img{
                padding-top: 10%;
                margin: auto;
                width: 10vw;
                height: 25vh;
                display: block;

            }
            #messageContainer{
                margin-top: 50px;
            }
        </style>
    </head>
    <body>
        <div class="nav" >
            <!--top left welcome-->
            <h3 class="logo" style="margin-left: 100px; margin-top: 2%; bold">Profile</h3>
            <!--header list top right-->
            <ul>
                <li><a  href="IndexController" ><button class="btn">Home</button></a></li>
                <li><a href="OrderController?action=getOrder&userID=<%= user.getUserID()%>" class="btn">Orders</a></li>
                <li><a href="LoginController?action=logout" class="btn">Logout</a></li>
            </ul>
        </div>
        <div class="container rounded bg-white mt-5 mb-5">
            <div id="popup" class="form-container">
                <div class="con">
                    <div id="messageContainer"></div><br>
                    <button class="btn" onclick="closeForm()" >OK</button>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3 border-right">
                    <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150" src="https://th.bing.com/th/id/R.89f59066dd3183cb465068d3e4269649?rik=z3SMKTw9FKtyPw&riu=http%3a%2f%2feshitadesai.com%2fwp-content%2fuploads%2f2017%2f06%2fProfile-ICon.png&ehk=xSGkDRzX5FK0lvKswGuTSoHwTzArHO3vw3iNXHSom%2b8%3d&risl=&pid=ImgRaw&r=0"><span class="font-weight-bold"><%=userF.getUserName()%></span><span class="text-black-50"><%=userF.getEmail()%></span><span> </span></div>
                </div>
                <div class="col-md-5 border-right">
                    <div class="p-3 py-5">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h4 class="text-right">Profile Settings</h4>
                        </div>
                        <form action="UserController" method="POST">
                            <div class="row mt-2">

                                <div class="col-md-6"><label class="labels">Name</label><input type="text" class="form-control" placeholder="first name"  name="userName" value="<%=userF.getUserName()%>"></div>
                                <input type="hidden"   name="userID" value="<%=userF.getUserID()%>">
                                <%
                                    String surname = userF.getSurName();
                                    if (userF.getSurName() == null) {
                                        surname = "";
                                    }
                                %>

                                <div class="col-md-6"><label class="labels">Surname</label><input type="text" class="form-control" name="surName" value="<%=surname%>"></div>
                                <div class="col-md-6"><label class="labels">Title</label>
                                    <select  class="form-control" name="title">
                                        <option>MR</option>
                                        <option>Miss</option>
                                    </select>
                                </div>
                            </div>

                            <div class="row mt-3">
                                <div class="col-md-12"><label class="labels">Mobile Number</label><input type="text" class="form-control" placeholder="enter phone number" name="telephone" value="<%=userF.getTelephoneNumber()%>"></div>
                                <div class="col-md-12"><label class="labels">Street</label><input type="text" class="form-control" placeholder="enter address line 1" name="street" value="<%=userF.getAddress().getStreet()%>"></div>
                                <div class="col-md-12"><label class="labels">Suburb</label><input type="text" class="form-control" placeholder="enter address line 2" name="suburb" value="<%=userF.getAddress().getSuburb()%>"></div>
                                <div class="col-md-12"><label class="labels">code</label><input type="text" class="form-control" placeholder="enter address line 3" name="code" value="<%=userF.getAddress().getCode()%>"></div>
                            </div>

                            <input type="hidden" name="action" value="editUser">
                            <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="submit">Save Profile</button></div>
                        </form>
                    </div>
                </div>  

            </div>
        </div>

    </body>
    <script> function closeForm() {
            var popup = document.getElementById("popup");
            popup.style.display = "none";
        }
        var message = '<%=request.getAttribute("message")%>';
        console.log(message);
        if (message !== 'null' && message !== '') {
            var messageContainer = document.getElementById("messageContainer");
            messageContainer.innerHTML = message;
            var popup = document.getElementById("popup");
            popup.style.display = "block";
        }</script>
</html>