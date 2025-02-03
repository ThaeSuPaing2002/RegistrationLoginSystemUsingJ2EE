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
<form action="RegisterServlet" method="post">
<table>
<tr><td>Name: </td><td><input type="text" name="name"></td></tr>
<tr><td>Email: </td><td><input type="email" name="email"></td></tr>
<tr><td>Password: </td><td><input type="password" name="password"></td></tr>
<tr><td>Confirmation Password: </td><td><input type="password" name="cpassword"></td></tr>
<tr><td> </td><td><input type="submit" value="Register"></td></tr>
</table>
</form>
</body>
</html>