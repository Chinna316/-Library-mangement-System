package controllers;

import auth.AuthHandler;
import database.MongoHandler;
import models.Order;
import models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "orders", value = "/orders")
public class OrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = AuthHandler.getInstance().authenticateUser(request.getCookies());
        if (user!=null && user.getRole().equals(User.LIBRARIAN)){
            if ("post".equalsIgnoreCase(request.getParameter("method"))){
                doPost(request, response);
            }else{
                List<Order> orders = MongoHandler.getInstance().getAllOrders();
                System.out.println(Arrays.toString(orders.toArray()));
                request.setAttribute("user", user);
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("orders.jsp").forward(request, response);
            }
        }else{
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String status = request.getParameter("status");
        String orderId = request.getParameter("id");
        MongoHandler.getInstance().updateOrder(orderId, status);
        User user = AuthHandler.getInstance().authenticateUser(request.getCookies());
        List<Order> orders = MongoHandler.getInstance().getAllOrders();
        System.out.println(Arrays.toString(orders.toArray()));
        request.setAttribute("user", user);
        request.setAttribute("orders", orders);

        request.getRequestDispatcher("orders.jsp").forward(request, response);

    }
}
