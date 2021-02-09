<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Students</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<form class="signup">
		<div class="container">
			<br>
			<div class="signup-image">

				<div style="display: flex; justify-content: space-between;">
					<a href="${pageContext.request.contextPath}/image-upload/sign-up" class="signup-image-link">Sign Up</a> 
					<a href="${pageContext.request.contextPath}/image-upload/students" class="signup-image-link">View Students</a>
				</div>
			</div>
		</div>
	</form>
</body>
</html>