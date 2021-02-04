<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SumExample</title>
</head>
<body>

<%
	final int targetNum = 10;
	int resultSum = 0;
	for(int i = 0; i <= targetNum; i++)
		resultSum += i;
%>

1-10까지의 합은 = <%=resultSum %>

</body>
</html>