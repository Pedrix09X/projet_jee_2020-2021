<%@ page import="sql.Utils"%>
<%@ page import="entities.User"%>
<%@ page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../elements/message.jsp"%>

<form class="d-flex justify-content-center">
	<div class="d-flex col-lg-6">
		<div class="dropdown col-lg-12 me-2">
			<input class="form-control dropdown-toggle" autocomplete="off" type="search" id="dropdownMenuButton" data-bs-toggle="dropdown" placeholder="Entrez un nom d'utilisateur..." aria-label="Add">
			<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
			</ul>
		</div>
		<button class="btnAdd btn btn-outline-primary" type="button">Ajouter</button>
	</div>
</form>
<p class="resultText text-center" hidden>Ce nom d'utilisateur n'existe pas.</p>

<br>
<%
	List<User> friends = (List<User>) request.getAttribute("list");
if (friends != null && friends.size() > 0) {
%>
<div class="p-2 d-lg-flex flex-lg-wrap">
	<%
		for (User friend : friends) {
	%>
	<div class="d-lg-flex bg-secondary bg-gradient text-white rounded-3 shadow p-2 col-lg-4 me-2">
		<input class="userID" type="hidden" value="<%=friend.getId()%>"/>
		<button type="button" class="btnDelete btn-close p-2" aria-label="Delete"></button>
		<h5 class="p-2"><%=friend.getLogin()%></h5>
		<span class="p-2"><%=friend.getFirstName()%> <%=friend.getLastName()%></span>
	</div>
	<%
		}
	%>
</div>
<%
	} else {
%>
<div class="p-4 sm-3">
	<p class="text-center fw-light fst-italic">
		Vous n'avez pas d'amis :(
	</p>
</div>
<%
	}
%>

<script src="<%=request.getContextPath()%>/js/searchFriends.js"></script>