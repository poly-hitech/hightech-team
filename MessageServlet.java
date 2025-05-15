import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/send")
public class MessageServlet extends HttpServlet {
    private static final List<Message> messages = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sender = request.getParameter("sender");
        String receiver = request.getParameter("receiver");
        String content = request.getParameter("content");

        messages.add(new Message(sender, receiver, content));
        request.setAttribute("messages", messages);
        RequestDispatcher dispatcher = request.getRequestDispatcher("chat.jsp");
        dispatcher.forward(request, response);
    }
}
