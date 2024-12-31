<%--<jsp:useBean id="message" scope="request" type="com.mau.model.Message"/>--%>
<%--<jsp:useBean id="message" class="com.mau.model.Message" scope="request"/>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message Details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<h1>${message.name}</h1>
<p>User: ${message.user}</p>
<p>Date: ${message.issuedDate}</p>
<p>${message.description}</p>

<form action="${pageContext.request.contextPath}/dispatchServlet" method="POST">
    <input type="hidden" name="id" value="${message.id}">
    <input type="text" name="name" value="${message.name}"><br>
    <textarea name="description" required>${message.description}</textarea><br>
    <button type="submit" name="action" value="applyUpdateMessage">Update</button>
</form>
</body>
</html>
