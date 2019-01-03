package com.dagabienkowska.servlets;

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

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream input = getServletContext().getResourceAsStream("/WEB-INF/jsonV1.json");
        String serverPath = getServletContext().getRealPath("/WEB-INF/jsonV1.json");
        Gson gson = new Gson();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(input, "utf-8"));

        JsonPOJO products = gson.fromJson(buffer, JsonPOJO.class);
        List<Product> productsList = products.getProducts();

        JsonPOJO users = gson.fromJson(buffer, JsonPOJO.class);
        List<User> userList = users.getUsers();

        JsonPOJO carts = gson.fromJson(buffer, JsonPOJO.class);
        List<ShoppingCart> shoppingCarts = carts.getCarts();

        int id = Integer.valueOf(req.getParameter("addToCart"));

        String userName = null;
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies){
            cookie.getName().equals("userInCookie");
            userName = cookie.getValue();
        }

        int itemCounter = 0;
        if (shoppingCarts.isEmpty()){
            ShoppingCart shoppingCart = new ShoppingCart();
            List<Product> pr = new ArrayList<>();
            shoppingCart.setUsername(userName);
            pr.add(productsList.get(id));
            shoppingCart.setProductList(pr);
            itemCounter++;

        }

        req.getSession().setAttribute("itemCount", itemCounter);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("product_list.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
