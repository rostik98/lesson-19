<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>All Students</title>
<!-- Main css -->
<!-- <link rel="stylesheet" href="/css/style.css"> -->
<link rel="stylesheet" href="/css/styles.css">
<link rel="stylesheet" href="/css/test.css"> 
</head>
<body>	
<br>
	<!-- Sign up form -->
	<section class="signup">
		
				<br>
				<!--  add our html table here -->
				<c:set var="count" value="0" scope="page" /><br>
				<table border="1">
					<tr>
						<th>Sr.No</th>
						<th>Name</th>
						<th>Age</th>
						<th>File Name</th>
						<th>File Size</th>
						<th>File Type</th>
						<th>Image</th>
						<th>Created Date</th>
						<th>Action</th>
					</tr>
			<c:choose>
				<c:when test="${not empty studentsList}">
				<c:forEach var="theStudent" items="${studentsList}">
				<c:set var="count" value="${count + 1}" scope="page"/>
					<tr>
						<td>${count}</td>
						<td> ${theStudent.name} </td>
						<td> ${theStudent.age} </td>
						<td> ${theStudent.fileName} </td>
						<td> ${theStudent.fileSize} </td>
						<td> ${theStudent.fileType} </td>
						<td><img src="/uploads/${theStudent.fileName}" width="270" height="250" /> </td>
						<td> ${theStudent.createdDate} </td>
		<td> 
		<a href="${pageContext.request.contextPath}/image-upload/removeFile/${theStudent.id}/${theStudent.fileName}" onclick="return confirm('Are you sure you want to delete ${theStudent.name}?');">Delete</a>
		</td>
					</tr>
				</c:forEach>
				</c:when>
				<c:otherwise>
					<center><h1>No Students Found.</h1></center>
				</c:otherwise>
			</c:choose>
				
</table>

				<br><br>
				<div style="display: flex; justify-content: space-between;">
						<a href="${pageContext.request.contextPath}/image-upload/students"
							class="signup-image-link">View Students</a>
							<a href="${pageContext.request.contextPath}/image-upload/sign-up" class="signup-image-link">Sign Up</a> 
							 <a
							href="${pageContext.request.contextPath}/image-upload/home"
							class="signup-image-link">Go Home</a>
					</div>
			
			<br> <br> <br>
	
	</section>
</body>
</html>