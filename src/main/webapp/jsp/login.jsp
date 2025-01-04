<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginStyles.css">
</head>
<body>
<div class="login-container">
  <h1>Login</h1>

  <%-- Display error message if userFound is false --%>
  <% if (request.getAttribute("userFounded") != null && !(Boolean) request.getAttribute("userFounded")) { %>
  <div class="error-message">Invalid username or password.</div>
  <% } %>

  <form action="<%= request.getContextPath() %>/login" method="post">
    <label for="name">Username:</label>
    <input type="text" id="name" name="name" required><br><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>

    <button type="submit">Login</button>
  </form>
</div>
</body>
</html>