package com.dagabienkowska.servlets;

import com.dagabienkowska.shop.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {

    private String path = "C:\\Users\\Dagmara\\Projekty\\MiniShop\\src\\main\\webapp\\WEB-INF\\jsonV1.json";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("registerUsername");
        String password = req.getParameter("registerPassword");
        String name = req.getParameter("registerName");
        String surname = req.getParameter("registerSurname");
        InputStream input = getServletContext().getResourceAsStream("/WEB-INF/jsonV1.json");
        Gson gson = new Gson();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(input, "utf-8"));
        JsonPOJO dataFromJson = gson.fromJson(buffer, JsonPOJO.class);

        List<Product> products = dataFromJson.getProducts();

        List<User> users = dataFromJson.getUsers();

        User user = new User();
        user.setId(users.size()+1);
        user.setUsername(username);
        user.setPassword(password);
        user.setTotalCashSpend(0);
        user.setName(name);
        user.setSurname(surname);
        user.setRole("user");
        users.add(user);

        dataFromJson.setUsers(users);
        Writer writer = new FileWriter(path);
        gson = new GsonBuilder().create();
        gson.toJson(dataFromJson, writer);
        writer.flush();
        writer.close();

        RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
        resp.getWriter()
                .println("<font color=red>Registration successful. Please log in</font>");
        rd.include(req, resp);

    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
