package controllers;

import database.MongoHandler;
import models.Author;
import models.Book;
import models.Category;
import models.Publisher;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "modify", value = "/modify")
public class ModifyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String req = request.getParameter("request");
        String isbn = request.getParameter("isbn");
        List<Publisher> publishers = MongoHandler.getInstance().getPublishers();
        System.out.println("Publishers: "+publishers);
        request.setAttribute("publishers", publishers);
        List<Author> authors = MongoHandler.getInstance().getAuthors();
        System.out.println("Authors: "+authors);
        request.setAttribute("authors", authors);
        List<Category> categories = MongoHandler.getInstance().getCategories();
        request.setAttribute("categories", categories);
        if (req.equalsIgnoreCase("delete")) {
            Objects.requireNonNull(MongoHandler.getInstance()).deleteBook(isbn);
            System.out.println("ISBN: " + isbn);
            System.out.println("Book deleted");
            response.sendRedirect("books");
        } else if (req.equalsIgnoreCase("edit")) {
            Book book = MongoHandler.getInstance().searchBookByISBN(isbn);
            request.setAttribute("book", book);
            request.getRequestDispatcher("edit_book.jsp").forward(request, response);
            response.getWriter().println("Edit: " + isbn);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        int stock = Integer.parseInt(request.getParameter("stock"));

        String edition = request.getParameter("edition");
        String publisher = request.getParameter("publisher");
        String author = request.getParameter("author");
        String category = request.getParameter("category");

        Book book = new Book(isbn, title, stock, null, edition, null, new Category(category));
        boolean result = MongoHandler.getInstance().updateBook(id, book, author, publisher);
        if (result) {
            response.getWriter().println("Book Updated");
            response.setHeader("Refresh", "5; URL=books");
        }


    }
}
