<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Input Page</title>
      <link rel="stylesheet" href="/css/success.css">
</head>
<body>
    <h2>Welcome ${param.username} !</h2>
    <p>You can now proceed to write something:</p>
    <form action="/user/save" method="post">
     <input type="hidden" name="id" value="${id}" />
        <label for="userInput">Your Input:</label><br>
        <textarea id="userInput" name="userInput" rows="4" cols="50"></textarea><br><br>
        <c:if test="${not empty error}">
			<div style="color: green">${success}</div>
		</c:if>
        <button type="submit">Submit</button>
        <button type="button" onclick="location.href='/user/'"class="logout-button">Log Out</button> 
    </form>
</body>
</html>
