package com.dagabienkowska.servlets;

import com.dagabienkowska.DAO.Impl.UserJsonDaoImpl;
import com.dagabienkowska.DAO.Spec.UserDao;
import com.dagabienkowska.shop.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {

    private UserDao userDao = new UserJsonDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("registerUsername");
        String password = req.getParameter("registerPassword");
        String name = req.getParameter("registerName");
        String surname = req.getParameter("registerSurname");

        List<User> users = userDao.getAllUsers();

        Integer max = users.stream()
                .mapToInt(User::getId)
                .max().getAsInt();
            User user = User.builder()
                    .id(max +1)
                    .username(username)
                    .password(password)
                    .name(name)
                    .surname(surname)
                    .totalCashSpend(new BigDecimal(0))
                    .role("user")
                    .build();

        userDao.createUser(user);

        RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
        rd.forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
