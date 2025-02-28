package com.topiefor.service;

import com.topiefor.models.Product;
import java.util.List;

public interface ProductService {

    boolean addProduct(Product product);
    boolean editProduct(Product product);
    boolean productAvailability(Product product);
    List<Product> getProductsByCategory(int categoryID);
    List<Product> getAllProducts();

}