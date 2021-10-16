<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Web News</title>
		<link rel="stylesheet" href="views/news.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.0.0/animate.min.css">
</head>

<body>
	<% 
	   session.setAttribute("view", "signin"); 
	   session.setAttribute("nuser", ""); 
	   session.setAttribute("message", "");
	   session.setAttribute("searchTitle", "");
	%>
	
	<% if (session.getAttribute("view") == "signin") { %>
		<%@ include file="views/signin.jsp" %>
	<% } %>
</body>

</html>