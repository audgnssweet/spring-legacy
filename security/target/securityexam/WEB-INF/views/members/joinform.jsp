<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <title>로그인 </title>
</head>
<body>
<div>
  <div>
    <form method="post" action="/auth/joinProc">
      <div>
        <label>이름</label>
        <input type="text" name="name">
      </div>
      <div>
        <label>email</label>
        <input type="email" name="email"/>
      </div>
      <div>
        <label>암호</label>
        <input type="password" name="password">
      </div>
      <div>
        <label></label>
        <input type="submit" value="로그인">
      </div>
    </form>
  </div>
</div>
</body>
</html>