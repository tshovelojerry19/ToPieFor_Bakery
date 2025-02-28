<%@page import="com.topiefor.models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Integer code = (Integer) request.getSession().getAttribute("vCode");
    if (code > 0) {
        request.setAttribute("vCode", code);
    }
    String reset = (String) request.getAttribute("reset");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="UserCSS/CodeVerificationPageCSS.css" type="text/css" rel="stylesheet" />
        <script src="UserJS/CodeVerificationPageJS.js"></script>

        <title>JSP Page</title>
        <style>
        </style>
    <body>
        <div class="box">

            <div class="page">

                <div id="errorMsg"></div>
                <div class="content">

                    <!--Login Form-->
                    <form  action="/ToPieFor/UserController" class="login" name="loginForm" onsubmit="return validateLoginForm()" method="POST" >
                        <input type="text" name="code" id="signEmail" placeholder="Verification code" required>
                        <input type="hidden" name="action" value="verifyCode">
                        <%if (reset != null && !reset.isEmpty()) {%><input type="hidden" name="reset" value="reset"><%}%>
                        <input type="submit" value=" Verify">
                        <a href="#">Back to Registration</a>
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

                        var message = '<%=request.getAttribute("message")%>';
                        console.log(message);
                        if (message !== 'null' && message !== '') {
                            document.getElementById("errorMsg").innerHTML = message;
                        }
</script>