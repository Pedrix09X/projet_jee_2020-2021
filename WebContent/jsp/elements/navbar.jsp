<%@ page import="entities.User"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
	<div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a class="nav-link" href="index.jsp">Accueil</a></li>
			<li class="nav-item"><a class="nav-link" href="#">Je suis positif !</a></li>
		</ul>
	</div>
	<div class="mx-auto order-0">
		<span class="navbar-brand mb-0 h1">antiCovid</span>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".dual-collapse2">
			<span class="navbar-toggler-icon"></span>
		</button>
	</div>
	<ul class="nav navbar-nav ml-auto w-100 justify-content-end">
		<%
		User user = (User) session.getAttribute("user");
		if (user != null) {
		%>
			<li class="navbar-text"><span>Bonjour, <%= user.getLogin() %> !</span></li>
			<li class="nav-item"><a class="nav-link" href="#">Se déconnecter</a></li>
		<%} else { %>
			<li class="nav-item"><a class="nav-link" href="#">Se connecter</a></li>
			<li class="nav-item"><a class="nav-link" href="signup">S'inscrire</a></li>
		<%} %>
	</ul>
</nav>




