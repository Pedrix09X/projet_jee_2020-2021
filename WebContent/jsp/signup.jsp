<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container">
	<%@include file="elements/message.jsp" %>

	<form method="post" action="signup">
		<div class="form-group row mb-2">
			<label class="col-lg-4 col-form-label" for="login">Nom
				d'utilisateur</label>
			<div class="col-lg-8">
				<input id="login" name="login" type="text" required="required"
					class="form-control">
			</div>
		</div>
		<div class="form-group row mb-2">
			<label for="lastName" class="col-lg-4 col-form-label">Nom</label>
			<div class="col-lg-8">
				<input id="lastName" name="lastName" type="text" required="required"
					class="form-control">
			</div>
		</div>
		<div class="form-group row mb-2">
			<label for="firstName" class="col-lg-4 col-form-label">Prénom</label>
			<div class="col-lg-8">
				<input id="firstName" name="firstName" type="text"
					required="required" class="form-control">
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
			<label for="passConfirmed" class="col-lg-4 col-form-label">Confirmer</label>
			<div class="col-lg-8">
				<input id="passConfirmed" name="passConfirmed" type="password"
					required="required" class="form-control">
			</div>
		</div>
		<div class="form-group row mb-2">
			<label for="birthDate" class="col-lg-4 col-form-label">Date de
				naissance (YYYY-MM-DD)</label>
			<div class="col-lg-8">
				<input id="birthDate" name="birthDate" type="date"
					required="required" class="form-control">
			</div>
		</div>
		<div class="form-group row mb-2">
			<div class="offset-4 col-lg-8">
				<button name="submit" type="submit" class="btn btn-primary">S'inscrire</button>
			</div>
		</div>
	</form>
</div>