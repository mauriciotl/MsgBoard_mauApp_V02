<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message Board</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<h1>Message Board</h1>

<form action="${pageContext.request.contextPath}/dispatchServlet" method="POST">
    <input type="text" name="name" placeholder="Message Name" required><br>
    <textarea name="description" placeholder="Message Description" required></textarea><br>
    <button type="submit" name="action" value="addMessage">Post Message</button>
</form>

<h2>All Messages</h2>
<ul>
    <c:choose>
        <c:when test="${not empty messages}">
            <c:forEach var="message" items="${messages}">
                <li>
                    <p>${message.user} - ${message.issuedDate} - ${message.name}</p>
                    <p>${message.description}</p>
                    <form action="${pageContext.request.contextPath}/dispatchServlet" method="POST" style="display:inline;">
                        <input type="hidden" name="id" value="${message.id}">
                        <button type="submit" name="action" value="deleteMessage">Delete</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/dispatchServlet" method="POST" style="display:inline;">
                        <input type="hidden" name="id" value="${message.id}">
                        <button type="submit" name="action" value="requestUpdateMessage">Update</button>
                    </form>
                </li>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p>No messages to display.</p>
        </c:otherwise>
    </c:choose>
</ul>
</body>
</html>
