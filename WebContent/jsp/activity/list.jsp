<%@ page import="sql.Utils"%>
<%@ page import="entities.Activity"%>
<%@ page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../elements/message.jsp"%>

<a href="<%=request.getContextPath()%>/activity?s=add" class="btn btn-outline-success mb-3 col-lg-12">+ Ajouter une activité</a>

<%
	List<Activity> activities = (List<Activity>) request.getAttribute("list");
if (activities.size() > 0) {
%>
<div class="p-4 d-flex flex-column bd-highlight lg-3 bg-secondary bg-gradient text-white rounded-3">
	<div class="list-group">
	<%
		for (Activity activity : activities) {
	%>
		<a href="activity?s=show&id=<%=activity.getId() %>" class="list-group-item list-group-item-action">
		    <div class="d-flex w-100 justify-content-between">
				<h5 class="mb-1"></h5>
				<small class="text-muted"><%=Utils.timestampToString(activity.getStartDate())%> - <%=Utils.timestampToString(activity.getEndDate())%></small>
		    </div>
		    <p class="mb-1"><Strong><%=activity.getTitle()%></Strong> <br> <%=activity.getLocation().getAddress()%></p>
			<small class="text-muted"><%=activity.getLocation().getGPS()%></small>
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