/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.topiefor.service.impl;

import com.topiefor.dao.RecipeDao;
import com.topiefor.dao.impl.CategoryDaoImpl;
import com.topiefor.models.Category;
import com.topiefor.models.Recipe;
import com.topiefor.service.CategoryService;
import com.topiefor.service.RecipeService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Train
 */
public class RecipeServiceImpl implements RecipeService {

    private RecipeDao recipeDao;

    public RecipeServiceImpl(RecipeDao recipeDao) {
        setRecipeDao(recipeDao);
    }

    @Override
    public boolean addRecipe(Recipe recipe) {
        return recipe == null ? false : recipeDao.addRecipe(recipe);
    }

    @Override
    public boolean editRecipe(Recipe recipe) {
        return recipe == null ? false : recipeDao.editRecipe(recipe);

    }

    @Override
    public List<Recipe> getAllRecipies() {
        if (recipeDao.getAllRecipies() != null || !(recipeDao.getAllRecipies().isEmpty())) {
            return recipeDao.getAllRecipies();
        }
        return null;
    }

    public void setRecipeDao(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    @Override
    public boolean deleteRecipe(Recipe recipe) {
        return recipe == null ? false : recipeDao.deleteRecipe(recipe);
    }

    //----------------------------------test-----------------------------------------
}
