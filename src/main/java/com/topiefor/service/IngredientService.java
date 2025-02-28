package com.topiefor.service;

import com.topiefor.models.Ingredient;
import java.util.List;

public interface IngredientService {
    
   boolean addIngredient(Ingredient ingredient);
    boolean editIngredient(Ingredient ingredient);
    List<Ingredient> getAllIngredient();
    boolean deleteIngredient(Ingredient ingredient);
    Ingredient getIngredientByIngredientID(int id);
}
