<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

My id is <%=getId() %>

<%!
	String id = "audgnssweet";	//멤버변수 선언	

	public String getId(){		//메소드 선언
		return id;
	}
%>

</body>
</html>