/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.topiefor.service;

import com.topiefor.models.Recipe;
import java.util.List;

/**
 *
 * @author Train
 */
public interface RecipeService {
     boolean addRecipe(Recipe recipe);
    boolean editRecipe(Recipe recipe);
    List<Recipe> getAllRecipies();
    boolean deleteRecipe(Recipe recipe);
    
}
