<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@include file="elements/message.jsp"%>

<form method="post" action="login">
	<div class="form-group row mb-2">
		<label class="col-lg-4 col-form-label" for="login">Nom
			d'utilisateur</label>
		<div class="col-lg-8">
			<input id="login" name="login" type="text" required="required"
				class="form-control">
		</div>
	</div>
	<div class="form-group row mb-2">
		<label for="pass" class="col-lg-4 col-form-label">Mot de passe</label>
		<div class="col-lg-8">
			<input id="pass" name="pass" type="password" required="required"
				class="form-control">
		</div>
	</div>
	<div class="form-group row mb-2">
		<div class="offset-4 col-lg-8">
			<button name="submit" type="submit" class="btn btn-primary">Se
				connecter</button>
		</div>
	</div>
</form>