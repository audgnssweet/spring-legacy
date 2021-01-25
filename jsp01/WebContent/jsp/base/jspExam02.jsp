<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 두 개의 Scriptlet 사이에 html코드가 들어가는 것도 가능합니다. -->
<%
	for(int i = 1; i <= 5; i++){
%>
<h<%=i %>>JSP는 이런식으로도 쓸 수 있습니다.</h<%=i %>>
<%
	}
%>

</body>
</html>