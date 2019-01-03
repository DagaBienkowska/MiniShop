package com.dagabienkowska.servlets;

import com.dagabienkowska.DAO.Impl.ProductJsonDaoImpl;
import com.dagabienkowska.DAO.Spec.ProductDao;
import com.dagabienkowska.shop.Product;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet("/Products")
public class ProductsListServlet extends HttpServlet {

    private ProductDao productDao = new ProductJsonDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Product> products = productDao.getAllProducts();
        req.setAttribute("productList", products);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("product_list.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
