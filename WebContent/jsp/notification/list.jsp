<%@page import="tables.NotificationTable"%>
<%@ page import="sql.Utils"%>
<%@ page import="entities.Notification"%>
<%@ page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../elements/message.jsp"%>

<%
	List<Notification> notifs = (List<Notification>) request.getAttribute("list");
if (notifs!=null && notifs.size() > 0) {
%>
<div class="p-4 d-flex flex-column bd-highlight lg-3 bg-secondary bg-gradient text-white rounded-3">
	<div class="list-group">
	<%
		for (Notification notif : notifs) {
	%>
		<a href="<%=notif.getAction() %>" class="list-group-item list-group-item-action">
		    <div class="d-flex w-100 justify-content-between">
				<h5 class="mb-1"><%=notif.getText()%></h5>
				<small class="text-muted"><%=Utils.dateToString(notif.getReceivedDate())%></small>
		    </div>
		    <% if (notif.getType() == NotificationTable.ASK_FRIEND) {%>
		    	<button class="btn btn-outline-light col-lg-5 btn-sm">Accepter</button>
		    	<button class="btn btn-outline-light col-lg-5 btn-sm">Refuser</button>
		    <%} %>
		</a>
	<%
		}
	%>
	</div>
</div>
<%
	} else {
%>
<div class="p-4 sm-3">
	<p class="text-center fw-light fst-italic">
		Vous n'avez pas encore créé d'activité. <a href="activity?s=add"
			class="link-primary">CLiquez ici</a> pour en créer une.
	</p>
</div>
<%
	}
%>