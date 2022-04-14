<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String errorMessage = (String) request.getAttribute("errorMessage");
%>
<h1>Error: <%=errorMessage %></h1>
</body>
</html>