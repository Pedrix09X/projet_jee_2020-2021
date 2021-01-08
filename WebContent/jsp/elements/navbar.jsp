<%@page import="tables.TableLocator"%>
<%@ page import="entities.User"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% User user = (User) session.getAttribute("user"); %>

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
	<div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item p-2"><a class="nav-link" href="<%=request.getContextPath()%>">Accueil</a></li>
			<%
			if (user != null) {
			%>
				<%
				if (user.isInfected()) {
				%>
				<li class="nav-item p-2"><a class="nav-link" href="" data-bs-toggle="modal" data-bs-target="#positifModal">Je ne suis plus positif !</a></li>
				<%
				} else { 
				%>
				<li class="nav-item p-2"><a class="nav-link" href="" data-bs-toggle="modal" data-bs-target="#positifModal">Je suis positif !</a></li>
				<%} %>
			<li class="nav-item p-2"><a class="nav-link" href="activity?s=list">Mes activités</a></li>
			<li class="nav-item p-2"><a class="nav-link" href="friend">Mes amis</a></li>
				<%if (user.isAdmin()) { %>
				<li class="nav-item p-2"><a class="nav-link" href="admin">Administrateur</a></li>
				<%}%>
			<%} %>
		</ul>
	</div>
	<div class="mx-auto order-0">
		<span class="navbar-brand mb-0 h1">antiCovid</span>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".dual-collapse2">
			<span class="navbar-toggler-icon"></span>
		</button>
	</div>
	<ul class="nav navbar-nav ml-auto w-100 justify-content-end">
		<%if (user != null) {%>
			<li class="nav-item p-2"><a class="nav-link" href="#">Bonjour, <%= user.getLogin() %> !</a></li>
			<li class="nav-item p-2"><a class="nav-link" href="notif">Notifications <span class="badge bg-danger"><%=TableLocator.getNotificationTable().getCount(user) %></span></a></li>
			<li class="nav-item p-2"><a class="nav-link" href="signout">Se déconnecter</a></li>
		<%} else { %>
			<li class="nav-item p-2"><a class="nav-link" href="login">Se connecter</a></li>
			<li class="nav-item p-2"><a class="nav-link" href="signup">S'inscrire</a></li>
		<%} %>
	</ul>
</nav>

<div class="modal fade" id="positifModal" tabindex="-1" aria-labelledby="positifModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Attention !</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <%
        String msg = "";
        String btnLink = "";
        if (user != null) {
        	if (user.isInfected()) {
        		msg = "Si vous n'êtes plus infecté, vous pouvez l'indiquer ici, en cliquant sur le bouton ci-dessous. Faite le uniquement si"
        				+ " vous avez effectué un test qui présente un résultat négatif.";
        		btnLink = "covided?undo=1";
        	} else {
        		msg = "Cette action est à effectuer uniquement si vous êtes certain d'être positif au COVID-19. Cela aura pour effet d'envoyer"
                    	+ " une notification à toutes les personnes qui ont visité les même lieux que vous depuis les 10 derniers jours, afin de les"
                    	+ " prévenir qu'il sont potentiellement cas contact. Les personnes concerné ne sauront pas que vous êtes infecté.";
        		btnLink = "covided";
        	}
        } else { 
        	msg = "Veuillez vous connecter avant de vous déclarer positif !";
        }
        %>
        <p>
        	<%=msg %>
        </p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fermer</button>
        <% if(user!=null) {%> 
        	<a class="btn btn-primary" href="<%=btnLink%>">Je confirme !</a>
        <%} %>
      </div>
    </div>
  </div>
</div>



