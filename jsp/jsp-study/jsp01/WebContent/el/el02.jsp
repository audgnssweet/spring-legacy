<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	pageContext.setAttribute("p1", "page scope value");
	request.setAttribute("r1", "request scope value");
	session.setAttribute("s1", "session scope value");
	application.setAttribute("a1", "application scope value");
%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- jsp style vs el style --%>
p1 : <%= pageContext.getAttribute("p1") %> <br>
p1 : ${pageScope.p1 } <br>

r1 : <%= request.getAttribute("r1") %> <br>
r1 : ${requestScope.r1 } <br>

s1 : <%= session.getAttribute("s1") %> <br>
s1 : ${sessionScope.s1 } <br>

a1 : <%= application.getAttribute("a1") %> <br>
a1 : ${applicationScope.a1 } <br>

</body>
</html>