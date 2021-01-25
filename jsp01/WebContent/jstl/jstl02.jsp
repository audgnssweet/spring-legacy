<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="n" scope="request" value="50" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:if test="${n == 20 }">
	<h1>n은 20입니다.</h1>
</c:if>
<c:if test="${n == 10 }">
	<h1>n은 10입니다.</h1>
</c:if>

<c:choose>
	<c:when test="${n == 10 }">
		<h1>n은 10입니다.</h1>
	</c:when>
	<c:when test="${n == 20 }">
		<h1>n은 20입니다.</h1>
	</c:when>
	<c:otherwise>
		<h1>n은 몇입니까?</h1>
	</c:otherwise>
</c:choose>

</body>
</html>