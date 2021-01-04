<%@ page import="entities.User"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
	<div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item p-2"><a class="nav-link" href="<%=request.getContextPath()%>">Accueil</a></li>
			<li class="nav-item p-2"><a class="nav-link" href="" data-bs-toggle="modal" data-bs-target="#positifModal">Je suis positif !</a></li>
			<%
			User user = (User) session.getAttribute("user");
			if (user != null) {
			%>
			<li class="nav-item p-2"><a class="nav-link" href="activity?s=list">Liste de mes activités</a></li>
			<li class="nav-item p-2"><a class="nav-link" href="activity?s=add">Ajouter une activité</a></li>
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
        <h5 class="modal-title">Se déclarer positif</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <%
        if (user != null) {
        %>
        <p>
        	Cette action est à effectuer uniquement si vous êtes certain d'être positif au COVID-19. Cela aura pour effet d'envoyer
        	une notification à toutes les personnes qui ont visité les même lieux que vous depuis les 10 derniers jours, afin de les
        	prévenir qu'il sont potentiellement cas contact. Les personnes concerné ne sauront pas que vous êtes infecté.
        </p>
        <%
        } else { 
        %>
        <p>
        	Veuillez vous connecter avant de vous déclarer positif !
        </p>
        <%} %>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fermer</button>
        <% if(user!=null) {%> 
        	<a class="btn btn-primary" href="covided">Je confirme !</a>
        <%} %>
      </div>
    </div>
  </div>
</div>



