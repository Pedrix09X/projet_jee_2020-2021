<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container">
	<form method="post" action="signup">
		<div class="form-group row">
			<label class="col-4 col-form-label" for="login">Nom
				d'utilisateur</label>
			<div class="col-8">
				<input id="login" name="login" type="text" required="required"
					class="form-control">
			</div>
		</div>
		<div class="form-group row">
			<label for="lastName" class="col-4 col-form-label">Nom</label>
			<div class="col-8">
				<input id="lastName" name="lastName" type="text" required="required"
					class="form-control">
			</div>
		</div>
		<div class="form-group row">
			<label for="firstName" class="col-4 col-form-label">Prénom</label>
			<div class="col-8">
				<input id="firstName" name="firstName" type="text"
					required="required" class="form-control">
			</div>
		</div>
		<div class="form-group row">
			<label for="pass" class="col-4 col-form-label">Mot de passe</label>
			<div class="col-8">
				<input id="pass" name="pass" type="password" required="required"
					class="form-control">
			</div>
		</div>
		<div class="form-group row">
			<label for="passConfirmed" class="col-4 col-form-label">Confirmer</label>
			<div class="col-8">
				<input id="passConfirmed" name="passConfirmed" type="password"
					required="required" class="form-control">
			</div>
		</div>
		<div class="form-group row">
			<label for="birthDate" class="col-4 col-form-label">Date de
				naissance</label>
			<div class="col-8">
				<input id="birthDate" name="birthDate" type="date"
					required="required" class="form-control">
			</div>
		</div>
		<div class="form-group row">
			<div class="offset-4 col-8">
				<button name="submit" type="submit" class="btn btn-primary">S'inscrire</button>
			</div>
		</div>
	</form>
</div>
