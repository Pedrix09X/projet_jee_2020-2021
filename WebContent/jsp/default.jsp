<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="icon" href="<%=request.getContextPath()%>/images/favicon.ico" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<title>antiCovid - ${title}</title>
</head>
<body>
	<%@include file="elements/navbar.jsp" %>
	<div class="container p-2">
		<h2>${title}</h2>
		<% pageContext.include((String) request.getAttribute("page")); %>
	</div>
	<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>