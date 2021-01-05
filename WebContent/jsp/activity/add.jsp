<%@ page import="java.util.List" %>
<%@ page import="entities.Location" %>

<%@include file="../elements/message.jsp"%>

<div class="p-4 sm-3">
	<p class="text-center fw-light fst-italic">
		Entrez un titre d'activité pour reduire la liste des lieux. Si aucun lieu ne semble correspondre à votre activité,
		vous pouvez ajouter un lieu en cochant la case "Créer un nouveau lieu".
	</p>
</div>

<form method="post" action="activity">
	<div class="bg-secondary bg-gradient text-white p-4 rounded-3 mb-2">
		<div class="form-group row mb-2">
			<label class="col-lg-4 col-form-label" for="login">Titre</label>
			<div class="col-lg-8">
				<input id="activityName" name="activityName" type="text" required="required"
					class="form-control">
			</div>
		</div>
		<div class="form-group row mb-2">
			<label for="start" class="col-lg-4 col-form-label">Début de
				l'activité</label>
			<div class="col-lg-8">
				<input id="start" name="start" type="datetime-local" required="required" class="form-control">
			</div>
		</div>
		<div class="form-group row">
			<label for="end" class="col-lg-4 col-form-label">Fin de
				l'activité</label>
			<div class="col-lg-8">
				<input id="end" name="end" type="datetime-local" required="required"
					class="form-control">
			</div>
		</div>
	</div>
	
	<div class="d-lg-flex flex-row justify-content-evenly form-group mb-2">
		<div class="p-4 col-lg-5 border bg-secondary bg-gradient text-white rounded-3 mb-2">
			<div class="text-center mb-4">
				<input class="form-check-input" type="radio" name="radioLoc" id="selectRadio" checked>
				<label class="form-check-label" for="selectRadio">
					Choisir un lieu dans la liste
				</label>
			</div>
			<select class="form-select selectLoc" name="selectLoc" size="8" required="required">
			<%
				List<Location> locations = (List<Location>) request.getAttribute("list");
				for (Location loc: locations) {
			%>
				<option value="<%=loc.getId()%>"><%=loc.getName()%></option>
			<%
				}
			%>
			</select>
		</div>
		
		<div class="p-4 col-lg-5 border bg-secondary bg-gradient text-white rounded-3 mb-2">
			<div class="text-center mb-4">
				<input class="form-check-input" type="radio" name="radioLoc" id="createRadio">
				<label class="form-check-label" for="createRadio">
					Créer un nouveau lieu
				</label>
			</div>
			<div class="createLoc">
				<div class="form-group row mb-2">
					<label for="end" class="col-lg-4 col-form-label">Intitulé</label>
					<div class="col-lg-8">
						<input id="locName" name="locName" type="text" required="required" class="form-control" disabled>
					</div>
				</div>
				<div class="form-group row mb-2">
					<label for="end" class="col-lg-4 col-form-label">Adresse</label>
					<div class="col-lg-8">
						<input id="locAddress" name="locAddress" type="text" required="required" class="form-control" disabled>
					</div>
				</div>
				<div class="form-group row mb-2">
					<label for="end" class="col-lg-4 col-form-label">Coordonées (facultatif)</label>
					<div class="col-lg-8">
						<input id="locGPS" name="locGPS" type="text" class="form-control" disabled>
					</div>
				</div>
				<div class="form-group row mb-2">
					<div class="offset-2">
						<button id="locSubmit" name="locSubmit" type="button" class="btn btn-outline-light col-lg-8 btn-sm"  disabled>Créer le lieu</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="form-group row mb-2">
		<div class="offset-2">
			<button name="submit" type="submit" class="btn btn-primary col-lg-8">Ajouter</button>
		</div>
	</div>
</form>

<script src="<%=request.getContextPath() + "/js/location.js"%>"></script>