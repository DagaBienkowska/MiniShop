package com.dagabienkowska.servlets;

import com.dagabienkowska.shop.JsonPOJO;
import com.dagabienkowska.shop.User;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        String password = req.getParameter("pass");
        InputStream input = getServletContext().getResourceAsStream("/WEB-INF/jsonV1.json");
        Gson gson = new Gson();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(input, "utf-8"));
        boolean isUser = true;

        JsonPOJO users = gson.fromJson(buffer, JsonPOJO.class);
        for (User u : users.getUsers()) {
            String userFromJson = u.getUsername();
            String passwordFromJson = u.getPassword();

            if (user != null && user.equals(userFromJson)
                    && password != null && password.equals(passwordFromJson)) {
                Cookie loginCookie = new Cookie("userInCookie", user);
                loginCookie.setMaxAge(30 * 60);
                resp.addCookie(loginCookie);
                resp.sendRedirect("/products");
            } else {
                isUser = false;
            }
        }

        if (!isUser) {
            RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
            resp.getWriter()
                    .println("<font color=red>You are not loged in or put wrong loging data</font>");
            rd.include(req, resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}