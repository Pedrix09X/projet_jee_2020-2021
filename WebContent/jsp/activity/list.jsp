<%@ page import="sql.Utils"%>
<%@ page import="entities.Activity"%>
<%@ page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../elements/message.jsp"%>

<%
	List<Activity> activities = (List<Activity>) request.getAttribute("list");
if (activities.size() > 0) {
%>
<div class="p-4 d-flex flex-column bd-highlight sm-3">
	<%
		for (Activity activity : activities) {
	%>
	<div
		class="d-flex justify-content-lg-evenly shadow p-3 mb-4 bg-secondary bg-gradient text-white rounded-3">
		<div class="p-2"><%=activity.getTitle()%>: </div>
		<div class="p-2 flex-lg-fill"><%=activity.getLocation().getAddress()%></div>
		<div class="p-2"><%=Utils.dateToString(activity.getStartDate())%></div>
		<div class="p-2"> - </div>
		<div class="p-2"><%=Utils.dateToString(activity.getEndDate())%></div>
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
		Vous n'avez pas encore créé d'activité. <a href="activity?s=add"
			class="link-primary">CLiquez ici</a> pour en créer une.
	</p>
</div>
<%
	}
%>