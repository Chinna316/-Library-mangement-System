package controllers;

import auth.AuthHandler;
import database.MongoHandler;
import models.Author;
import models.Publisher;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "author", value = "/author")
public class AuthorController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = AuthHandler.getInstance().authenticateUser(request.getCookies());
        request.setAttribute("user", user);
        request.getRequestDispatcher("author.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        Author author = new Author(null, name, address, email, mobile);
        boolean result = Objects.requireNonNull(MongoHandler.getInstance()).addAuthor(author);
        if (result) {
            response.getWriter().println("Author Added");
            response.setHeader("Refresh", "5; URL=home");
        }
    }
}