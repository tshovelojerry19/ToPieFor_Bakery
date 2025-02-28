<%@page import="com.topiefor.models.User"%>
<%@page import="java.text.DecimalFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User user = (User) request.getSession().getAttribute("user");

    if (user != null) {
        request.setAttribute("user", user);
    }

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
            .reset {
                color: white;
                display: flex;
                flex-direction: column;
                align-items: center; /* Centers the div horizontally */
                justify-content: center; /* Centers the div vertically */
                height: 50vh; /* Set the desired height of the reset div */
                width: 30vw;
                background-color: rgb(0,0,0,.3);
                margin-left: 35%;
                margin-top: 5%;
            }

            .content {
                margin-top: 20px; /* Adds spacing above the form content */
            }

            .text-left {
                margin-top: 0; /* Remove the default margin from the heading */
            }
            input{
                margin-top: 5%;
                margin-bottom:  15%;
            }
            .button{
                margin-left: 7%;
                margin-top: 5%;
            }
        </style>
    </head>
    <body>
        <div class="nav" >
            <!--top left welcome-->
            <h3 class="logo" style="margin-left: 100px; margin-top: 2%; bold">Profile</h3>
            <!--header list top right-->
            <ul>
                <li><a  href="IndexController"><button class="btn">Home</button></a></li>
                <li><a href="LoginController?action=logout" class="btn">Login</a></li>
            </ul>
        </div>
        <div class="reset">
            <h4 class="text-left">Password Reset</h4>
            <div class="content">
                <form action="UserController" method="POST">
                    <label class="labels">New Password</label>
                    <input type="password" class="form-control" placeholder="Enter new Password" name="password" id="password">

                    <input type="hidden" name="action" value="resetPassword">
                    <div class="button"> <button class="btn btn-primary profile-button" type="submit">Reset Password</button></div>
                </form>
            </div>
        </div>
    </body>

</html>
<script>

</script>