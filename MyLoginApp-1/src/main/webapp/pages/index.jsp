<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/styles.css">
</head>
<body>


	<form action="/user/login2" method="post">
		<h2>Login</h2>
		<c:if test="${not empty error}">
			<div style="color: red">${error}</div>
		</c:if>
		<br> <input type="text" name="username" placeholder="Username"
			required><br> <input type="password" name="password"
			placeholder="Password" required><br>
		<button type="submit">Login</button>

		<button type="button" onclick="location.href='/user/register'">Resister</button>

	</form>


</body>
</html>
