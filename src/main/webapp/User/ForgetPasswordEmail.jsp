<%@page import="com.topiefor.models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="UserCSS/CodeVerificationPageCSS.css" type="text/css" rel="stylesheet" />
        <script src="UserJS/CodeVerificationPageJS.js"></script>

        <title>JSP Page</title>

        <style>
            body{
                margin: 0;
                padding: 0;
                background-image: url(https://wallpaperaccess.com/full/1892454.jpg);
                background-size: cover;
                background-repeat: no-repeat;
                font-family : Verdana,Tahoma, sans-serif;
            }

            .box{
                width: 50vw;

                background-color:#d6cfc7;
                padding: 20px;
                margin: 8% auto 0;
                text-align: center;
                position: relative;
                box-sizing: border-box;
                flex-direction: column;
            }





            #errorMsg{
                color:red;
                text-align: center;
                font-size: 12px;
                padding-bottom: 20px;

            }

            .content{
                display: inline-flex;
                overflow: hidden;
            }

            form {
                position: relative;
                display: inline-flex;
                width: 100%;
                height: 100%;
                flex-shrink: 0;
                flex-direction: column;
                transition: right 0.5s;
            }

            .login a{
                text-decoration: none;
                color: #ffffff;
                font-size: 15px;
                text-align: center;
                cursor: pointer;

            }

            .extend form {
                right: 100%;
            }

            input[type=text], input[type=password], input[type=email]{
                display:block;
                position: relative;
                border: 4px solid #ffffff;
                padding: 12px;
                margin-bottom: 15px;
                width: 40vw;
                box-sizing: border-box;
                font-size: 17px;
                outline: none;
            }

            input[type=text]:hover,  input[type=password]:hover, input[type=email]:hover{
                border: 4px solid #877f7d;

            }

            input[type=submit]{
                display:block;
                position: relative;
                border: 3px solid #828282;
                padding: 12px;
                margin-bottom: 20px;
                margin-left: 40%;
                width: 5em;
                border-radius: 25px;
                background-color:#363636 ;
                text-align:center;
                font-size: 20px;
                color: #ffffff;
                cursor:pointer;
                outline: none;
            }



        </style>
    <body>
       
        <div class="box">

            <div class="page">

                <div id="errorMsg"></div>
                <div class="content">

                    <!--Login Form-->
                    <form  action="/ToPieFor/UserController" class="login" name="loginForm"  method="POST" >
                        <input type="text" name="email" id="signEmail" placeholder="Enter Email" required>
                        <input type="hidden" name="action" value="forgetPasswordEmail">
                        <input type="submit" value="verify">
                        <a href="#">Login</a>
                    </form>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
        <script src="index.js"></script>
    </body>
</html>
