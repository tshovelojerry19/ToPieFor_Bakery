package com.topiefor.service.impl;

import com.topiefor.dao.IngredientDao;
import com.topiefor.models.Ingredient;
import com.topiefor.service.IngredientService;
import java.util.List;

public class IngredientServiceImpl implements IngredientService {

    private IngredientDao ingredientDao;
    private String page;

    public IngredientServiceImpl(IngredientDao ingredientDao) {
        setIngredientDao(ingredientDao);
    }

    @Override
    public boolean addIngredient(Ingredient ingredient) {
        return ingredient == null ? false : ingredientDao.addIngredient(ingredient);
    }

    @Override
    public boolean editIngredient(Ingredient ingredient) {
        return ingredient == null ? false : ingredientDao.editIngredient(ingredient);
    }

    @Override
    public List<Ingredient> getAllIngredient() {
        if (ingredientDao.getAllIngredient() != null || !(ingredientDao.getAllIngredient().isEmpty())) {
            return ingredientDao.getAllIngredient();
        }
        return null;
    }

    private void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    @Override
    public boolean deleteIngredient(Ingredient ingredient) {
        return ingredient == null ? false : ingredientDao.deleteIngredient(ingredient);
    }

    //----------------------------------test-----------------------------------------
    @Override
    public Ingredient getIngredientByIngredientID(int id) {
        if (id > 0) {
            for (Ingredient ingredient : getAllIngredient()) {
                if (ingredient.getIngreidientID() == id) {
                    return ingredient;
                }
            }
        }
        return null;
    }
}
