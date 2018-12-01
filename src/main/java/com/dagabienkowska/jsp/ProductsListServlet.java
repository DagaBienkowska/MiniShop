package com.dagabienkowska.jsp;

import com.dagabienkowska.DAO.Product;
import com.dagabienkowska.DAO.Products;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/products")
public class ProductsListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream input = getServletContext().getResourceAsStream("/WEB-INF/jsonV1_json.js");
        Gson gson = new Gson();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(input, "utf-8"));
        List<Product> productsList = new ArrayList<>();

        Products products = gson.fromJson(buffer, Products.class);
        for (Product p : products.getProducts()){
            long id = p.getId();
            String name = p.getName();
            String description = p.getDescription();
            long price = p.getPrice();
            int quantity = p.getQuantity();

            Product product = new Product(id, name, description, price, quantity);
            productsList.add(product);

        }

        req.getSession().setAttribute("products", productsList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("product_list.jsp");
        requestDispatcher.forward(req, resp);
    }
}
