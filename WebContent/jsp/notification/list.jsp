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
		int friendID = 0;
		if (notif.getFriend() != null) {
			friendID = notif.getFriend().getId();
		}
		
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
	<div class="notif p-4 mb-2 border rounded-3 bg-gradient <%=colorClass%> <%=colorText%>">
		<input type="hidden" class="notifID" value="<%=notif.getId()%>"/>
		<input type="hidden" class="notifSeen" value="<%=notif.isSeen()%>"/>
		<input type="hidden" class="notifType" value="<%=notif.getType()%>"/>
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

<script src="<%=request.getContextPath()%>/js/notification.js"></script>