package controllers;

import auth.AuthHandler;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "register", value = "/register")
public class RegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        AuthHandler authHandler = AuthHandler.getInstance();
        String username = request.getParameter("username");
        String mobile = request.getParameter("phone");
        String address = request.getParameter("address");
        boolean register = authHandler.registerUser(email, password, name, username, mobile, address);
        if (register) {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "error");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
