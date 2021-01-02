<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<title>antiCovid</title>
</head>
<body>

	<%@include file="jsp/elements/navbar.jsp" %>

	<div class="container">
		<%@include file="jsp/elements/message.jsp" %>
		
		<div class="row mb-4">
			<div class="col-lg-8"><h2>Quelques données sur la pandémie en France</h2></div>
			<div class="lastUpdate col-lg-4"></div>
		</div>
		<div class="row">
			<div class="col-lg-12"><h4>Depuis la dernières mise à jour</h4></div>
		</div>
		<div class="d-lg-flex justify-content-evenly mb-4">
			<div class="shadow text-center diffCase p-4 bd-highlight col-lg-3 rounded-3"><p class="fs-3 fw-bold"></p>cas confirmés</div>
			<div class="shadow text-center diffDeath p-4 bd-highlight col-lg-3 rounded-3"><p class="fs-3 fw-bold"></p>décès</div>
			<div class="shadow text-center diffRecov p-4 bd-highlight col-lg-3 rounded-3"><p class="fs-3 fw-bold"></p>récupérés</div>
		</div>
		<div class="row totalLabel">
			<div class="col-lg-12"><h4>Total</h4></div>
		</div>
		<div class="d-lg-flex justify-content-evenly mb-4">
			<div class="shadow text-center totCase p-4 bd-highlight col-lg-3 rounded-3"><p class="fs-3 fw-bold"></p>cas confirmés</div>
			<div class="shadow text-center totDeath p-4 bd-highlight col-lg-3 rounded-3"><p class="fs-3 fw-bold"></p>décès</div>
			<div class="shadow text-center totRecov p-4 bd-highlight col-lg-3 rounded-3"><p class="fs-3 fw-bold"></p>récupérés</div>
		</div>
	</div>

	<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="js/getStats.js"></script>
</body>
</html>