// Désactiver le formulaire quand "select" est choisit
$("#selectRadio").change(function (){
	$(".selectLoc").prop("disabled", false)
	$(".createLoc input, .createLoc button").prop("disabled", true)
})

// Désactiver la liste quand "create" est choisit
$("#createRadio").change(function (){
	$(".selectLoc").prop("disabled", true)
	$(".createLoc input, .createLoc button").prop("disabled", false)
})

// Envoi une requête au serveur pour ajouter (si possible) un nouveau lieu et récupère le nom et l'id de ce nouveau lieu
$("#locSubmit").click(function (){
	var url = location.pathname
	var split = url.split("/")
	split[split.length-1] = "location"
	url = split.join("/")
	
	// Mise en place des paramêtres de la requête
	var data = {
		r: "json",
		s: "add",
		name: $("#locName").val(),
		address: $("#locAddress").val(),
		gps: $("#locGPS").val()
	}
	
	// Execution de la requête post
	$.post({
		url : url,
		data : data,
		dataType : 'json',
		success: updateList
    });
})

// Traitement du résultat de la requête 
function updateList(data, status) {
	$(".selectLoc").prepend("<option selected value=\"" + data.id + "\">" + data.name + "</option>")	// Ajout le résultat à la liste
	$("#selectRadio").prop("checked", true).change()													// Coche "Choisir un lieu dans la liste"
	$(".createLoc input, .createLoc button").val("")													// Vide tous les champs du formulaire
}
