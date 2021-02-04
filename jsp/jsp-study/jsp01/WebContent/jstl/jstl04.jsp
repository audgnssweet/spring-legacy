<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:import url="http://www.siminsori.com/news/photo/201908/216003_65054_1826.jpg"
var="urlValue" scope="request"></c:import>

${requestScope.urlValue }

</body>
</html>