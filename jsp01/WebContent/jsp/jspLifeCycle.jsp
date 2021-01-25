<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%!
	public void jspInit(){
		System.out.println("jsp-init called!");
	}

	public void jspDestroy(){
		System.out.println("jsp-destroy called!");
	}
%>

<% System.out.println("jsp-service called~"); %>

</body>
</html>