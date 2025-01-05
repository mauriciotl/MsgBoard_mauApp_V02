package com.mau.controller;

import com.mau.model.User;
import com.mau.service.MessageStorage;
import com.mau.model.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serial;
import java.util.Collections;
import java.util.List;

@WebServlet("/dispatchServlet") // Servlet mapping
public class DispatcherServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final String MESSAGE_STORAGE = "messageStorage";

    @Override
    public void init() throws ServletException {
        // Initialize MessageStorage in ServletContext if not already present
        if (getServletContext().getAttribute(MESSAGE_STORAGE) == null) {
            getServletContext().setAttribute(MESSAGE_STORAGE, new MessageStorage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        HttpSession session = request.getSession(false); // Get existing session or null
//        if (session == null || session.getAttribute("user") == null) {
//            // User not logged in
//            response.sendRedirect(request.getContextPath() + "/login"); // Forward to dispatch servlet
//            return; // Important: Stop further processing
//        }



        MessageStorage messageStorage = (MessageStorage) getServletContext().getAttribute(MESSAGE_STORAGE);

        // Retrieve all messages and set them as a request attribute
        List<Message> messages = messageStorage.getAllMessages();

        //Reverse the Message list.
        request.setAttribute("messages", this.reverseMessages(messageStorage));

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Forward to the JSP for rendering
        request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // Get existing session or null

//        if (session == null || session.getAttribute("user") == null) {
//            // User not logged in
//            response.sendRedirect(request.getContextPath() + "/login"); // Forward to dispatch servlet
//            return; // Important: Stop further processing
//        }


        String action = request.getParameter("action");
        MessageStorage messageStorage = (MessageStorage) getServletContext().getAttribute(MESSAGE_STORAGE);

        if ("addMessage".equals(action)) {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
//            String user = request.getRemoteAddr(); // Use IP address as user identifier
            User user = (User) session.getAttribute("user");
            int id = com.mau.utils.IdGenerator.generateId();

            // Create and store the new message
            Message newMessage = new Message(id, user.getName(), name, description, new java.util.Date());
            messageStorage.addMessage(newMessage);

            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");

            response.sendRedirect(request.getContextPath() + "/dispatchServlet");
        } else if ("deleteMessage".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            messageStorage.deleteMessage(id);
            response.sendRedirect(request.getContextPath() + "/dispatchServlet");
        } else if ("requestUpdateMessage".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Message updateMessage= messageStorage.getMessageById(id);
            request.setAttribute("message", updateMessage);
            request.getRequestDispatcher("/jsp/messageDetail.jsp").forward(request, response);
        } else if ("applyUpdateMessage".equals(action)) {

            //APPLY UPDATE ROUTINE
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            // Update the message with new values.
            Message updatedMessage = messageStorage.getMessageById(id);
            updatedMessage.setName(name);
            updatedMessage.setDescription(description);
            messageStorage.updateMessage(id, updatedMessage);
            response.sendRedirect(request.getContextPath() + "/dispatchServlet");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private List<Message> reverseMessages(MessageStorage messageStorage) {
        List<Message> messages = messageStorage.getAllMessages(); // Retrieve a copy of the messages list
        Collections.reverse(messages); // Reverse the list
        return messages; // Return the reversed list
    }

}
