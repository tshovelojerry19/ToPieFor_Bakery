package com.topiefor.dao;

import com.topiefor.models.Ingredient;
import java.util.List;

public interface IngredientReportDao {

    List<Ingredient> ingredientInStock();
    List<Ingredient> ingredientsRequiredToBe0rdered();
    boolean ingredientsRequiredToBe0rderedWriter(String fileName);
   boolean ingredientInStockWriter(String fileName);

}