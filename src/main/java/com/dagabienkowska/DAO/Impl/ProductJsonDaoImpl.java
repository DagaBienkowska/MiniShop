package com.dagabienkowska.DAO.Impl;

import com.dagabienkowska.DAO.Spec.ProductDao;
import com.dagabienkowska.Utils.JsonUtils;
import com.dagabienkowska.shop.JsonPOJO;
import com.dagabienkowska.shop.Product;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductJsonDaoImpl implements ProductDao {

    private static final Logger LOGGER = Logger.getLogger(ProductJsonDaoImpl.class.getName());

    @Override
    public boolean createOrUpdateProduct(Product product) {
        JsonPOJO jsonPOJO = JsonUtils.getJson();
        List<Product> products = JsonUtils.getJson().getProducts();
        if (products.contains(product)){
            int index = products.indexOf(product);
            products.set(index, product);
            LOGGER.log(Level.INFO, "Updated product");
        } else {
            jsonPOJO.getProducts().add(product);
        }
        jsonPOJO.setProducts(products);
        JsonUtils.writeJson(jsonPOJO);
        return  true;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = JsonUtils.getJson().getProducts();
        LOGGER.log(Level.INFO, "Fetched all products");
        return products;
    }
}
