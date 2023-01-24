package controllers;

import database.MongoHandler;
import models.Order;
import models.Payment;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ret_book", value = "/ret_book")
public class ReturnController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("id");
        Order order = MongoHandler.getInstance().getOrderById(orderId);

        if (order.getTotal() > 0) {
            request.setAttribute("order", order);
            request.getRequestDispatcher("ret_order.jsp").forward(request, response);
        }else{
            Payment payment = new Payment(orderId, "none", "none", "none", "none");
            MongoHandler.getInstance().retBook(orderId, payment);
            request.getRequestDispatcher("mybooks").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String orderId = request.getParameter("id");
        String cardNumber = request.getParameter("card_number");
        String expYear = request.getParameter("expiry_year");
        String expMonth = request.getParameter("expiry_month");
        String cvv = request.getParameter("cvv");

        Payment payment = new Payment(orderId, cardNumber, expYear, expMonth, cvv);


        MongoHandler.getInstance().retBook(orderId, payment);

        response.getWriter().println("Payment successful");
        response.setHeader("Refresh", "5; URL=mybooks");

    }
}
