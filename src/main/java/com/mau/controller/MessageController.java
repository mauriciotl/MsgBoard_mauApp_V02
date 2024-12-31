
package com.mau.controller;

import com.mau.model.Message;
import com.mau.service.MessageStorage;
import com.mau.utils.IdGenerator;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;

public class MessageController {

    private MessageStorage getMessageStorage(HttpServletRequest request) {
        return (MessageStorage) request.getServletContext().getAttribute("messageStorage");
    }

    public void showHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MessageStorage messageStorage = getMessageStorage(request);
        request.setAttribute("messages", messageStorage.getAllMessages());
//        request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
        request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
    }

    public void addMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String user = "User";  // For simplicity, using a static user; can be extended with session

        Message message = new Message(IdGenerator.generateId(), user, name, description, new Date());
        getMessageStorage(request).addMessage(message);

        response.sendRedirect(request.getContextPath());
    }

    public void deleteMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        getMessageStorage(request).deleteMessage(id);

        response.sendRedirect(request.getContextPath());
    }

    public void updateMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Message newMessage = new Message(id, "User", name, description, new Date());
        getMessageStorage(request).updateMessage(id, newMessage);

        response.sendRedirect(request.getContextPath());
    }

    public void viewMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Message message = getMessageStorage(request).getMessageById(id);
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/views/messageDetail.jsp").forward(request, response);
        request.getRequestDispatcher("/jsp/messageDetail.jsp").forward(request, response);
    }
}
