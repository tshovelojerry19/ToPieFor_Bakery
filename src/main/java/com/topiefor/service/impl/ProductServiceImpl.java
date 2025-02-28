package com.topiefor.service.impl;

import com.topiefor.dao.ProductDao;
import com.topiefor.models.Product;
import com.topiefor.service.ProductService;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        setProductDao(productDao);
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public boolean addProduct(Product product) {
        return product == null ? false : productDao.addProduct(product);
    }

    @Override
    public boolean editProduct(Product product) {
        return product == null ? false : productDao.editProduct(product);
    }

    @Override
    public boolean productAvailability(Product product) {
        return product == null ? false : productDao.productAvailability(product);
    }

    @Override
    public List<Product> getAllProducts() {
        if (productDao.getAllProducts() != null || !(productDao.getAllProducts().isEmpty())) {
            return productDao.getAllProducts();
        }
        return null;
    }

    @Override
    public List<Product> getProductsByCategory(int categoryID) {
        List<Product> allProduct = getAllProducts();
        List<Product> filterProducts = new ArrayList();

        allProduct.stream().filter(prods -> (categoryID == prods.getCategory().getCategoryID() && prods.isStatus() == true)).forEachOrdered(prods -> {
            filterProducts.add(prods);
        });

        return filterProducts;
    }

   

}
