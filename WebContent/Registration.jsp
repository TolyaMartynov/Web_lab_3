<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="MainServlet" method="post">
	<h3>Registration</h3>
	<% if(request.getSession().getAttribute("exception") != null) { %>
	<font color="red"><%= request.getSession().getAttribute("exception") %></font>
	<% }
	request.getSession().removeAttribute("exception");%>
	<table border="0">
	<tr><td>Login:</td><td><input name="login" type="text" value="" id="nameInput"></td></tr>
	<tr><td>Password:</td><td><input name="password" type="text" value="" id="pass"></td></tr>
	<tr><td>Is admin:</td><td><input name="isAdmin" type="checkbox" value="" id="isAdmin"></td></tr>
	<tr><td><input type="submit" value="Registration"></td></tr>
	</table>
	</form>
	<a href="http://localhost:8080/TestServer/EnterServlet">enter</a>
</body>
</html>