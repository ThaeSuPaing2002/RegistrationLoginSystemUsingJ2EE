<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
if(request.getAttribute("result")!=null){
	%>
	<h3><%= request.getAttribute("result")%></h3>
	<% 
}
%>
	<%@ page import="layer.entity.*"%>
	<%
	if (session.getAttribute("validated") != null) {
		User user = (User) session.getAttribute("validated");
	%>
	<h3>
		Welcome
		<%=user.getName()%>!!
		<%=user.getId() %>
	</h3>
	<form action="UpdateServlet" method="post">
		<table>
		<tr>
				<td></td>
				<td><input type="text" name="id" value="<%=user.getId()%>" hidden></td>
			</tr>
			<tr>
				<td>New Name:</td>
				<td><input type="text" name="name" value="<%=user.getName()%>"></td>
			</tr>
			<tr>
				<td>New Email:</td>
				<td><input type="email" name="email"
					value="<%=user.getEmail()%>" ></td>
			</tr>
			<tr>
				<td>New Password:</td>
				<td><input type="password" name="password" ></td>
			</tr>
			<tr>
				<td>Enter Current Password:</td>
				<td><input type="password" name="current_password"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Register"></td>
			</tr>
		</table>
	</form>
	<form action="LogoutServlet" method="post">
	<input type="text" name="id" value="<%=user.getId() %>" hidden>
	<input type="submit" value="Logout">
	</form>
	
	<form action="DeleteServlet" method="post">
	<input type="text" name="id" value="<%=user.getId() %>" hidden>
	<button type="submit" onclick="return confirm('are you sure?')">Delete</button>
	</form>
	<% 
}else{
	response.sendRedirect("login.jsp");
}
%>

</body>
</html>