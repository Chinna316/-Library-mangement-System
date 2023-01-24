package controllers;

import auth.AuthHandler;
import database.MongoHandler;
import models.Author;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "category", value = "/category")
public class CategoryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = AuthHandler.getInstance().authenticateUser(request.getCookies());
        request.setAttribute("user", user);
        request.getRequestDispatcher("category.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        boolean result = Objects.requireNonNull(MongoHandler.getInstance()).addCategoryToDB(name);
        if (result) {
            response.getWriter().println("Category Added");
            response.setHeader("Refresh", "5; URL=home");
        }
    }
}