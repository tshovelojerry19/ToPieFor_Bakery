package com.topiefor.processservice;

import com.topiefor.controller.ProcessRequest;
import com.topiefor.dao.IngredientDao;
import com.topiefor.dao.impl.UnitDaoImpl;
import com.topiefor.database.manager.DatabaseManager;
import com.topiefor.models.Ingredient;
import com.topiefor.models.Unit;
import com.topiefor.service.UnitService;
import com.topiefor.service.impl.IngredientServiceImpl;
import com.topiefor.service.impl.UnitServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessTheIngredientRequest implements ProcessRequest {

    private String page;
    private IngredientServiceImpl ingredientServiceImpl;

    public ProcessTheIngredientRequest(IngredientDao IngredientDao) {
        ingredientServiceImpl = new IngredientServiceImpl(IngredientDao);
    }

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {
        allUnits(request);
        List<Ingredient> allIngredients = null;
        String ingredientAction = request.getParameter("action");

        String ingredientName;
        String ingredientStatus;
        int ingredientID = 0;
        int ingredientQuantity = 0;
        int ingredientUnitID = 0;
        String ingredientIDs;
        String ingredientQuantitys;
        String ingredientUnitIDs;

        boolean flagStatus = false;
        if (ingredientAction != null) {
            switch (ingredientAction.trim()) {
                case "add":

                    ingredientName = request.getParameter("ingredientName");
                    ingredientQuantitys = request.getParameter("ingredientQuantity");
                    ingredientUnitIDs = request.getParameter("ingredientUnitID");
                    if (ingredientName != null && !ingredientName.isEmpty()
                            && ingredientQuantitys != null && !ingredientQuantitys.isEmpty()
                            && ingredientUnitIDs != null && !ingredientUnitIDs.isEmpty()) {

                        ingredientName = ingredientName.trim();
                        ingredientQuantitys = ingredientQuantitys.trim();
                        ingredientUnitIDs = ingredientUnitIDs.trim();
                        try {
                            ingredientUnitID = Integer.parseInt(ingredientUnitIDs);
                            ingredientQuantity = Integer.parseInt(ingredientQuantitys);
                        } catch (NumberFormatException ex) {
                            System.out.println("error" + ex.getMessage());
                        }
                        if (ingredientServiceImpl.addIngredient(new Ingredient(0, ingredientName, ingredientQuantity, new Unit(ingredientUnitID), true))) {
                            request.setAttribute("allIngredients", new ArrayList<>(ingredientServiceImpl.getAllIngredient()));
                            request.setAttribute("message", "Ingredient added!");
                            page = "Admin/IngredientsPage.jsp";
                        } else {
                            request.setAttribute("message", "Ingredient not Added");
                            page = "Admin/IngredientsPage.jsp";

                        }
                    } else {
                        page = "Admin/ErrorPage.jsp";

                    }

                    break;

                //---------------------------------------------------------------------------------------------------------
                case "edit":

                    ingredientName = request.getParameter("ingredientName");
                    ingredientQuantitys = request.getParameter("ingredientQuantity");
                    ingredientUnitIDs = request.getParameter("ingredientUnitID");
                    ingredientIDs = request.getParameter("ingredientID");

                    if (ingredientName != null && !ingredientName.isEmpty()
                            && ingredientQuantitys != null && !ingredientQuantitys.isEmpty()
                            && ingredientUnitIDs != null && !ingredientUnitIDs.isEmpty()
                            && ingredientIDs != null && !ingredientIDs.isEmpty()) {
                        ingredientName = ingredientName.trim();
                        ingredientQuantitys = ingredientQuantitys.trim();
                        ingredientUnitIDs = ingredientUnitIDs.trim();
                        ingredientIDs = ingredientIDs.trim();
                        try {
                            ingredientUnitID = Integer.parseInt(ingredientUnitIDs);
                            ingredientQuantity = Integer.parseInt(ingredientQuantitys);
                            ingredientID = Integer.parseInt(ingredientIDs);
                        } catch (NumberFormatException ex) {
                            System.out.println("error" + ex.getMessage());
                        }
                        if (ingredientID > 0 && ingredientQuantity > 0) {
                            if (ingredientServiceImpl.editIngredient(new Ingredient(ingredientID, ingredientName, ingredientQuantity, (new Unit(ingredientUnitID))))) {
                                request.setAttribute("allIngredients", new ArrayList<>(ingredientServiceImpl.getAllIngredient()));
                                System.out.println("did edit");
                                request.setAttribute("message", "Ingredient Updated!");
                                page = "Admin/IngredientsPage.jsp";
                            } else {
                                System.out.println("not edited");
                                request.setAttribute("allIngredients", new ArrayList<>(ingredientServiceImpl.getAllIngredient()));
                                request.setAttribute("message", "Ingredient not Updated!");
                                page = "Admin/IngredientsPage.jsp";
                            }
                        }
                    } else {
                        page = "Admin/ErrorPage.jsp";
                    }
                    break;
                //---------------------------------------------------------------------------------------------------------
                case "getEditForm":

                    ingredientIDs = (String) request.getParameter("ingredientID");
                    if (ingredientIDs != null && !ingredientIDs.isEmpty()) {
                        ingredientIDs = ingredientIDs.trim();
                        try {
                            ingredientID = Integer.parseInt(ingredientIDs);
                            Ingredient ingredient = ingredientServiceImpl.getIngredientByIngredientID(ingredientID);
                            if (ingredient != null) {
                                request.setAttribute("ingredient", ingredient);
                                page = "Admin/IngredientsPage.jsp";
                            } else {
                                request.setAttribute("message", "ingredient not found");
                                page = "Admin/IngredientsPage.jsp";
                            }

                        } catch (NumberFormatException ex) {
                            System.out.println("cant convert" + ex.getMessage());
                            page = "Admin/ErrorPage.jsp";
                        }
                    } else {
                        page = "Admin/ErrorPage.jsp";
                        break;
                    }

                case "get":
                    allIngredients = ingredientServiceImpl.getAllIngredient();
                    if (allIngredients != null && !allIngredients.isEmpty()) {

                        request.setAttribute("allIngredients", allIngredients);
                    } else {
                        request.setAttribute("response", "no data");
                    }
                    page = "Admin/IngredientsPage.jsp";
                    break;

                case "getStock":
                    allIngredients = ingredientServiceImpl.getAllIngredient();
                    if (allIngredients != null && !allIngredients.isEmpty()) {

                        request.setAttribute("allIngredients", allIngredients);
                    } else {
                        request.setAttribute("response", "no data");
                    }
                    page = "Admin/IngredientStockPage.jsp";
                    break;

                //------------------------------------------------------------------------------------
                case "delete":

                    ingredientStatus = request.getParameter("ingredientStatus");
                    if (ingredientStatus != null && !ingredientStatus.isEmpty()) {

                        ingredientStatus = ingredientStatus.trim();
                        flagStatus = checkFlag(ingredientStatus, request);
                        if (request.getParameter("ingredientID") != null && !request.getParameter("ingredientID").isEmpty()) {
                            try {
                                ingredientID = Integer.parseInt(request.getParameter("ingredientID").trim());
                            } catch (NumberFormatException ex) {
                                System.out.println("error " + ex.getMessage());
                            }
                        }

                        if (ingredientServiceImpl.deleteIngredient(new Ingredient(ingredientID, flagStatus))) {
                            request.setAttribute("allIngredients", new ArrayList<>(ingredientServiceImpl.getAllIngredient()));
                            request.setAttribute("message", "Ingredient status changed");

                        } else {
                            request.setAttribute("reponse", "status not changed");
                        }
                    }
                    page = "Admin/IngredientsPage.jsp";
                    break;

                default:
                    request.setAttribute("response", "something went wrong");
                    break;
            }

        }
    }
//    

    private void allUnits(HttpServletRequest request) {
        ServletContext sc = request.getServletContext();
        DatabaseManager databaseManager = (DatabaseManager) sc.getAttribute("dbman");
        UnitService unitService = new UnitServiceImpl(UnitDaoImpl.getInstance(databaseManager.getConnection()));
        request.setAttribute("allUnits", new ArrayList<>(unitService.getAllUnits()));
    }

    private boolean checkFlag(String ingredientString, HttpServletRequest request) {
        boolean flagStatus = false;
        switch (ingredientString) {

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
