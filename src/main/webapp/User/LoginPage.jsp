<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="AdminCSS/AddUserPageCSS.css" type="text/css" rel="stylesheet" />
        <script src="UserJS/LoginPageJS.js"></script>
        
        <title>Login Page</title>

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
                width: 380px;
                height: 800px;
                background-color:#d6cfc7;
                padding: 20px;
                margin: 8% auto 0;
                text-align: center;
                position: relative;
                box-sizing: border-box;
                flex-direction: column;
            }

            .box img{
                width:100px;
                margin-top:-50px ;
            }

            .header {
                width: 300px;
                height: 50px;
                text-transform: uppercase;
                display: inline-flex;
                padding-top: 20px;
                padding-bottom: 0;
                justify-content: space-between;
            }

            .header a {
                font-size: 20px;
                font-weight: bold;
                text-decoration: none;
                color: #48494b;
                display: inline-flex;
                padding-left: 25px;
                padding-right: 25px;
                justify-content: center;
                cursor:pointer;
                border: none;
            }

            .header .active {
                color: #48494b;
                font-weight: bold;
                position: relative;
                border: none;
            }

            .header .active:after {
                position: absolute;
                border: none;
                content: "";
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
                height: auto;
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
                width: 100%;
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
                width: 100%;
                border-radius: 25px;
                background-color:#363636 ;
                text-align:center;
                font-size: 20px;
                color: #ffffff;
                cursor:pointer;
                outline: none;
            }

            input[type=submit]:hover{
                background-color:#ffffff;
                color: #000000;
                font-weight: bold;
            }
            input[type=checkbox]{
                background-color:#df80ff;
            }
            #check{
                margin-top: 10px;
                text-align: left;
                color: #ffffff;
                font-size: 15px;
            }
        </style>
    <body>
        <div class="box">

            <img src="https://th.bing.com/th/id/R.89f59066dd3183cb465068d3e4269649?rik=z3SMKTw9FKtyPw&riu=http%3a%2f%2feshitadesai.com%2fwp-content%2fuploads%2f2017%2f06%2fProfile-ICon.png&ehk=xSGkDRzX5FK0lvKswGuTSoHwTzArHO3vw3iNXHSom%2b8%3d&risl=&pid=ImgRaw&r=0">
            <div class="page">
                <div class="header">
                    <a id="login" class="active" href="#login">login</a>
                    <a id="signup" href="#signup">signup</a>
                </div>
                <div id="errorMsg"></div>
                <div class="content">

                    <!--Login Form-->
                    <form  action="/ToPieFor/LoginController" class="login" name="loginForm" onsubmit="return validateLoginForm()" method="POST" >

                        <input type="email" name="email" id="signEmail" placeholder="Email" required>
                        <input type="hidden" name="action" value="login">
                        <input type="password" name="password" id="logPassword" placeholder="Password" required>
                        <br><br>
                        <input type="submit" value="Login">
                        <a href="ForgetPasswordEmail.jsp">Forgot Password?</a>
                    </form>

                    <!--SignUp Form-->
                    <form class="signup" name="signupForm" action="/ToPieFor/UserController" method="POST">
                        <input type="text" name="userName" id="signName" placeholder="Username" required>
                        <input type="email" name="email" id="signEmail" placeholder="Email" required>
                        <input type="text" name="telephone" id="telePhoneNumber" placeholder="Telephone Number" required>
                        <input type="hidden" name="action"  value="addU">
                        <input type="text" name="street" id="STAddress" placeholder="Street Address" required>
                        <input type="hidden" name="role"  value="PUBLIC">
                        <input type="text" name="suburb" id="Suburb" placeholder="Suburb" required>
                        <input type="text" name="code" id="Code" placeholder="Postal Code" required>
                        <input type="password" name="password" id="signPassword" placeholder="Password" required>
                        <!--<input type="password" name="rePassword" id="resignPassword" placeholder="Repeat-Password"><br>-->
                        <input type="submit" value="SignUp">
                    </form>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
        <script src="index.js"></script>
    </body>
</html>
<script>
                        var jQueryAlias = jQuery.noConflict();

                        jQueryAlias(window).on("hashchange", function () {
                            if (location.hash.slice(1) == "signup") {
                                jQueryAlias(".page").addClass("extend");
                                jQueryAlias("#login").removeClass("active");
                                jQueryAlias("#signup").addClass("active");
                            } else {
                                jQueryAlias(".page").removeClass("extend");
                                jQueryAlias("#login").addClass("active");
                                jQueryAlias("#signup").removeClass("active");
                            }
                        });

                        jQueryAlias(window).trigger("hashchange");

                        function validateLoginForm() {
                            var name = document.getElementById("logName").value;
                            var password = document.getElementById("logPassword").value;
                           

                            if (password.length < 8) {
                                document.getElementById("errorMsg").innerHTML = "Your password must include atleast 8 characters"
                                return false;
                            } else {
                                alert("Successfully logged in");
                                return true;
                            }
                        }
                        function validateSignupForm() {
                            var mail = document.getElementById("signEmail").value;
                            var name = document.getElementById("signName").value;
                            var password = document.getElementById("signPassword").value;


                            if (password.length < 8) {
                                document.getElementById("errorMsg").innerHTML = "Your password must include atleast 8 characters";
                                return false;
                            } else {
                                alert("Successfully signed up");
                                return true;
                            }
                        }
                        var message = '<%=request.getAttribute("message")%>';
                        console.log(message);
                        if (message !== 'null' && message !== '') {
                            document.getElementById("errorMsg").innerHTML = message;
                        }
</script>