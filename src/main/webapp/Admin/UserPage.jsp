<%@page import="java.util.List"%>
<%@page import="com.topiefor.models.User"%>
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
        <link href="Admin/Admin.css" type="text/css" rel="stylesheet" />
        <link href="AdminCSS/UserPageCSS.css" type="text/css" rel="stylesheet" />
        <script src="AdminJS/UserPageJS.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users Page</title>
    </head>
    <style>

    </style>
    <%-- -------------------------------------------------------------------------------------------------------------------------------------------- --%>
    <body>
        <!-----------------Scalable Vector Graphics----------------->
        <svg style="display:none;">

    <symbol id="pages" viewBox="0 0 16 16">
        <rect x="4" width="12" height="12" transform="translate(20 12) rotate(-180)" />
        <polygon points="2 14 2 2 0 2 0 14 0 16 2 16 14 16 14 14 2 14" />
    </symbol>/*Home*/

    <symbol id="users" viewBox="0 0 16 16">
        <path d="M8,0a8,8,0,1,0,8,8A8,8,0,0,0,8,0ZM8,15a7,7,0,0,1-5.19-2.32,2.71,2.71,0,0,1,1.7-1,13.11,13.11,0,0,0,1.29-.28,2.32,2.32,0,0,0,.94-.34,1.17,1.17,0,0,0-.27-.7h0A3.61,3.61,0,0,1,5.15,7.49,3.18,3.18,0,0,1,8,4.07a3.18,3.18,0,0,1,2.86,3.42,3.6,3.6,0,0,1-1.32,2.88h0a1.13,1.13,0,0,0-.27.69,2.68,2.68,0,0,0,.93.31,10.81,10.81,0,0,0,1.28.23,2.63,2.63,0,0,1,1.78,1A7,7,0,0,1,8,15Z" />
    </symbol>/*Users*/

    <symbol id="trends" viewBox="0 0 16 16">
        <polygon points="0.64 11.85 -0.02 11.1 2.55 8.85 4.57 10.4 9.25 5.25 12.97 8.84 15.37 6.79 16.02 7.54 12.94 10.2 9.29 6.68 4.69 11.76 2.59 10.14 0.64 11.85" />
    </symbol>/*Categories*/

    <symbol id="collection" viewBox="0 0 16 16">
        <rect width="7" height="7" />
        <rect y="9" width="7" height="7" />
        <rect x="9" width="7" height="7" />
        <rect x="9" y="9" width="7" height="7" />
    </symbol>/*Ingredients*/

    <symbol id="comments" viewBox="0 0 16 16">
        <path d="M0,16.13V2H15V13H5.24ZM1,3V14.37L5,12h9V3Z" />
        <rect x="3" y="5" width="9" height="1" />
        <rect x="3" y="7" width="7" height="1" />
        <rect x="3" y="9" width="5" height="1" />
    </symbol>/*Recipes*/



    <symbol id="settings" viewBox="0 0 16 16">
        <rect x="9.78" y="5.34" width="1" height="7.97" />
        <polygon points="7.79 6.07 10.28 1.75 12.77 6.07 7.79 6.07" />
        <rect x="4.16" y="1.75" width="1" height="7.97" />
        <polygon points="7.15 8.99 4.66 13.31 2.16 8.99 7.15 8.99" />
        <rect x="1.28" width="1" height="4.97" />
        <polygon points="3.28 4.53 1.78 7.13 0.28 4.53 3.28 4.53" />
        <rect x="12.84" y="11.03" width="1" height="4.97" />
        <polygon points="11.85 11.47 13.34 8.88 14.84 11.47 11.85 11.47" />
    </symbol>/*Products*/

    <symbol id="charts" viewBox="0 0 16 16">
        <polygon points="0.64 7.38 -0.02 6.63 2.55 4.38 4.57 5.93 9.25 0.78 12.97 4.37 15.37 2.31 16.02 3.07 12.94 5.72 9.29 2.21 4.69 7.29 2.59 5.67 0.64 7.38" />
        <rect y="9" width="2" height="7" />
        <rect x="12" y="8" width="2" height="8" />
        <rect x="8" y="6" width="2" height="10" />
        <rect x="4" y="11" width="2" height="5" />
    </symbol>/*Address to deliver*/

    <symbol id="appearance" viewBox="0 0 16 16">
        <path d="M3,0V7A2,2,0,0,0,5,9H6v5a2,2,0,0,0,4,0V9h1a2,2,0,0,0,2-2V0Zm9,7a1,1,0,0,1-1,1H9v6a1,1,0,0,1-2,0V8H5A1,1,0,0,1,4,7V6h8ZM4,5V1H6V4H7V1H9V4h1V1h2V5Z" />
    </symbol>/* */



    </svg>
    <%-- -------------------------------------------------------------------------------------------------------------------------------------------- --%>
    <header class="page-header">
        <nav>
            <%-- -------------------------------------------------------------------------------------------------------------------------------------------- --%>
            <ul class="admin-menu">
                <li>
                    <a href="IndexController">
                        <svg>
                        <use xlink:href="#pages"></use>
                        </svg>
                        <span>welcome Page</span>
                    </a>
                </li>
                <!-------------------------------Admin------------------------->
                <li class="menu-heading">
                    <h3>Admin</h3>
                </li>
                <!-------------------------------Home button------------------------->
                <li>
                    <a href="OrderReportController?action=get">
                        <svg>
                        <use xlink:href="#pages"></use>
                        </svg>
                        <span>Home</span>
                    </a>
                </li>
                <!-------------------------------Users button------------------------->
                <li>
                    <a href="UserController?action=get">
                        <svg>
                        <use xlink:href="#users"></use>
                        </svg>
                        <span>Users</span>
                    </a>
                </li>

                <%-- -------------------------------------------------------------------------------------------------------------------------------------------- --%>
                <!-------------------------------Assets------------------------->
                <li class="menu-heading">
                    <h3>Assets</h3>
                </li>
                <!-------------------------------ingredient button------------------------->
                <li>
                    <a href="CategoryController?action=get">

                        <svg>
                        <use xlink:href="#trends"></use>
                        </svg>
                        <span >Categories</span>
                    </a>
                </li>
                <!-------------------------------Ingredients button------------------------->
                <li>
                    <a href="IngredientController?action=get"  onclick="submitForm()">
                        <form id ="myForm" method="POST" action="UnitController">
                            <input type="hidden" name="action" value="get">
                        </form>
                        <svg>
                        <use xlink:href="#collection"></use>
                        </svg>

                        <span>Ingredients</span>
                    </a>

                </li>
                <!-------------------------------Recipe button------------------------->
                <li>
                    <a href="RecipesController?action=get">
                        <form id ="myForm" method="POST" action="IngredientController">
                            <input type="hidden" name="action" value="get">
                        </form>
                        <svg>
                        <use xlink:href="#comments"></use>
                        </svg>
                        <span>Recipes</span>
                    </a>
                </li>
                <li>
                    <a href="ProductController?action=get">
                        <svg>
                        <use xlink:href="#settings"></use>
                        </svg>
                        <span>Products</span>
                    </a>
                </li>
                <%-- -------------------------------------------------------------------------------------------------------------------------------------------- --%>
                <!-------------------------------Reports------------------------->
                <li class="menu-heading">
                    <h3>Reports</h3>
                </li>
                <!-------------------------------Report Pages------------------------->
                <li>
                    <a href="OrderController?action=get">
                        <svg>
                        <use xlink:href="#appearance"></use>
                        </svg>
                        <span>Orders</span>
                    </a>
                </li>
                <li>
                    <a href="OrderController?action=getBaked">
                        <svg>
                        <use xlink:href="#appearance"></use>
                        </svg>
                        <span>Delivery Orders</span>
                    </a>
                </li>
                <li>
                    <a href="OrderController?action=getDelivered">
                        <svg>
                        <use xlink:href="#appearance"></use>
                        </svg>
                        <span>Completed Orders</span>
                    </a>
                </li>
                <li>
                    <a href="IngredientController?action=getStock">
                        <svg>
                        <use xlink:href="#appearance"></use>
                        </svg>
                        <span>Stock Shortage</span>
                    </a>
                </li>
                <li>
                    <a href="UserController?action=getActive">
                        <svg>
                        <use xlink:href="#appearance"></use>
                        </svg>
                        <span>Active Users</span>
                    </a>
                </li>
                <li>
                    <a href="#0">
                    </a>
                </li>
            </ul>
        </nav>
    </header>
    <%-- -------------------------------------------------------------------------------------------------------------------------------------------- --%>
    <section class="page-content">
        <!--add a user button and pop up-->
        <div class="topnav" style="">
            <h2>Add a User</h2>
            <span>
                <a href="UserController?action=addForm">
                    <button class="buttonTable">Add new User</button>
                </a>
            </span>
        </div>
        <br>
        <!--main body content with table and search button and sort buttons-->
        <div class="topnavMain" style="">
            <h2>All Users in the database</h2><br>
            <label><b>Sort by:</b></label>
            <p>
                <span>
                    <button onclick="sortTableAlphabeticalUsername()" class="buttonTable">Username</button>
                </span>
                <span>
                    <button onclick="sortTableAlphabeticalSurname()" class="buttonTable">Surname</button>
                </span>
                <span>
                    <button onclick="sortTableAlphabeticalEmail()" class="buttonTable">Email</button>
                </span>
                <span>
                    <button onclick="sortTableActive()" class="buttonTable">Active</button>
                </span>
                <span>
                    <button onclick="sortTableInactive()" class="buttonTable">Inactive</button>
                </span><br><br>
                <label><b>Search by UserName:</b></label>
                <br>
                <input id="myInput" type="text" placeholder="" onkeyup="mySearchFunction()" class="search">

            </p>
            <br> 

            <table style="width:75%" id="myTable">
                <tr>
                    <th>Username</th>
                    <th>Surname</th>
                    <th>Telephone</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Status</th>
                    <th>Edit/Update</th>
                    <th>De/Activate</th>
                </tr>

                <%
                    String status;

                    List<User> userList = (List<User>) request.getAttribute("allUsers");
                    //Collections.sort(ingredientList);
                    if (userList != null && !userList.isEmpty()) {
                        for (User i : userList) {

                            int userID = i.getUserID();
                            String username = i.getUserName();
                            String surname = i.getSurName();
                            String title = i.getTitle();
                            String telephone = i.getTelephoneNumber();
                            String email = i.getEmail();
                            String role = i.getRole().name();

                            if (i.isStatus()) {
                                status = "Active";
                            } else {
                                status = "Inactive";
                            }


                %>

                <tr>
                <form name="editForm" action="UserController" method="POST">

                    <!--Add Form -->
                    <!--   NAME -->
                    <td>
                        <input id="editable" type="text" name="userNameName" value="<%=username%>" >
                    </td
                    <!--   SURNAME -->
                    <td>
                        <input id="editable" type="text" name="surName" value="<%=surname%>" >
                    </td>
                    <!--   telephone -->
                    <td>
                        <input id="editable" type="text" name="telephoneNumber" value="<%=telephone%>" >
                    </td>
                    <!--   email -->
                    <td>
                        <input id="editable" type="text" name="email" value="<%=email%>" >
                    </td>
                    <!--   role -->
                    <td>
                        <input id="editable" type="text" name="role" value="<%=role%>" >
                    </td>
                    <!--   Status    -->
                    <td>
                        <p ><%= status%></p>
                    </td>

                    <!--Edit form -->
                    <td>
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="userID" value="<%=userID%>">
                        <span>
                            <input type="submit" value="Update" class="buttonTable">
                        </span>
                    </td>
                </form>
                <%if (status.equals("Active")) {
                        status = "Deactivate";
                    } else {
                        status = "Activate";
                    }
                %>
                <!-- Delete form -->
                <td>
                    <form name="deleteForm" action="UserController" method="POST">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="userID" value="<%=userID%>">
                        <input type="hidden" name="userStatus" value="<%=status%>">
                        <span>
                            <input type="submit" value="<%=status%>" class="buttonTable" id="submit-button">
                        </span>
                    </form>


                </td>
                </tr> 

                <%
                        }
                    } else {

                        out.print("<tr><td colspan='4'>No data found.</td></tr>");
                    }
                %>
            </table>
            <div id="popup" class="form-container">
                <div class="con">
                    <div id="messageContainer"></div><br>
                    <button class="close-button" onclick="closeForm()" class="buttonTable">OK</button>
                </div>
            </div>
        </div>
    </section>
    <%-- -------------------------------------------------------------------------------------------------------------------------------------------- --%>
    <footer class="page-footer">
    </footer>
    <%-- -------------------------------------------------------------------------------------------------------------------------------------------- --%>
</section>
</body>
</html>
<script>
    function closeForm() {
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
    }

</script>