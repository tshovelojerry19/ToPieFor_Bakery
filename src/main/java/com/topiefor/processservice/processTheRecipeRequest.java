package com.topiefor.processservice;

import com.topiefor.controller.ProcessRequest;
import com.topiefor.dao.RecipeDao;
import com.topiefor.dao.impl.IngredientDaoImpl;
import com.topiefor.dao.impl.UnitDaoImpl;
import com.topiefor.database.manager.DatabaseManager;
import com.topiefor.models.Ingredient;
import com.topiefor.models.Recipe;
import com.topiefor.models.Unit;
import com.topiefor.service.IngredientService;
import com.topiefor.service.UnitService;
import com.topiefor.service.impl.IngredientServiceImpl;
import com.topiefor.service.impl.RecipeServiceImpl;
import com.topiefor.service.impl.UnitServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class processTheRecipeRequest implements ProcessRequest {

    List<Recipe> allRecipies = null;
    List<Ingredient> listOfIngredients = null;

    int recipeID = 0;
    String recipeIDs;
    String recipeName;
    String recipeStatus;
    String recipeInstruction;
    private List<Ingredient> RecipeListInstruction;
    boolean flagStatus = false;
    private String page;
    private RecipeServiceImpl recipeServiceImpl;

    public processTheRecipeRequest(RecipeDao recipeDao) {
        recipeServiceImpl = new RecipeServiceImpl(recipeDao);
    }

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {

        allIngredients(request);
        allUnits(request);
        String RecipeAction = request.getParameter("action");

        if (RecipeAction != null) {
            switch (RecipeAction.trim()) {
                case "add":
                    listOfIngredients = handleIngredientJson(request.getParameter("ingredientsArray"));
                    recipeName = request.getParameter("recipeName");
                    recipeInstruction = request.getParameter("recipeInstruction");
                    if (recipeName != null && !recipeName.isEmpty()) {
                        recipeName = recipeName.trim();
                        recipeInstruction = recipeInstruction.trim();

                        if (recipeServiceImpl.addRecipe(new Recipe(0, recipeInstruction, recipeName, true, listOfIngredients))) {

                            request.setAttribute("allCategories", new ArrayList<>(recipeServiceImpl.getAllRecipies()));
                            request.setAttribute("message", "Recipe added!");
                        } else {
                            request.setAttribute("message", "Recipe not Added");
                        }

                        page = "Admin/AddRecipePage.jsp";
                    }

                    break;

                //---------------------------------------------------------------------------------------------------------
                case "edit":
                    recipeName = request.getParameter("recipeName");
                    recipeInstruction = request.getParameter("recipeInstruction");
                    if (recipeName != null) {
                        try {
                            recipeID = Integer.parseInt(request.getParameter("recipeID"));
                        } catch (NumberFormatException ex) {
                            System.out.println("error" + ex.getMessage());
                        }
                        if (!recipeName.isEmpty() && recipeID > 0) {
                            if (recipeServiceImpl.editRecipe(new Recipe(recipeInstruction, recipeName))) {
                                request.setAttribute("allRecipies", new ArrayList<>(recipeServiceImpl.getAllRecipies()));
                                request.setAttribute("message", "Recipe Updated!");
                            } else {
                                request.setAttribute("message", "Recipe not Updated!");
                            }
                        }

                    }
                    page = "Admin/AddRecipePage.jsp";
                    break;
                //---------------------------------------------------------------------------------------------------------
                case "get":
                    allRecipies = recipeServiceImpl.getAllRecipies();
                    if (allRecipies != null && !allRecipies.isEmpty()) {

                        request.setAttribute("allRecipies", allRecipies);
                    } else {
                        request.setAttribute("response", "no data");
                    }
                    page = "Admin/RecipesPage.jsp";
                    break;

                //------------------------------------------------------------------------------------
                case "delete":

                    recipeName = request.getParameter("recipeName");
                    recipeStatus = request.getParameter("status");

                    if (recipeStatus != null && !recipeStatus.isEmpty()) {

                        recipeStatus = recipeStatus.trim();
                        flagStatus = checkFlag(recipeStatus, request);
                    }
                    try {
                        recipeID = Integer.parseInt(request.getParameter("recipeID").trim());
                    } catch (NumberFormatException ex) {
                        System.out.println("error " + ex.getMessage());
                    }
                    if (recipeName != null && !recipeName.isEmpty() && recipeID > 0) {
                        if (recipeServiceImpl.deleteRecipe(new Recipe(recipeID, flagStatus))) {
                            request.setAttribute("allRecipies", new ArrayList<>(recipeServiceImpl.getAllRecipies()));
                            request.setAttribute("message", "Recipe status changed");
                        } else {
                            request.setAttribute("allRecipies", new ArrayList<>(recipeServiceImpl.getAllRecipies()));
                            request.setAttribute("message", "Recipe status  not changed");
                        }
                    }
                    page = "Admin/RecipesPage.jsp";
                    break;
                case "addForm":
                    page = "Admin/AddRecipePage.jsp";
                    break;
                case "editForm":
                    recipeIDs = request.getParameter("recipeID");
                    if (recipeIDs != null && !recipeIDs.isEmpty()) {
                        recipeIDs = recipeIDs.trim();
                        try {
                            recipeID = Integer.parseInt("recipeIDs");
                        } catch (NumberFormatException ex) {
                            System.out.println("error: " + ex.getMessage());
                        }
                        if (operation(request)) {

                            if (recipeID > 0) {

                                Recipe recipe = new Recipe(recipeID, recipeName, recipeInstruction, flagStatus, RecipeListInstruction);
                                request.setAttribute("recipe", recipe);

                            } else {

                                page = "Admin/ErrorPage.jsp";
                            }
                            page = "Admin/AddProductPage.jsp";
                            break;
                        }
                    }
                    page = "Admin/ErrorPage.jsp";
                    break;

                default:
                    request.setAttribute("response", "something went wrong");
                    break;
            }
        }
    }

    private void allIngredients(HttpServletRequest request) {
        ServletContext sc = request.getServletContext();
        DatabaseManager databaseManager = (DatabaseManager) sc.getAttribute("dbman");
        IngredientService ingredientService = new IngredientServiceImpl(IngredientDaoImpl.getInstance(databaseManager.getConnection()));
        request.setAttribute("allIngredients", new ArrayList<>(ingredientService.getAllIngredient()));

    }

    private void allUnits(HttpServletRequest request) {
        ServletContext sc = request.getServletContext();
        DatabaseManager databaseManager = (DatabaseManager) sc.getAttribute("dbman");
        UnitService unitService = new UnitServiceImpl(UnitDaoImpl.getInstance(databaseManager.getConnection()));
        request.setAttribute("allUnits", new ArrayList<>(unitService.getAllUnits()));

    }

    private boolean checkFlag(String categoryStatus, HttpServletRequest request) {
         flagStatus = false;
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

    private List<Ingredient> handleIngredientJson(String ingredientsJson) {
        String ingredientID;
        String quantity;
        String unit;

        List<Ingredient> ingredients = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(ingredientsJson);
            for (Object obj : jsonArray) {
                JSONObject ingredientObject = (JSONObject) obj;
                ingredientID = (String) ingredientObject.get("ingredientID");
                quantity = (String) ingredientObject.get("quantity");
                unit = (String) ingredientObject.get("unit");
                ingredients.add(new Ingredient(Integer.parseInt(ingredientID.trim()), Integer.parseInt(quantity.trim()), new Unit(Integer.parseInt(unit.trim()))));

            }
        } catch (ParseException ex) {
            System.out.println("error" + ex);
        }

        return ingredients;
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

    public boolean checkForNulls() {
        return recipeName != null && !recipeName.isEmpty()
                && recipeInstruction != null && !recipeInstruction.isEmpty()
                && RecipeListInstruction != null && !RecipeListInstruction.isEmpty();

    }

    public boolean operation(HttpServletRequest request) {
        recipeName = request.getParameter("recipeName");
        recipeInstruction = request.getParameter("recipeInstruction");

        if (checkForNulls()) {

            recipeName = recipeName.trim();
            recipeInstruction = recipeInstruction.trim();
            return true;
        }
        return false;
    }

}
