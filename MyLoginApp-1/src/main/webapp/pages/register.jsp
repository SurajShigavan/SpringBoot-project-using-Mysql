<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    
    <form action="/user/register1" method="post">
    <h2>Resister</h2>
     <c:if test="${not empty error}">
			<div style="color: red">${errorMessage}</div>
		</c:if><br>
        <input type="text" name="username" placeholder="Username" required><br>
        <input type="password" name="password" placeholder="Password" required><br>
        <button type="submit">Register</button>
        
      <button type="button" onclick="location.href='/user/'">Back to Login</button> 
    </form>
    <p id="error" class="error"></p>

</body>
</html>
