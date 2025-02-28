
package com.topiefor.dao;

import com.topiefor.models.Product;
import java.util.List;


public interface ProductDao {
    boolean addProduct(Product product);
    boolean editProduct(Product product);
    boolean productAvailability(Product product);
    List<Product> getAllProducts();
    
}
