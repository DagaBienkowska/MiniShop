package com.dagabienkowska.servlets;

import com.dagabienkowska.DAO.Impl.ProductJsonDaoImpl;
import com.dagabienkowska.DAO.Impl.ShoppingCartJsonDaoImpl;
import com.dagabienkowska.DAO.Spec.ProductDao;
import com.dagabienkowska.DAO.Spec.ShoppingCartDao;
import com.dagabienkowska.Utils.JsonUtils;
import com.dagabienkowska.shop.JsonPOJO;
import com.dagabienkowska.shop.Product;
import com.dagabienkowska.shop.ShoppingCart;
import com.dagabienkowska.shop.User;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private ShoppingCartDao shoppingCartDao = new ShoppingCartJsonDaoImpl();
    private ProductDao productDao = new ProductJsonDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String productId = req.getParameter("product");
        String username = req.getParameter("username");
        Optional<ShoppingCart> shoppingCart = shoppingCartDao.findCartByUsername(username);
        JsonPOJO jsonAccess = JsonUtils.getJson();
        List<Product> allProduts = productDao.getAllProducts();
        int indexOfProduct = allProduts.indexOf(allProduts.get(Integer.valueOf(productId) -1));

        if (shoppingCart.isPresent()) {
            List<Product> products = shoppingCart.get().getProducts();
            Optional<Product> product = products
                    .stream()
                    .filter(e ->e.getId().equals(Integer.valueOf(productId)))
                    .findFirst();
            if (product.isPresent()){
                int index = products.indexOf(product.get());
                int actualQuantity = product.get().getQuantity();
                product.get().setQuantity(actualQuantity +1);
                products.set(index, product.get());
                allProduts.get(indexOfProduct).setQuantity(allProduts.get(indexOfProduct).getQuantity() -1);


            } else {
                Product productToAdd = Product.builder()
                        .id(Integer.valueOf(productId))
                        .quantity(1)
                        .build();
                products.add(productToAdd);
                allProduts.get(indexOfProduct).setQuantity(allProduts.get(indexOfProduct).getQuantity() -1);

            }
            jsonAccess.setProducts(allProduts);
            JsonUtils.writeJson(jsonAccess);
            shoppingCartDao.saveOrUpdateCart(shoppingCart.get());

        } else {
            List<Product> productsNewCart = new ArrayList<>();
            Product addProductToNewCart = Product.builder()
                    .id(Integer.valueOf(productId))
                    .quantity(1)
                    .build();
            productsNewCart.add(addProductToNewCart);
            ShoppingCart addNewCart = ShoppingCart.builder()
                    .username(username)
                    .products(productsNewCart)
                    .build();

            allProduts.get(indexOfProduct).setQuantity(allProduts.get(indexOfProduct).getQuantity() -1);
            jsonAccess.setProducts(allProduts);
            JsonUtils.writeJson(jsonAccess);
            shoppingCartDao.saveOrUpdateCart(addNewCart);


        }
        RequestDispatcher rd = req.getRequestDispatcher("/Products");
        rd.forward(req, resp);
    }
}
