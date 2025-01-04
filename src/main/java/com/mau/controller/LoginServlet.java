package com.mau.controller;

import com.mau.model.User;
import com.mau.service.UserStorage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@WebServlet("/login") // Servlet mapping
public class LoginServlet extends HttpServlet {

    private final UserStorage userStorage = new UserStorage(); // Instance of your storage

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get existing session or null

        if (session != null && session.getAttribute("user") != null) {
            // User already logged in (session exists)
            response.sendRedirect(request.getContextPath() + "/dispatchServlet"); // Forward to dispatch servlet
            return; // Important: Stop further processing
        }

        // User not in session, validate credentials
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        if (name == null || password == null || name.isEmpty() || password.isEmpty()){
            request.setAttribute("userFounded", false);
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            return;
        }

        Optional<User> userFound = userStorage.getAllUsers().stream().filter(user -> user.getName().equals(name) && user.getPassword().equals(password)).findFirst();
        if (userFound.isPresent()) {
            // Valid credentials, create session and redirect
            session = request.getSession(); // Create new session
            session.setAttribute("user", userFound.get()); // Store user object in session
            response.sendRedirect(request.getContextPath() + "/dispatchServlet"); // Redirect to dispatch servlet
        } else {
            // Invalid credentials
            request.setAttribute("userFounded", false);
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}