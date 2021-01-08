<%@page import="sql.Utils"%>
<%@page import="tables.ActivityTable"%>
<%@page import="entities.Activity"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@include file="../elements/message.jsp"%>

<form>
	<div class="d-lg-flex flex-lg-rown justify-content-between mb-3">
		<div class="col-lg-3">
			<label for="title" class="form-label"><%=ActivityTable.COLUMN_TITLE %></label> 
			<input type="text" class="form-control" id="title">
		</div>
		<div class="col-lg-3">
			<label for="startDate" class="form-label"><%=ActivityTable.COLUMN_STARTDATE %></label>
			<input type="datetime-local" class="form-control" id="startDate">
		</div>
		<div class="col-lg-3">
			<label for="endDate" class="form-label"><%=ActivityTable.COLUMN_ENDDATE %></label>
			<input type="datetime-local" class="form-control" id="endDate">
		</div>
		<div class="col-lg-1">
			<label for="location" class="form-label"><%=ActivityTable.COLUMN_LOCATION %></label>
			<input type="number" class="form-control" id="location">
		</div>
		<div class="col-lg-1">
			<label for="user" class="form-label"><%=ActivityTable.COLUMN_USER %></label>
			<input type="number" class="form-control" id="user">
		</div>
		<button type="button" class="btnAdd btn btn-success align-self-baseline mt-auto">+</button>
	</div>
	<p class="errorText text-center text-danger" hidden>Une erreur est survenu lors de l'ajout de cette activité. Vérifiez les données et rééssayez.</p>
</form>

<br>

<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th scope="col"><%=ActivityTable.COLUMN_ID %></th>
			<th scope="col"><%=ActivityTable.COLUMN_TITLE %></th>
			<th scope="col"><%=ActivityTable.COLUMN_STARTDATE %></th>
			<th scope="col"><%=ActivityTable.COLUMN_ENDDATE %></th>
			<th scope="col"><%=ActivityTable.COLUMN_LOCATION %></th>
			<th scope="col"><%=ActivityTable.COLUMN_USER %></th>
			<th scope="col"></th>
		</tr>
	</thead>
	<tbody>
	<%
	List<Activity> activities = (List<Activity>)request.getAttribute("list");
	for (Activity activity : activities) { %>
		<tr>
			<th scope="row"><%=activity.getId()%></th>
			<td><%=activity.getTitle()%></td>
			<td><%=Utils.timestampToString(activity.getStartDate())%></td>
			<td><%=Utils.timestampToString(activity.getEndDate())%></td>
			<td><%=activity.getLocation().getId()%></td>
			<td><%=activity.getUser().getId()%></td>
			<td><button class="btn btn-danger btnDel">-</button></td>
		</tr>
	<%} %>
	</tbody>
</table>

<script src="<%=request.getContextPath()%>/js/admin/activity.js"></script>