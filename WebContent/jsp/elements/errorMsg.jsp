<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% if(request.getAttribute("error") != null) { %>
<div class="alert alert-danger alert-dismissible fade show"role="alert">
	${error}
	<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
<%}%>