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
<div class="d-flex flex-column bd-highlight lg-3">
	<% for (Notification notif : notifs) { 
		String colorClass = "";
		String colorText = "";
		String buttons = "";
		if (notif.getType() == NotificationTable.CONTACT) {
			if (notif.isSeen())
				colorClass = "border-danger border-3";
			else {
				colorClass = "bg-danger";
				colorText = "text-white";
			}
		} else if (notif.getType() == NotificationTable.ASK_FRIEND){
			if (notif.isSeen())
				colorClass = "border-warning border-3";
			else {
				colorClass = "bg-warning";
				buttons = ""
					+ "<div class=\"d-flex justify-content-evenly\">"
					+ "		<button class=\"btn btn-success col-lg-4 btnAccept\">Accepter</button>"
					+ "		<button class=\"btn btn-danger col-lg-4 btnRefuse\">Refuser</button>"
					+ "</div>";
			}
		} else {
			if (notif.isSeen()) 
				colorClass = "border-secondary border-3";
			else {
				colorClass = "bg-secondary";
				colorText = "text-white";
			}
		}
	%>
	<div class="p-4 mb-2 border rounded-3 bg-gradient <%=colorClass%> <%=colorText%>">
		<div class="d-flex w-100 justify-content-between mb-2">
			<h5 class="mb-1"><%=notif.getText()%></h5>
			<small class="<%=colorText%>"><%=Utils.dateToString(notif.getReceivedDate())%></small>
		</div>
		<%=buttons %>
	</div>
	<% } %>
</div>
<%
} else {
%>
<div class="p-4 sm-3">
	<p class="text-center fw-light fst-italic">
		Vous n'avez pas de notification.
	</p>
</div>
<%
	}
%>


<!-- 
<div class="list-group">
	<%
		for (Notification notif : notifs) {
	%>
		<a href="<%=notif.getAction() %>" class="list-group-item list-group-item-action mb-2">
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
-->