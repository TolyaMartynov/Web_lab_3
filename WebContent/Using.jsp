<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="myPack.*"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 

HttpSession s = request.getSession();
if(s.getAttribute("login") == null || s.getAttribute("password") == null) {
	getServletConfig().getServletContext().getRequestDispatcher("/EnterServlet.java").forward(request, response);	
	return;
}
else
{
	UserData ud = Entering.Enter((String)s.getAttribute("login"), (String)s.getAttribute("password"));
	if(ud == null)
	{
		getServletConfig().getServletContext().getRequestDispatcher("/EnterServlet").forward(request, response);	
		return;
	}
	else
	{
		java.util.ArrayList<UserData> userArray = Entering.GetUsers();
		%><h1>Users list</h1><p> 
		<form action="EnterServlet" method="post">
		<table border="0"><%
		for(int i = 0; i < userArray.size(); i++)
		{%>
			<tr><td><%= userArray.get(i).login %><td><td><%if(ud.isAdmin && !ud.login.equals(userArray.get(i).login)) { %><button type="submit" value="<%=i %>" name="remove">delete</button><%} %></td></tr>
	  	<%}%>
	  	</table>
	  	</form>
	<%}
	
}
%>
<form action="EnterServlet" method="post" >
<button type="submit" value="exit" name="exit">exit</button>
</form>
</body>
</html>