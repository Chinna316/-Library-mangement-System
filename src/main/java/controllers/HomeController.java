package controllers;

import auth.AuthHandler;
import database.MongoHandler;
import models.Author;
import models.Category;
import models.Publisher;
import models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "home", value = "/home")
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        User m_user = (User) request.getAttribute("user");
        User user = AuthHandler.getInstance().authenticateUser(cookies);
        List<Publisher> publishers = MongoHandler.getInstance().getPublishers();
        System.out.println("Publishers: "+publishers);
        request.setAttribute("publishers", publishers);
        List<Author> authors = MongoHandler.getInstance().getAuthors();
        System.out.println("Authors: "+authors);
        request.setAttribute("authors", authors);
        List<Category> categories = MongoHandler.getInstance().getCategories();
        request.setAttribute("categories", categories);
        if (user != null && user.getRole().equals(User.LIBRARIAN)) {
            System.out.println("Setting user");
            request.setAttribute("user", user);
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        } else if (user != null && user.getRole().equals(User.STUDENT)) {
            System.out.println("Setting m_user");
            request.setAttribute("user", m_user);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
