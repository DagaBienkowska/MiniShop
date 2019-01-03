package com.dagabienkowska.servlets;

import com.dagabienkowska.DAO.Impl.UserJsonDaoImpl;
import com.dagabienkowska.DAO.Spec.UserDao;
import com.dagabienkowska.Utils.CookieUtils;
import com.dagabienkowska.shop.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Optional;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    private UserDao userDao = new UserJsonDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        List<User> users = userDao.getAllUsers();
        Optional<User> user = users.stream()
                .filter(e -> e.getUsername().equals(username)
                        && e.getPassword().equals(password))
                .findFirst();
        if (user.isPresent()){
            CookieUtils.createUserCookie(resp, user.get());
            req.getSession().setAttribute("username", username);
            resp.sendRedirect("/Products");
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
            PrintWriter out = resp.getWriter();
            out.println("<p>Invalid username or password</p>");
            rd.include(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}