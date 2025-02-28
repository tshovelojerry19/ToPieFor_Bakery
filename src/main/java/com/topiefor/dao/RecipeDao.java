package com.topiefor.dao;

import com.topiefor.models.Recipe;
import java.util.List;

public interface RecipeDao {

    boolean addRecipe(Recipe recipe);
    boolean editRecipe(Recipe recipe);
    List<Recipe> getAllRecipies();
    boolean deleteRecipe(Recipe recipe);
    
}
