<%@page import="sql.Utils"%>
<%@page import="tables.UserTable"%>
<%@page import="entities.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@include file="../elements/message.jsp"%>

<form>
	<div class="d-lg-flex flex-lg-rown justify-content-between mb-3">
		<div class="col-lg-1">
			<label for="login" class="form-label"><%=UserTable.COLUMN_LOGIN %></label> 
			<input type="text" class="form-control" id="login">
		</div>
		<div class="col-lg-1">
			<label for="password" class="form-label"><%=UserTable.COLUMN_PASSWORD %></label>
			<input type="password" class="form-control" id="password">
		</div>
		<div class="col-lg-1">
			<label for="first" class="form-label"><%=UserTable.COLUMN_FIRSTNAME %></label>
			<input type="text" class="form-control" id="first">
		</div>
		<div class="col-lg-1">
			<label for="last" class="form-label"><%=UserTable.COLUMN_LASTNAME %></label>
			<input type="text" class="form-control" id="last">
		</div>
		<div class="col-lg-2">
			<label for="birth" class="form-label"><%=UserTable.COLUMN_BIRTHDATE %></label>
			<input type="date" class="form-control" id="birth">
		</div>
		<div class="col-lg-1">
			<div class="form-check align-bottom mt-auto">
				<input type="checkbox" class="form-check-input" id="infected">
				<label for="infected" class="form-check-label"><%=UserTable.COLUMN_INFECTED %></label>
			</div>
		</div>
		<div class="col-lg-1">
			<div class="form-check mt-auto">
				<input type="checkbox" class="form-check-input" id="contact">
				<label for="contact" class="form-check-label"><%=UserTable.COLUMN_CONTACT %></label>
			</div>
		</div>
		<div class="col-lg-1">
			<div class="form-check mt-auto">
				<input type="checkbox" class="form-check-input" id="admin">
				<label for="admin" class="form-check-label"><%=UserTable.COLUMN_ADMIN %></label>
			</div>
		</div>
		<button type="button" class="btnAdd btn btn-success align-self-baseline mt-auto">+</button>
	</div>
	<p class="errorText text-center text-danger" hidden>Une erreur est survenu lors de l'ajout de cet utilisateur. Vérifiez les données et rééssayez.</p>
</form>

<br>

<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th scope="col"><%=UserTable.COLUMN_ID %></th>
			<th scope="col"><%=UserTable.COLUMN_LOGIN %></th>
			<th scope="col"><%=UserTable.COLUMN_PASSWORD %></th>
			<th scope="col"><%=UserTable.COLUMN_FIRSTNAME %></th>
			<th scope="col"><%=UserTable.COLUMN_LASTNAME %></th>
			<th scope="col"><%=UserTable.COLUMN_BIRTHDATE %></th>
			<th scope="col"><%=UserTable.COLUMN_INFECTED %></th>
			<th scope="col"><%=UserTable.COLUMN_CONTACT %></th>
			<th scope="col"><%=UserTable.COLUMN_ADMIN %></th>
			<th scope="col"></th>
		</tr>
	</thead>
	<tbody>
	<%
	List<User> users = (List<User>)request.getAttribute("list");
	for (User user : users) { %>
		<tr>
			<th scope="row"><%=user.getId()%></th>
			<td><%=user.getLogin()%></td>
			<td>***</td>
			<td><%=user.getFirstName()%></td>
			<td><%=user.getLastName()%></td>
			<td><%=Utils.dateToString(user.getBirthDate())%></td>
			<td><%=user.isInfected()%></td>
			<td><%=user.isContact()%></td>
			<td><%=user.isAdmin()%></td>
			<td><button class="btn btn-danger btnDel">-</button></td>
		</tr>
	<%} %>
	</tbody>
</table>

<script src="<%=request.getContextPath()%>/js/admin/user.js"></script>