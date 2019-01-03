package com.dagabienkowska.DAO.Spec;

import com.dagabienkowska.shop.Product;

import java.util.List;

public interface ProductDao {

    boolean createOrUpdateProduct(Product product);
    List<Product> getAllProducts();

}
