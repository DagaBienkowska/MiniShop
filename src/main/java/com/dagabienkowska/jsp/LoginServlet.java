package com.dagabienkowska.jsp;

import com.dagabienkowska.DAO.User;
import com.dagabienkowska.DAO.Users;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
        InputStream input = getServletContext().getResourceAsStream("/WEB-INF/jsonV1_json.js");
        Gson gson = new Gson();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(input, "utf-8"));

        Users users = gson.fromJson(buffer, Users.class);
        for (User u : users.getUsers()) {
            String userFromJson = u.getUsername();
            String passwordFromJson = u.getPassword();

            if (user != null && user.equals(userFromJson)
                    && password != null && password.equals(passwordFromJson)) {
                Cookie loginCookie = new Cookie("userInCookie", user);
                loginCookie.setMaxAge(30 * 60);
                resp.addCookie(loginCookie);
                resp.sendRedirect("/product_list.jsp");
            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                resp.getWriter()
                        .println("<font color=red>Jesteś niezalogowany albo błędne dane.</font>");
                rd.include(req, resp);
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
