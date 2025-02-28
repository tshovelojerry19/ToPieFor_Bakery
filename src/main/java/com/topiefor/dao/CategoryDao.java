package com.topiefor.dao;

import com.topiefor.models.Category;
import java.util.List;

public interface CategoryDao {

    boolean addCategory(Category category);
    boolean editCatgory(Category category);
    boolean deleteCategory(Category category);
    List<Category> getAllCategories();
    //    Category getCategoryByName(String categoryName);
    //    boolean deleteCategory(int categoryID);

}