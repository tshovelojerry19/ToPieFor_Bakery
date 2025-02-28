package com.topiefor.processservice;

import com.topiefor.controller.ProcessRequest;
import com.topiefor.dao.CategoryDao;
import com.topiefor.models.Category;
import com.topiefor.service.impl.CategoryServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class processTheCategoryRequest implements ProcessRequest{
    private String page;
    private CategoryServiceImpl categoryServiceImpl;

    public processTheCategoryRequest(CategoryDao categoryDao) {
        categoryServiceImpl = new CategoryServiceImpl(categoryDao);
    }
    
    
      @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {

        //Category category = null;
        List<Category> allCategories = null;
        String categoryAction = request.getParameter("action");
         
        String categoryName;
        String categoryStatus;
        boolean flagStatus = false;
        int categoryID = 0;

        if (categoryAction != null) {
            switch (categoryAction.trim()) {
                case "add":
                    categoryName = request.getParameter("categoryName");
                    if (categoryName != null && !categoryName.isEmpty()) {
                        categoryName = categoryName.trim();
                        if (categoryServiceImpl.addCategory(new Category(0, categoryName, true))) {
                            request.setAttribute("allCategories", new ArrayList<>(categoryServiceImpl.getAllCategories()));
                            request.setAttribute("message", "Category added!");
                        } else {
                            request.setAttribute("message", "category not Added");
                        }

                        page = "Admin/CategoryPage.jsp";
                    }

                    break;

                //---------------------------------------------------------------------------------------------------------
                case "edit":

                    categoryName = request.getParameter("categoryName");
                    categoryStatus = request.getParameter("status");
                    if (categoryName != null) {
                        try {
                            categoryID = Integer.parseInt(request.getParameter("categoryID"));
                        } catch (NumberFormatException ex) {
                            System.out.println("error" + ex.getMessage());
                        }
                        if (!categoryName.isEmpty() && categoryID > 0) {
                            if (categoryServiceImpl.editCategory(new Category(categoryID, categoryName, flagStatus))) {
                                request.setAttribute("allCategories", new ArrayList<>(categoryServiceImpl.getAllCategories()));
                                request.setAttribute("message", "Category Updated!");
                            } else {
                                request.setAttribute("message", "Category not Updated!");
                            }
                        }

                    }
                    page = "Admin/CategoryPage.jsp";
                    break;
                //---------------------------------------------------------------------------------------------------------
                case "get":
                    allCategories = categoryServiceImpl.getAllCategories();
                    if (allCategories != null && !allCategories.isEmpty()) {

                        request.setAttribute("allCategories", allCategories);
                    } else {
                        request.setAttribute("response", "no data");
                    }
                    page = "Admin/CategoryPage.jsp";
                    break;

                //------------------------------------------------------------------------------------
                case "delete":

                    categoryName = request.getParameter("categoryName");
                    categoryStatus = request.getParameter("status");
                   
                    if (categoryStatus != null && !categoryStatus.isEmpty()) {
                       
                        categoryStatus = categoryStatus.trim();
                        flagStatus = checkFlag(categoryStatus, flagStatus, request);
                    }
                    try {
                        categoryID = Integer.parseInt(request.getParameter("categoryID").trim());
                    } catch (NumberFormatException ex) {
                        System.out.println("error " + ex.getMessage());
                    }
                    if (categoryName != null && !categoryName.isEmpty() && categoryID > 0) {
                        categoryName = categoryName.trim();
                        if (categoryServiceImpl.deleteCategory(new Category(categoryID, categoryName, flagStatus))) {
                            request.setAttribute("allCategories", new ArrayList<>(categoryServiceImpl.getAllCategories()));
                            request.setAttribute("message", "Category Status Updated");
                        } else {
                            request.setAttribute("reponse", "category deleted");
                        }
                    }
                    page = "Admin/CategoryPage.jsp";
                    break;

                default:
                    request.setAttribute("response", "something went wrong");
                    break;
            }

        }
    }

    private boolean checkFlag(String categoryStatus, boolean flagStatus, HttpServletRequest request) {
        switch (categoryStatus) {
            case "Activate":
                flagStatus = true;
                break;
            case "Deactivate":
                flagStatus = false;
                break;
            default:
                request.setAttribute("error", "error");
                break;
        }
        return flagStatus;
    }

    @Override
    public void processTheResponse(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        try {
            requestDispatcher.forward(request, response);

        } catch (ServletException | IOException ex) {
            System.err.println("Error Dispatching View: " + ex.getMessage());
        }
    }

}
