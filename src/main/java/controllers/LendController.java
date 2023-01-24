package controllers;

import auth.AuthHandler;
import database.MongoHandler;
import models.Book;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "lend", value = "/lend")
public class LendController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        User user = AuthHandler.getInstance().authenticateUser(request.getCookies());
        Book book = MongoHandler.getInstance().searchBookByISBN(isbn);
        request.setAttribute("book", book);
        request.setAttribute("user", user);
        System.out.println(user);
        request.getRequestDispatcher("lend.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String isbn = request.getParameter("isbn");
        String day = request.getParameter("days");
        System.out.println(isbn);
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(day);
            User user = AuthHandler.getInstance().authenticateUser(request.getCookies());
            Book book = MongoHandler.getInstance().searchBookByISBN(isbn);
            boolean res = MongoHandler.getInstance().lendBook(user, isbn, date);
            if (res) {
                System.out.println("sending to mybooks");
                response.sendRedirect("mybooks");
            } else {
                request.setAttribute("error", "Error occurred");
                request.setAttribute("book", book);
                request.setAttribute("user", user);
                request.getRequestDispatcher("lend.jsp").forward(request, response);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
