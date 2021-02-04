<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:set var="testvar01" scope="page">page testvar</c:set>

testvar = ${pageScope.testvar01 } <br>

<c:remove var="testvar01" scope="page"/>

<c:set var="testvar02" scope="request" value="request testvar2" />

testvar2 = ${requestScope.testvar02 }

<c:remove var="testvar02" scope="request"/>

</body>
</html>