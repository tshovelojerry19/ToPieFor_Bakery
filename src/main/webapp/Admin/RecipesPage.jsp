<%@page import="com.topiefor.models.User"%>
<%@page import="com.topiefor.models.Recipe"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="com.topiefor.models.Category"%>
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
        <link href="AdminCSS/RecipesPageCSS.css" type="text/css" rel="stylesheet" />
        <script src="AdminJS/RecipePageJS.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recipe Page</title>

        <style>
            .popuppop.scrollable {
                max-height: 600px; /* Set the maximum height of the content */
                overflow-y: auto; /* Enable vertical scrollbar when content overflows */
            }
        </style>
    </head>
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
        <%-- -------------------------------------------------------------------------------------------------------------------------------------------- --%>
    </header>
    <section class="page-content">

        <%-- -------------------------------------------------------------------------------------------------------------------------------------------- --%>
        <!--Add recipe text and button--> 
        <div class="topnav" style="">
            <h2>Add Recipe</h2>
            <span>
                <a href="RecipesController?action=addForm"
                   <button class="buttonTable">Add new Recipe</button></a>
            </span>
        </div>
        <br>
        <!--main body content with table and search button and sort buttons-->
        <div class="topnavMain" style="">
            <h2>All Recipes in the database</h2>
            <br>
            <label><b>Sort by:</b></label>
            <br>
            <span>
                <button onclick="sortTableAlphabetical()" class="buttonTable">Alphabetical</button>
            </span>
            <span>
                <button onclick="sortTableActive()" class="buttonTable">Active</button></span>
            <span>
                <button onclick="sortTableInactive()" class="buttonTable">Inactive</button>
            </span>
            <br><br>
            <label><b>Search by Recipes</b></label>
            <br>
            <input id="myInput" type="text" placeholder="" onkeyup="mySearchFunction()" class="search">
            <br> 
            <br>
            <table style="width:75%" id="myTable">
                <tr>
                    <th>RecipeName</th>
                    <th>Instructions</th>
                    <th>Status</th>
                    <th>More Info</th>
                    <th>Edit/Update</th>
                    <th>De/Activate</th>

                </tr>

                <%
                    String stts;
                    String status;
                    List<Recipe> recipeList = (List<Recipe>) request.getAttribute("allRecipies");
                    //Collections.sort(recipeList);
                    if (recipeList != null && !recipeList.isEmpty()) {
                        for (Recipe r : recipeList) {
                            int recipeID = r.getRecipeID();
                            String recipe = r.getRecipeName();
                            String instruction = r.getInstruction();

                            if (r.isStatus()) {
                                status = "Active";
                            } else {
                                status = "Inactive";
                            }

                %>

                <tr>
                <form name="editForm" action="RecipesController" method="POST">
                    <!-- Recipe ID Recipe Name -->
                    <input type="hidden" value="<%=recipeID%>">

                    <!--   Recipe instructions -->
                    <td>
                        <input id="editable" type="text" name="recipeName" value="<%=instruction%>" >
                    </td>

                    <!--name-->
                    <td>
                        <input id="editable" type="text" name="instruction" value="<%=recipe%>" >
                    </td>

                    <!--   Status    -->
                    <td>
                        <p ><%= status%></p>
                    </td>

                    <!-- Info form -->
                    <td>
                        <div>
                            <a class="buttonTable" href="#popup<%= recipeID%>">show</a>
                        </div>

                        <div id="popup<%= recipeID%>" class="overlay">
                            <div class="popuppop scrollable">
                                <h2>Recipe</h2><br>
                                <a class="close" href="#">&times;</a>
                                <div class="content">
                                    <b><p>Recipe Name:</p></b>
                                    <p><%= instruction%></p><br>

                                    <b><p>Instruction:</p></b>
                                    <p><%= recipe%></p><br>

                                </div>
                            </div>
                        </div>
                    </td>


                    <!--Edit form -->
                    <td>
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="recipeID" value="<%=recipeID%>">
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
                    <form name="deleteForm" action="RecipesController" method="POST">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="recipeID" value="<%= recipeID%>">
                        <input type="hidden" name="recipeName" value="<%= recipe%>">
                        <input type="hidden" name="status" value="<%=status%>">
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
                <button class="btn" onclick="closeForm()" >OK</button>
            </div>
        </div>
        </div>
        <%-- -------------------------------------------------------------------------------------------------------------------------------------------- --%>
        <footer class="page-footer">
        </footer>
        <%-- -------------------------------------------------------------------------------------------------------------------------------------------- --%>
       
    </section>

</body>
</html>
<script>
    function submitForm() {
        document.getElementById("myForm").submit();
    }
    window.addEventListener('load', function () {
        document.documentElement.style.zoom = '90%';
    });

    // Initialize Variables
    var closePopup = document.getElementById("popupclose");
    var overlay = document.getElementById("overlay");
    var popup = document.getElementById("popup");
    var button = document.getElementById("button");
    // Close Popup Event
    closePopup.onclick = function () {
        overlay.style.display = 'none';
        popup.style.display = 'none';
    };
    // Show Overlay and Popup
    button.onclick = function () {
        overlay.style.display = 'block';
        popup.style.display = 'block';
    };

    //sort buttons for tables

    function sortTableAlphabetical() {
        var table, rows, switching, i, x, y, shouldSwitch;
        table = document.getElementById("myTable");
        switching = true;
        /*Make a loop that will continue until
         no switching has been done:*/
        while (switching) {
            //start by saying: no switching is done:
            switching = false;
            rows = table.rows;
            /*Loop through all table rows (except the
             first, which contains table headers):*/
            for (i = 1; i < (rows.length - 1); i++) {
                //start by saying there should be no switching:
                shouldSwitch = false;
                /*Get the two elements you want to compare,
                 one from current row and one from the next:*/
                x = rows[i].getElementsByTagName("TD")[0];
                y = rows[i + 1].getElementsByTagName("TD")[0];
                //check if the two rows should switch place:
                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    //if so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            }
            if (shouldSwitch) {
                /*If a switch has been marked, make the switch
                 and mark that a switch has been done:*/
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
            }
        }
    }

    //sort for active

    function sortTableInactive() {
        var table, rows, switching, i, x, y, shouldSwitch;
        table = document.getElementById("myTable");
        switching = true;
        /*Make a loop that will continue until
         no switching has been done:*/
        while (switching) {
            //start by saying: no switching is done:
            switching = false;
            rows = table.rows;
            /*Loop through all table rows (except the
             first, which contains table headers):*/
            for (i = 1; i < (rows.length - 1); i++) {
                //start by saying there should be no switching:
                shouldSwitch = false;
                /*Get the two elements you want to compare,
                 one from the current row and one from the next:*/
                x = rows[i + 1].getElementsByTagName("TD")[2];
                y = rows[i ].getElementsByTagName("TD")[2];
                //check if the two rows should switch place:
                if (x.innerHTML.toLowerCase() === "Active" && y.innerHTML.toLowerCase() !== "Active" ||
                        x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    //if so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            }
            if (shouldSwitch) {
                /*If a switch has been marked, make the switch
                 and mark that a switch has been done:*/
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
            }
        }
    }
    function sortTableActive() {
        var table, rows, switching, i, x, y, shouldSwitch;
        table = document.getElementById("myTable");
        switching = true;
        /*Make a loop that will continue until
         no switching has been done:*/
        while (switching) {
            //start by saying: no switching is done:
            switching = false;
            rows = table.rows;
            /*Loop through all table rows (except the
             first, which contains table headers):*/
            for (i = 1; i < (rows.length - 1); i++) {
                //start by saying there should be no switching:
                shouldSwitch = false;
                /*Get the two elements you want to compare,
                 one from the current row and one from the next:*/
                x = rows[i].getElementsByTagName("TD")[2];
                y = rows[i + 1].getElementsByTagName("TD")[2];
                //check if the two rows should switch place:
                if (x.innerHTML.toLowerCase() === "Active" && y.innerHTML.toLowerCase() !== "Active" ||
                        x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    //if so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            }
            if (shouldSwitch) {
                /*If a switch has been marked, make the switch
                 and mark that a switch has been done:*/
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
            }
        }
    }

    // search tab

    function mySearchFunction() {
        var input, filter, table, rows, i, txtValue;
        input = document.getElementById("myInput");
        filter = input.value.toUpperCase();
        table = document.getElementById("myTable");
        rows = table.rows;

        for (i = 1; i < rows.length; i++) {
            var cell = rows[i].getElementsByTagName("TD")[0]; // Assuming the username is in the first column (index 0)
            if (cell) {
                var usernameInput = cell.querySelector('input[name="recipeName"]');
                if (usernameInput) {
                    txtValue = usernameInput.value.toUpperCase();
                    if (txtValue.indexOf(filter) > -1) {
                        rows[i].style.display = "";
                    } else {
                        rows[i].style.display = "none";
                    }
                }
            }
        }

        sortTableBySearchVisibility();
    }

    function sortTableBySearchVisibility() {
        var table, rows, switching, i, x, y, shouldSwitch;
        table = document.getElementById("myTable");
        switching = true;

        while (switching) {
            switching = false;
            rows = table.rows;

            for (i = 1; i < rows.length - 1; i++) {
                shouldSwitch = false;
                x = rows[i].style.display;
                y = rows[i + 1].style.display;

                // Compare the display values
                if (x === "none" && y !== "none") {
                    shouldSwitch = true;
                    break;
                }
            }

            if (shouldSwitch) {
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
            }
        }
    }

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

//==========================de/activate hover========================================
    const submitButton = document.getElementById('submit-button');
    submitButton.addEventListener('click', function () {
        const currentValue = submitButton.value;
        if (currentValue === 'Activate') {
            submitButton.value = 'Deactivate';
        } else {
            submitButton.value = 'Activate';
        }
    });

</script>