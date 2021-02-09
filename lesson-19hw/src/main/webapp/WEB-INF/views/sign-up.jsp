<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Sign up</title>
<!-- Font Icon -->
<link rel="stylesheet" href="/fonts/material-icon/css/material-design-iconic-font.min.css">
<!-- Main css -->
<link rel="stylesheet" href="/css/style.css">
<!-- Main js -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" type="text/javascript"></script>
</head>
<body>
	<center><h3>Upload & Download</h3></center>
	<!-- Sign up form -->
	<section class="signup">
		<div class="container">
			<div class="signup-content">
				<div class="signup-form">
					<h3 class="form-title">Student Sign-Up Form</h3>
					<form class="register-form" name="register-form" id="register-form" >
						<div class="form-group">
							<label for="fname"><i class="zmdi zmdi-account material-icons-name"></i></label>
							<input name="firstName" id="name" placeholder="Name" />
							<div class="help-block" id="error_name"></div>
						</div>
						<div class="form-group">
							<label for="age"><i class="zmdi zmdi-account material-icons-name"></i></label>
							<input name="age" id="age" placeholder="Age" />
							<div class="help-block" id="error_age"></div>
						</div>
						<div class="form-group">
							<label for="attachment"><i class="zmdi zmdi-file"></i></label>
							<input type="file" name="file" accept="*" id="file" />
							<div class="help-block" id="error_file"></div>
						</div>
						<div class="form-group form-button">
							<input type="button" id="signup" class="form-submit" value="Submit" />
						</div>
					</form><br>
					<center><div id="success"></div></center>
					<center><div id="error"></div></center>
				</div>
				<div class="signup-image">
					<div style="display: flex; justify-content: space-between;">
						<a href="${pageContext.request.contextPath}/image-upload/students"
							class="signup-image-link">View Students</a> <a
							href="${pageContext.request.contextPath}/image-upload/home"
							class="signup-image-link">Go Home</a>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- JS -->
	<script src="/vendor/jquery/jquery.min.js"></script>
	<script src="/js/main.js"></script>
</body>
</html>