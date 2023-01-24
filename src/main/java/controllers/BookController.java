package controllers;

import auth.AuthHandler;
import database.MongoHandler;
import models.Book;
import models.Category;
import models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "books", value = "/books")
public class BookController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        User user = AuthHandler.getInstance().authenticateUser(request.getCookies());
        List<Book> books;
        if (request.getParameter("category") != null) {
            System.out.println("Category: "+ request.getParameter("category"));
            books = MongoHandler.getInstance().getBooksByCategory(request.getParameter("category"));
        } else if (query == null || query.length() < 1) {
            books = MongoHandler.getInstance().getBooks(10);
        } else {
            books = MongoHandler.getInstance().getBooksBySearch(query);
        }

        System.out.println(Arrays.toString(books.toArray()));

        request.setAttribute("books", books);
        request.setAttribute("user", user);
        System.out.println(user);
        request.getRequestDispatcher("books.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        int stock = Integer.parseInt(request.getParameter("stock"));
        String edition = request.getParameter("edition");
        String publisher = request.getParameter("publisher");
        String author = request.getParameter("author");
        String category = request.getParameter("category");
        Book book = new Book(isbn, title, stock, null, edition, null, new Category(category));
        boolean result = MongoHandler.getInstance().addBook(book, publisher, author);
        if (result) {
            response.getWriter().println("Book Added");
            response.setHeader("Refresh", "5; URL=books");
        }
    }
}