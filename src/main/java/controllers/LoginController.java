package controllers;

import auth.AuthHandler;
import database.MongoHandler;
import models.Publisher;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@WebServlet(name = "login", value = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        User user = AuthHandler.getInstance().authenticateUser(cookies);
        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("home").forward(request, response);
        } else {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        AuthHandler authHandler = AuthHandler.getInstance();
        try {
            String session = authHandler.loginUser(email, password);
            Cookie cookie = new Cookie("jwt", session);
            response.addCookie(cookie);
            Cookie[] cookies = new Cookie[]{cookie};
            User user = AuthHandler.getInstance().authenticateUser(cookies);
            request.setAttribute("user", user);
            response.sendRedirect("home");
        } catch (RuntimeException exc) {
            request.setAttribute("error", "Invalid username");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Invalid Username/password");
            request.getRequestDispatcher("login").forward(request, response);
        }
    }
}
