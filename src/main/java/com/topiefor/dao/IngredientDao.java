package com.topiefor.dao;

import com.topiefor.models.Ingredient;
import java.util.List;

public interface IngredientDao {
    
    boolean addIngredient(Ingredient ingredient);
    boolean editIngredient(Ingredient ingredient);
    List<Ingredient> getAllIngredient();
    boolean deleteIngredient(Ingredient ingredient);

}
