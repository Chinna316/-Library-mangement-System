package controllers;

import auth.AuthHandler;
import database.MongoHandler;
import models.Order;
import models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "mybooks", value = "/mybooks")
public class MyBooksController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = AuthHandler.getInstance().authenticateUser(request.getCookies());
        System.out.println(user.getId().toHexString());
        if (user != null){
            List<Order> orders = MongoHandler.getInstance().getMyOrders(user.getEmail());
            System.out.println(orders);
            request.setAttribute("orders", orders);
            request.setAttribute("user", user);
            request.getRequestDispatcher("mybooks.jsp").forward(request, response);
        }else {
            response.setStatus(403);
            response.sendRedirect("login");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
