package com.example.dm;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class MessageServlet extends HttpServlet {
    private static final List<Message> messages = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String sender = req.getParameter("sender");
        String receiver = req.getParameter("receiver");
        String content = req.getParameter("content");

        messages.add(new Message(sender, receiver, content));
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("chat.jsp").forward(req, resp);
    }
}
