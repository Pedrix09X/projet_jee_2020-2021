<%@page import="sql.Utils"%>
<%@page import="tables.LocationTable"%>
<%@page import="entities.Location"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@include file="../elements/message.jsp"%>

<form>
	<div class="d-lg-flex flex-lg-rown justify-content-between mb-3">
		<div class="col-lg-3">
			<label for="name" class="form-label"><%=LocationTable.COLUMN_NAME %></label>
			<input type="text" class="form-control" id="name">
		</div>
		<div class="col-lg-4">
			<label for="address" class="form-label"><%=LocationTable.COLUMN_ADDRESS %></label>
			<input type="text" class="form-control" id="address">
		</div>
		<div class="col-lg-4">
			<label for="gps" class="form-label"><%=LocationTable.COLUMN_GPS %></label>
			<input type="text" class="form-control" id="gps">
		</div>
		<button type="button" class="btnAdd btn btn-success align-self-baseline mt-auto">+</button>
	</div>
	<p class="errorText text-center text-danger" hidden>Une erreur est survenu lors de l'ajout de ce lieu. Vérifiez les données et rééssayez.</p>
</form>

<br>

<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th scope="col"><%=LocationTable.COLUMN_ID %></th>
			<th scope="col"><%=LocationTable.COLUMN_NAME %></th>
			<th scope="col"><%=LocationTable.COLUMN_ADDRESS %></th>
			<th scope="col"><%=LocationTable.COLUMN_GPS %></th>
			<th scope="col"></th>
		</tr>
	</thead>
	<tbody>
	<%
	List<Location> locations = (List<Location>)request.getAttribute("list");
	for (Location location : locations) { %>
		<tr>
			<th scope="row"><%=location.getId()%></th>
			<td><%=location.getName()%></td>
			<td><%=location.getAddress()%></td>
			<td><%=location.getGPS()%></td>
			<td><button class="btn btn-danger btnDel">-</button></td>
		</tr>
	<%} %>
	</tbody>
</table>

<script src="<%=request.getContextPath()%>/js/admin/location.js"></script>