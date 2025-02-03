<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ page import="layer.entity.*" %>
<%
if(session.getAttribute("validated")!=null){
	User user = (User) session.getAttribute("validated");
	%>
	<h3>Welcome <%=user.getName() %>!!</h3>
	<% 
}else{
	response.sendRedirect("login.jsp");
}
%>
</body>
</html>