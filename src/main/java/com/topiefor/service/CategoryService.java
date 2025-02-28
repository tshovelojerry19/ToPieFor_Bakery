package com.topiefor.service;

import com.topiefor.models.Category;
import java.util.List;


public interface CategoryService {

    boolean addCategory(Category category);
    boolean editCategory(Category category);
    List<Category> getAllCategories();
    //    Category getCategoryByName(String categoryName);
        boolean deleteCategory(Category category);

}
