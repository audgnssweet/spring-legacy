<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- EL 표기법 --%>
<h1> dice = ${dice } </h1>

<%--
<%
	int dice = (int)request.getAttribute("dice");
	for(int i = 0; i < dice; i++){
%>
	<h4>Hello</h4>
<%
	}
%>
--%>
</body>
</html>