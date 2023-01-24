package controllers;

import auth.AuthHandler;
import database.MongoHandler;
import models.Book;
import models.Category;
import models.Publisher;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "publisher", value = "/publisher")
public class ProducerController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = AuthHandler.getInstance().authenticateUser(request.getCookies());
        request.setAttribute("user", user);
        request.getRequestDispatcher("producer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");

        Publisher publisher = new Publisher(null, name, address, email, mobile);
        boolean result = MongoHandler.getInstance().addPublisher(publisher);
        if (result) {
            response.getWriter().println("Publisher Added");
            response.setHeader("Refresh", "5; URL=home");
        }
    }
}