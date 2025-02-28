package com.topiefor.service.impl;

import com.topiefor.models.Ingredient;
import java.util.List;
import com.topiefor.dao.IngredientReportDao;
import com.topiefor.service.IngredientReportService;

public class IngredientReportServiceImpl implements IngredientReportService {

    private IngredientReportDao ingredientReportDao;

    public IngredientReportServiceImpl(IngredientReportDao ingredientReportDao) {
        setReportDao(ingredientReportDao);
    }
    
    public void setReportDao(IngredientReportDao reportDao) {
        this.ingredientReportDao = reportDao;
    }

    @Override
    public List<Ingredient> ingredientInStock() {
         if (ingredientReportDao.ingredientInStock() != null || !(ingredientReportDao.ingredientInStock().isEmpty())) {
            return ingredientReportDao.ingredientInStock();
        }
        return null;
    }

    @Override
    public List<Ingredient> ingredientsRequiredToBe0rdered() {
         if (ingredientReportDao.ingredientsRequiredToBe0rdered() != null || !(ingredientReportDao.ingredientsRequiredToBe0rdered().isEmpty())) {
            return ingredientReportDao.ingredientsRequiredToBe0rdered();
        }
        return null;
    }

    @Override
    public boolean ingredientsRequiredToBe0rderedWriter(String fileName) {
        return fileName == null ? false : ingredientReportDao.ingredientsRequiredToBe0rderedWriter(fileName);
    }

    @Override
    public boolean ingredientInStockWriter(String fileName) {
        return fileName == null ? false : ingredientReportDao.ingredientInStockWriter(fileName);
    }

   

}