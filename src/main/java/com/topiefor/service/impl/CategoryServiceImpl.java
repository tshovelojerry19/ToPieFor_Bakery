package com.topiefor.service.impl;

import com.topiefor.dao.CategoryDao;
import com.topiefor.dao.impl.CategoryDaoImpl;
import com.topiefor.models.Category;
import com.topiefor.service.CategoryService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;
    private String page;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        setCategoryDao(categoryDao);
    }

    private boolean checkCategoryAvailability(Category category) {
        return getAllCategories().stream().anyMatch(cat -> (cat.equals(category)));
    }

    @Override
    public boolean addCategory(Category category) {
        if (checkCategoryAvailability(category)) {

            return false;
        }
        return category == null ? false : categoryDao.addCategory(category);
    }

    @Override
    public boolean editCategory(Category category) {
        if (!checkCategoryAvailability(category)) {

            return false;
        }
        return category == null ? false : categoryDao.editCatgory(category);

    }

    @Override
    public List<Category> getAllCategories() {
        if (categoryDao.getAllCategories() != null || !(categoryDao.getAllCategories().isEmpty())) {
            return categoryDao.getAllCategories();
        }
        return null;
    }

    private void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public boolean deleteCategory(Category category) {
        if (!checkCategoryAvailability(category)) {

            return false;
        }
        return category == null ? false : categoryDao.deleteCategory(category);
    }

    //----------------------------------test-----------------------------------------
   

}
