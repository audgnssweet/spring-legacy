<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

${3+4 }
${3 == 4 }
${"같음" == "같음" }

${4 == 4 && "같음" == "같음" }

<% int noempty = 3; %>
<br>
isempty = ${empty "noempty" }

</body>
</html>