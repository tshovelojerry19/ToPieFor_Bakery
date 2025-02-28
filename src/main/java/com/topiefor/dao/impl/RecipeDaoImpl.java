package com.topiefor.dao.impl;

import com.topiefor.dao.RecipeDao;
import com.topiefor.models.Ingredient;
import com.topiefor.models.Recipe;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class RecipeDaoImpl implements RecipeDao {

    private static RecipeDaoImpl recipeDaoImpl;
    private Connection con = null;
    private PreparedStatement ps = null;
    private PreparedStatement ps2 = null;
    private ResultSet rs = null;

    private RecipeDaoImpl(Connection con) {
        this.con = con;
        fetchAllRecipesFromDB();
    }

    //--------------------------------------------------------
    public static RecipeDaoImpl getInstance(Connection dbCon) {
        if (recipeDaoImpl == null) {
            recipeDaoImpl = new RecipeDaoImpl(dbCon);
        }
        return recipeDaoImpl;
    }

    private List<Recipe> fetchAllRecipesFromDB() {
        List<Recipe> recipeList = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT RecipeID,RecipeName,Instruction,Flag FROM recipe");
                rs = ps.executeQuery();
                while (rs.next()) {
                    recipeList.add(new Recipe(rs.getInt("RecipeID"), rs.getString("RecipeName"), rs.getString("Instruction"), rs.getBoolean("Flag")));
                }
            } catch (SQLException ex) {
                System.out.println("Error!!: " + ex.getMessage());
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }
        return recipeList;
    }

    @Override
    public List<Recipe> getAllRecipies() {
        return fetchAllRecipesFromDB();
    }

    @Override
    public boolean addRecipe(Recipe recipe) {

        if (checkRecipeAvailability(recipe)) {
            return false;
        }
        boolean retVal = false;
        int recipeID = 0;
        boolean succeed = true;
        if (con != null) {
            try {
                con.setAutoCommit(false);
                ps = con.prepareStatement("INSERT INTO recipe(RecipeID,RecipeName,Instruction,flag,LastDateUpdated) values(?,?,?,?,?)");

                ps.setInt(1, 0);
                ps.setString(2, recipe.getRecipeName());
                ps.setString(3, recipe.getInstruction());
                ps.setBoolean(4, true);
                Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
                ps.setTimestamp(5, currentTimestamp);

                if (ps.executeUpdate() > 0) {
                    ps = con.prepareStatement("SELECT LAST_INSERT_ID()");
                    rs = ps.executeQuery();
                    recipeID = -1;
                    if (rs.next()) {
                        recipeID = rs.getInt(1);
                    }
                    for (Ingredient ingredient : recipe.getIngredients()) {

                        ps = con.prepareStatement("INSERT INTO recipe_ingredient(RecipeID,IngredientID,Quantity) values(?,?,?)");
                        ps.setInt(1, recipeID);
                        ps.setInt(2, ingredient.getIngreidientID());
                        ps.setInt(3, ingredient.getQuantity());

                        if (ps.executeUpdate() < 1) {
                            con.rollback();
                            succeed = false;
                            break;
                        }
                    }
                    if (succeed) {
                        con.commit();
                        retVal = true;
                    }

                }
            } catch (SQLException ex) {

                try {
                    con.rollback();
                } catch (SQLException ex1) {
                    System.out.println("ERROR:" + ex1);
                }

                System.out.println("Error!!: " + ex.getMessage());
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }
        return retVal;
    }

    private boolean checkRecipeAvailability(Recipe recipe) {

        return fetchAllRecipesFromDB().stream().anyMatch(rec -> (rec.equals(recipe)));
    }

    @Override
    public boolean editRecipe(Recipe recipe) {

        if (checkRecipeAvailability(recipe)) {
            boolean retVal = false;
            if (con != null) {
                System.out.println("inside");
                try {
                    System.out.println("inside");
                    ps = con.prepareStatement("UPDATE recipe SET Instruction = ?,RecipeName =? , LastDateUpdated =? WHERE RecipeID = ? ");
                    ps.setString(1, recipe.getInstruction());
                    ps.setString(2, recipe.getRecipeName());
                    Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
                    ps.setTimestamp(3, currentTimestamp);
                    ps.setInt(4, recipe.getRecipeID());
                    if (ps.executeUpdate() > 0) {
                        retVal = true;
                    }
                } catch (SQLException ex) {
                    System.out.println("Error!!: " + ex.getMessage());
                } finally {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (SQLException ex) {
                            System.out.println("Could not close: " + ex.getMessage());
                        }
                    }
                }
            }
            return retVal;
        }
        return false;
    }

    @Override
    public boolean deleteRecipe(Recipe recipe) {
        if (checkRecipeAvailability(recipe)) {
            boolean retVal = false;
            if (con != null) {
                try {
                    ps = con.prepareStatement("UPDATE recipe SET flag = ?,LastDateUpdated =?  WHERE RecipeID = ?");
                    ps.setBoolean(1, recipe.isStatus());
                    Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
                    ps.setTimestamp(2, currentTimestamp);
                    ps.setInt(3, recipe.getRecipeID());

                    if (ps.executeUpdate() > 0) {
                        retVal = true;
                    }

                } catch (SQLException ex) {
                    System.out.println("Error!!: " + ex.getMessage());
                } finally {
                    if (ps != null) {
                        try {

                            ps.close();
                        } catch (SQLException ex) {
                            System.out.println("Could not close: " + ex.getMessage());
                        }
                    }
                }
            }
            return retVal;
        }
        return false;
    }

    
}
