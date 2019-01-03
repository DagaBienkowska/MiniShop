package com.dagabienkowska.servlets;

import com.dagabienkowska.shop.JsonPOJO;
import com.dagabienkowska.shop.Product;
import com.dagabienkowska.shop.ShoppingCart;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/products")
public class ProductsListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream input = getServletContext().getResourceAsStream("/WEB-INF/jsonV1.json");
        String serverPath = getServletContext().getRealPath("/WEB-INF/jsonV1.json");
        Gson gson = new Gson();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(input, "utf-8"));

        JsonPOJO products = gson.fromJson(buffer, JsonPOJO.class);
        List<Product> productsList = products.getProducts();



        req.getSession().setAttribute("products", productsList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("product_list.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
