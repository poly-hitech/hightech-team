<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, your.package.Message" %>
<html>
<head>
    <style>
        body {
            background-color: white;
            font-family: Arial, sans-serif;
        }
        .chat-box {
            border: 1px solid #ccc;
            padding: 20px;
            width: 500px;
            margin: auto;
        }
        .message {
            padding: 10px;
            border-bottom: 1px solid #eee;
        }
        .send-btn {
            background-color: #8000ff;
            color: white;
            border: none;
            padding: 10px;
        }
    </style>
</head>
<body>
    <div class="chat-box">
        <form action="send" method="post">
            <input type="text" name="sender" placeholder="보내는 사람" required><br><br>
            <input type="text" name="receiver" placeholder="받는 사람" required><br><br>
            <textarea name="content" placeholder="메시지를 입력하세요" required></textarea><br><br>
            <button type="submit" class="send-btn">보내기</button>
        </form>
        <hr>
        <h3>메시지 기록</h3>
        <%
            List<Message> messages = (List<Message>) request.getAttribute("messages");
            if (messages != null) {
                for (Message msg : messages) {
        %>
                    <div class="message">
                        <strong><%= msg.getSender() %></strong> ➝ <%= msg.getReceiver() %>: <%= msg.getContent() %>
                    </div>
        <%
                }
            }
        %>
    </div>
</body>
</html>
