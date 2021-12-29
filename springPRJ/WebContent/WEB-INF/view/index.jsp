<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	Hello World
	<form action="/user/insertUserInfo.do">
		<input type="text" name="user_id">
		<input type="text" name="user_password">
		<input type="text" name="user_name">
		<input type="text" name="user_nic">
		<input type="text" name="user_age">
		<input type="text" name="user_gender">
		<input type="submit">
	</form>

<form action="/user/findUserInfo.do" method="post">
	<input type="text" name="user_id">
	<input type="password" name="user_password">
	<input type="submit">
</form>
</body>
</html>