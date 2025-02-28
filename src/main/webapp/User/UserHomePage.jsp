<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.DecimalFormat"%>
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
    DecimalFormat dcf = new DecimalFormat("#.##");
%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="UserCSS/UserHomePageCSS.css" type="text/css" rel="stylesheet" />
        <script src="UserJS/AddUserPageJS.js"></script>

        <%@include file="head.jsp"%>
        <title>To pie For</title>

        <style>

        </style>
    </head>
    <body>
        <div class="nav" style="background-color:#E6CDAE; display: flex; justify-content: center; align-items: center;">
            <!--top left welcome-->
            <h3 class="logo" style="color: white; margin-left: 100px; font-weight: bold;">Welcome</h3>

            <!--header list top right-->
            <ul style="display: flex; list-style: none; margin-left: auto;">
                <li>
                    <a href="IndexController">
                        <button class="btn">Home</button>
                    </a>
                </li>
                <li>
                    <button class="btn" onclick="document.getElementById('cartModal').style.display = 'block'">
                        Cart <span class="badge badge-danger">${cart_list.size()}</span>
                    </button>
                </li>

                <%if (user == null) {%>
                <li>
                    <a href="User/LoginPage.jsp#signup" onclick="document.getElementById('signupModal').style.display = 'block'">
                        <button class="btn">Sign Up</button>
                    </a>
                </li>
                <li>
                    <a href="User/LoginPage.jsp#login" onclick="document.getElementById('loginModal').style.display = 'block'">
                        <button class="btn">Login</button>
                    </a>
                </li>
                <%}
                    if (user != null) {
                        if (user.getRole().name().equals("PUBLIC")) {%>
                <li>
                    <a href="UserController?action=editForm" class="btn">Profile</a>
                </li>
                <li>
                    <a href="OrderController?action=getOrder&userID=<%= user.getUserID()%>" class="btn">Orders</a>
                </li>
                <li>
                    <a href="LoginController?action=logout" class="btn">Logout</a>
                </li>
                <%}
                    if (user.getRole().name().equals("ADMIN")) {%>
                <li>
                    <a href="OrderReportController?action=get"<%= user.getUserID()%>" class="btn">Manage</a>
                </li>
                <li>
                    <a href="LoginController?action=logout" class="btn">Logout</a>
                </li>
                <%}
                    }%>
            </ul>
        </div>


        <!--cart modal-->
        <div id="cartModal" class="modalCart">
            <!--<span onclick="document.getElementById('cartModal').style.display = 'none'" >&times;</span>-->
            <!--on click close and pop up open-->

            <!--background colour-->
            <section class="h-100 h-custom" style="background-color: #4390a8;" >
                <div class="container py-5 h-100">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col">
                            <div class="card">
                                <div class="card-body p-4"  ">

                                    <div class="row">

                                        <!--shopping cart info fetched-->
                                        <div class="col-lg-7"style="overflow: auto; height: 100vh" >
                                            <!--back to main page button-->
                                            <h5 class="mb-3"><a style="color:black;" href="IndexController?action=get" class="text-body"><i
                                                        style="color:black;" class="fas fa-long-arrow-alt-left me-2"></i>Continue shopping</a></h5>
                                            <hr>

                                            <div class="d-flex justify-content-between align-items-center mb-4">
                                                <!--number of items in cart-->
                                                <div>
                                                    <%if (cart_list
                                                                != null && !cart_list.isEmpty()
                                                                && cart_list.size() > 0) {%>
                                                    <p class="mb-0">You have <%=cart_list.size()%> items in your cart</p>
                                                    <%}%>
                                                </div>
                                                <!--sort by button-->
                                                <!--<div>
                                                    <p class="mb-0"><span class="text-muted">Sort by:</span> 
                                                    <a class="text-body" style="color:black;">price <i class="fas fa-angle-down mt-1"></i></a></p>
                                                </div>-->
                                            </div>

                                            <div class="card mb-3" >
                                                <%
                                                    if (cart_list != null && !cart_list.isEmpty()) {
                                                        for (Product p : cart_list) {
                                                %>
                                                <!--card body display field of list-->
                                                <div class="card-body">
                                                    <div class="d-flex justify-content-between">
                                                        <!--section for image and name-->
                                                        <div class="d-flex flex-row align-items-center">
                                                            <!--image for list-->
                                                            <div>
                                                                <img src="Images/<%= p.getImage()%>" alt="Card image cap" class="img-fluid rounded-3" alt="Shopping item" style="width: 100px; height: 100px;">
                                                            </div>
                                                            <!--name of list-->
                                                            <div class="ms-3" style="padding-left: 15px; padding-top: 15px; margin-left: 50px; width: 130px;">
                                                                <h5><%=p.getName()%></h5>
                                                                <p class="small mb-0"></p>
                                                            </div>


                                                        </div>
                                                        <div class="form-group d-flex justify-content-between" style="margin-top: 35px;">
                                                            <a  class="btn btn-sm btn-decre" href="QuantityIncAndDec?ac=dec&id=<%=p.getProductID()%>"  style="height: 40px; width:40px; margin-right: 0px;margin-left: 30px;"><i class="fas fa-minus-square"></i></a>
                                                            <input style="width: 80px;height: 40px; margin-left: 5px; margin-right: 5px"type="text" name="quantity" class="form-control"  value="<%=p.getQuantity()%>" readonly> 
                                                            <a class="btn bnt-sm btn-incre" href="QuantityIncAndDec?&ac=inc&id=<%= p.getProductID()%>" style="height: 40px; width: 40px; margin-right: 0px;"><i class="fas fa-plus-square"></i></a> 
                                                        </div>
                                                        <!--section for quantity and price-->
                                                        <div class="d-flex flex-row align-items-center">

                                                            <div style="width: 90px;">
                                                                <label>Price:</label>
                                                                <h5 id="price_<%=p.getProductID()%>" class="mb-0">R<%= String.format("%.2f", p.getPrice() * p.getQuantity())%></h5>
                                                            </div>
                                                            <div style="mirgin-left: 80px;">
                                                                <a href="RemoveFromCartController?productID=<%=p.getProductID()%>" class="btn btn-sm btn-danger">X</a>
                                                            </div>
                                                        </div>

                                                    </div>

                                                </div>
                                                <%
                                                        }
                                                    }
                                                %>
                                            </div>
                                        </div>
                                        <!--Card body input field-->
                                        <div class="col-lg-5">
                                            <div class="card bg-info text-white rounded-3">
                                                <div class="card-body">
                                                    <!--card details-->
                                                    <div class="d-flex justify-content-between align-items-center mb-4">
                                                        <h5 class="mb-0" style="color:black" >Card details</h5>
                                                    </div>
                                                    <form class="mt-4">
                                                        <!--card holders name-->
                                                        <div class="form-outline form-white mb-4">
                                                            <label class="form-label" for="typeName" style="color:black" >Cardholder's Name</label>
                                                            <input type="text" id="typeName" class="form-control form-control-lg" size="17"
                                                                   placeholder="Cardholder's Name" />
                                                        </div>
                                                        <!--card number-->
                                                        <div class="form-outline form-white mb-4">
                                                            <label class="form-label" for="typeText" style="color:black" >Card Number</label>
                                                            <input type="text" id="typeText" class="form-control form-control-lg" size="17"placeholder="0000 0000 0000 0000" minlength="19" maxlength="19">
                                                        </div>

                                                        <div class="row mb-4">
                                                            <!--banking details-->
                                                            <div class="col-md-6">
                                                                <!--expiration of card-->
                                                                <div class="form-outline form-white">
                                                                    <input type="text" id="typeExp" class="form-control form-control-lg"
                                                                           placeholder="MM/YYYY" size="7" id="exp" minlength="7" maxlength="7" />
                                                                    <label class="form-label" for="typeExp" style="color:black" >Expiration</label>
                                                                </div>
                                                            </div>
                                                            <!--CVV for card-->
                                                            <div class="col-md-6">
                                                                <div class="form-outline form-white">
                                                                    <input type="password" id="typeText" class="form-control form-control-lg"
                                                                           placeholder="&#9679;&#9679;&#9679;" size="1" minlength="3" maxlength="3" />
                                                                    <label class="form-label" for="typeText" style="color:black" >Cvv</label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </form>

                                                    <hr class="my-4">
                                                    <!--total section-->
                                                    <div class="d-flex justify-content-between mb-4">
                                                        <%
                                                            if (cart_list != null) {
                                                                request.setAttribute("cart_list", cart_list);

                                                                double totalPrice = 0;
                                                                for (Product product : cart_list) {
                                                                    totalPrice += (product.getPrice() * product.getQuantity());
                                                                }

                                                        %>
                                                        <p class="mb-2" style="color:black" >Total</p>
                                                        <p class="mb-2"><span id="totalPrice" style="color:black"> R<%= String.format("%.2f", totalPrice)%></span></p>
                                                    </div>

                                                    <!--button to pay now-->
                                                    <button type="button" class="btn btn-info btn-block btn-lg"><a href="ChekOutController?action=getA">
                                                            <div class="d-flex justify-content-between">
                                                                <span><span id="payNowPrice" style="color:black">R<%= String.format("%.2f", totalPrice)%></span></span>
                                                                <span style="color:black">Pay Now <i class="fas fa-long-arrow-alt-right ms-2"></i></span>
                                                            </div></a>
                                                    </button>
                                                    <%
                                                        }
                                                    %>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </form>
    </div>
    <!--sign up modal-->
    <div id="signupModal" class="modal">

    </div>

    <!--login modal-->
    <div id="loginModal" class="modal">
  
    </div>

    <!--heading within image-->
    <div class="intro">
        <h2>TO PIE FOR</h2>
    </div>

    <div class="container">
        <!--category list displayed above products-->
        <div class="card-header my-3" style="background-color:#E6CDAE;">
            <a href="IndexController" style="color:black;">All Categories</a>
            <%
                Set<String> uniqueCategories = new HashSet<>();

                if (!products.isEmpty()) {
                    for (Product p : products) {
                        String category = p.getCategory().getName();
                        if (!uniqueCategories.contains(category)) {
                            uniqueCategories.add(category);
            %>
            <a href="#" onclick="sortProducts('<%= category%>')" style="color:black;" ><%= category%></a>
            <%
                        }
                    }
                } else {
                    out.println("There are no products");
                }
            %>
        </div>
        <!--products displayed with all info-->
        <div id="productGrid" class="row" style="background-color:#DBD8D5;">
            <% if (!products.isEmpty()) {
                    for (Product p : products) {
                        if (p.isStatus()) {
            %>
            <div class="col-md-3 my-3 category-card" data-category="<%= p.getCategory().getName()%>">
                <div class="card w-100" >
                    <img class="card-img-top" src="Images/<%= p.getImage()%>" alt="Card image cap">
                    <div class="card-body" style="background-color:	#E6CDAE;">
                        <h5 class="card-title"><%= p.getName()%></h5>
                        <h6 class="price" >Price: R<%= String.format("%.2f", p.getPrice())%></h6>


                        <button type="button" class="collapsible">See Allergies</button>
                        <div class="content">
                            <p><%= p.getAllergies()%></p>
                        </div>

                        <div class="mt-3 d-flex justify-content-between">
                            <form action="AddToCartController" method="POST">
                                <input type="hidden" name="productName" value="<%=p.getName()%>">
                                <input type="hidden" name="action" value="addToCart">
                                <input type="hidden" name="productPrice" value="<%=p.getPrice()%>">
                                <input type="hidden" name="productQuantity" value="<%=p.getQuantity()%>">
                                <input type="hidden"name="productID" value="<%=p.getProductID()%>">
                                <input type="hidden"name="productImage" value="<%=p.getImage()%>">
                                <input  class="btn" type="submit" value="Add to Cart">
                            </form>

                        </div>
                    </div>
                </div>
            </div>
            <% }
                    }
                } else {
                    out.println("There are no products");
                }%>
        </div>

    </div>

    <script>
//            sort js for products with category list
        function sortProducts(category) {
            const categoryCards = document.getElementsByClassName('category-card');
            for (let i = 0; i < categoryCards.length; i++) {
                const card = categoryCards[i];
                const cardCategory = card.getAttribute('data-category');
                if (cardCategory === category) {
                    card.style.display = 'block';
                } else {
                    card.style.display = 'none';
                }
            }
        }

        // Get the sign up modal
        var signupModal = document.getElementById('signupModal');
        // When the user clicks anywhere outside of the sign up modal, close it
        window.onclick = function (event) {
            if (event.target == signupModal) {
                signupModal.style.display = "none";
            }
        };

        // Get the login modal
        var loginModal = document.getElementById('loginModal');
        // When the user clicks anywhere outside of the login modal, close it
        window.onclick = function (event) {
            if (event.target == loginModal) {
                loginModal.style.display = "none";
            }
        };

        // Get the cart modal
        var cartModal = document.getElementById('cartModal');
        // When the user clicks anywhere outside of the login modal, close it
        window.onclick = function (event) {
            if (event.target == cartModal) {
                cartModal.style.display = "none";
            }
        };

//            update price on quantity click
        function updatePrice(input, productId, initialPrice) {
            var quantity = parseInt(input.value);
            var newPrice = quantity * initialPrice;
            document.getElementById("price_" + productId).textContent = newPrice.toFixed(2);

            // Recalculate the total price
            var total = 0;
            var prices = document.querySelectorAll("[id^=price_]");
            prices.forEach(function (price) {
                total += parseFloat(price.textContent);
            });
            document.getElementById("totalPrice").textContent = total.toFixed(2);
            document.getElementById("payNowPrice").textContent = total.toFixed(2);
        }


        var coll = document.getElementsByClassName("collapsible");
        var i;

        for (i = 0; i < coll.length; i++) {
            coll[i].addEventListener("click", function () {
                this.classList.toggle("active");
                var content = this.nextElementSibling;
                if (content.style.display === "block") {
                    content.style.display = "none";
                } else {
                    content.style.display = "block";
                }
            });
        }
        var open = <%=request.getAttribute("openCart")%>;
        console.log(open);
        if (open !== null && open !== '') {
            document.getElementById('cartModal').style.display = 'block';
        }
    </script>
</html>