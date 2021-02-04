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
	try{
		ServletContext servletContext = getServletContext();
		int value = (int)servletContext.getAttribute("value");
		value += 2;
		servletContext.setAttribute("value", value);		
%>
		<h1>value = <%=value %></h1>
<%
	}catch(Exception e){
%>
	<h1>attribute value : is not exist</h1>
<%
	}
%>


</body>
</html>