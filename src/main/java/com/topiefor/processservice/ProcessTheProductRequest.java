package com.topiefor.processservice;

import com.topiefor.controller.ProcessRequest;
import com.topiefor.dao.ProductDao;

import com.topiefor.dao.impl.CategoryDaoImpl;

import com.topiefor.dao.impl.RecipeDaoImpl;
import com.topiefor.database.manager.DatabaseManager;
import com.topiefor.models.Category;
import com.topiefor.models.Product;
import com.topiefor.models.Recipe;
import com.topiefor.service.CategoryService;
import com.topiefor.service.RecipeService;
import com.topiefor.service.impl.CategoryServiceImpl;
import com.topiefor.service.impl.ProductServiceImpl;
import com.topiefor.service.impl.RecipeServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessTheProductRequest implements ProcessRequest {

    private String page;
    private ProductServiceImpl productServiceImpl;
    private int productID = 0;
    private String productName;
    private String productStatus;
    private String productAllergies;
    private boolean flagStatus = false;
    private String productImage;
    private double productPrice = 0;
    private int recipeID = 0;
    private int categoryID = 0;
    private String recipe;
    private String category;
    private String productPriceS;
    private String productIDS;

    public ProcessTheProductRequest(ProductDao productDao) {
        productServiceImpl = new ProductServiceImpl(productDao);
    }

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {

        allRecipies(request);
        allCategories(request);

        List<Product> allProducts = null;
        String productAction = request.getParameter("action");

        // string from request
        if (productAction != null) {
            switch (productAction.trim()) {
                case "add":

                    if (operation(request)) {
                        try {
                            recipeID = Integer.parseInt(recipe);
                            categoryID = Integer.parseInt(category);
                            productPrice = Double.parseDouble(productPriceS);

                        } catch (NumberFormatException ex) {
                            System.out.println("error " + ex.getMessage());
                        }

                        if (recipeID > 0 && categoryID > 0 && productPrice > 0) {

                            if (productServiceImpl.addProduct(new Product(0, productName, productPrice, productAllergies, new Recipe(recipeID), new Category(categoryID), true, productImage))) {
                                request.setAttribute("allProducts", new ArrayList<>(productServiceImpl.getAllProducts()));
                                request.setAttribute("message", "product added!");
                            } else {
                                request.setAttribute("message", "product not Added");
                            }
                            page = "Admin/AddProductPage.jsp";
                            break;
                        }
                    }
                //---------------------------------------------------------------------------------------------------------

                case "edit":
                    productIDS = request.getParameter("productID");
                    if (operation(request)) {
                        if (productIDS != null && !productIDS.isEmpty()) {
                            try {
                                productID = Integer.parseInt(productIDS);
                                recipeID = Integer.parseInt(recipe);
                                categoryID = Integer.parseInt(category);
                                productPrice = Double.parseDouble(productPriceS);
                            } catch (NumberFormatException ex) {
                                System.out.println("error " + ex.getMessage());
                            }
                        }
                        if (productID > 0 && recipeID > 0 && categoryID > 0 && productPrice > 0) {

                            if (productServiceImpl.editProduct(new Product(productID, productName, productPrice, productAllergies, new Recipe(recipeID), new Category(categoryID), true, productImage))) {
                                request.setAttribute("allProducts", new ArrayList<>(productServiceImpl.getAllProducts()));
                                request.setAttribute("message", "product edited!");
                            } else {
                                request.setAttribute("message", "product not edited");
                            }
                            page = "Admin/AddProductPage.jsp";
                            break;
                        }
                    }
                //---------------------------------------------------------------------------------------------------------
                case "get":
                    allProducts = productServiceImpl.getAllProducts();
                    if (allProducts
                            != null && !allProducts.isEmpty()) {

                        request.setAttribute("allProducts", allProducts);
                    } else {
                        request.setAttribute("response", "no data");
                    }
                    page = "Admin/ProductPage.jsp";
                    break;

                //------------------------------------------------------------------------------------
                case "delete":

                    productStatus = request.getParameter("status");
                    productIDS = request.getParameter("productID");
                   
                        if (productIDS != null && !productIDS.isEmpty() && productStatus != null && !productStatus.isEmpty()) {
                            productStatus = productStatus.trim();
                            checkFlag(productStatus, request);
                            
                        }
                        try {
                            productID = Integer.parseInt(productIDS);
                           
                        } catch (NumberFormatException ex) {
                            System.out.println("error " + ex.getMessage());
                        }
                    
                    if (productServiceImpl.productAvailability(new Product(productID, flagStatus))) {
                    
                        request.setAttribute("allProducts", new ArrayList<>(productServiceImpl.getAllProducts()));
                         request.setAttribute("message", "product De/Activated!");
                    } else {
                        request.setAttribute("message", "Product De/Activated");
                    }

                    page = "Admin/ProductPage.jsp";
                    break;

                case "addForm":
                    page = "Admin/AddProductPage.jsp";
                    break;
                case "editForm":
                    productIDS = request.getParameter("productID");
                    if (operation(request)) {

                        try {
                            productID = Integer.parseInt(productIDS);
                            productPrice = Double.parseDouble(productPriceS);

                        } catch (NumberFormatException ex) {
                            System.out.println("error no now" + ex.getMessage());
                        }

                        if (productID > 0) {

                            Product product = new Product(productID, productName, productPrice, productAllergies, new Recipe(recipe), new Category(category), true, productImage);

                            request.setAttribute("product", product);

                        } else {

                            page = "Admin/ErrorPage.jsp";
                        }
                        page = "Admin/AddProductPage.jsp";
                        break;

                    }

                default:
                    request.setAttribute("response", "something went wrong");
                    break;
            }
        }
    }

    public boolean checkForNulls() {
        return productName != null && !productName.isEmpty()
                && productAllergies != null && !productAllergies.isEmpty()
                && productImage != null && !productImage.isEmpty()
                && productPriceS != null && !productPriceS.isEmpty()
                && recipe != null && !recipe.isEmpty()
                && category != null && !category.isEmpty();

    }

    private void allRecipies(HttpServletRequest request) {
        ServletContext sc = request.getServletContext();
        DatabaseManager databaseManager = (DatabaseManager) sc.getAttribute("dbman");
        RecipeService recipeService = new RecipeServiceImpl(RecipeDaoImpl.getInstance(databaseManager.getConnection()));
        request.setAttribute("allRecipies", new ArrayList<>(recipeService.getAllRecipies()));

    }

    private void allCategories(HttpServletRequest request) {
        ServletContext sc = request.getServletContext();
        DatabaseManager databaseManager = (DatabaseManager) sc.getAttribute("dbman");
        CategoryService categoryService = new CategoryServiceImpl(CategoryDaoImpl.getInstance(databaseManager.getConnection()));
        request.setAttribute("allCategories", new ArrayList<>(categoryService.getAllCategories()));

    }

    private void checkFlag(String categoryStatus, HttpServletRequest request) {

        switch (categoryStatus) {
            case "Show":
                flagStatus = true;
                break;
            case "DontShow":
                flagStatus = false;
                break;
            default:
                request.setAttribute("error", "error");
                break;
        }
       
    }

    @Override
    public void processTheResponse(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        try {
            requestDispatcher.forward(request, response);

        } catch (ServletException | IOException ex) {
            System.out.println("Error Dispatching View: " + ex.getMessage());
        }
    }

    public boolean operation(HttpServletRequest request) {
        productName = request.getParameter("productName");
        productAllergies = request.getParameter("productAllergies");
        productImage = request.getParameter("productImage");
        productPriceS = request.getParameter("productPrice");
        recipe = request.getParameter("productRecipe");
        category = request.getParameter("productCategory");

        if (checkForNulls()) {

            productName = productName.trim();
            productAllergies = productAllergies.trim();
            productImage = productImage.trim();
            productPriceS = productPriceS.trim();
            recipe = recipe.trim();
            category = category.trim();

            return true;
        }
        return false;
    }
}