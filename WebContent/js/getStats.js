$( document ).ready(function() {
	$.get({
		url : 'https://covid-api.com/api/reports?iso=FRA&region_name=France',
		dataType : 'json',
		success : updateStats
    });
});

function updateStats(data, status) {
	var france = data.data[0]
	// Stats depuis la dernière maj
	$('.diffCase').append(france.confirmed_diff + "<br> cas confirmés")
	$('.diffDeath').append(france.deaths_diff + "<br> décès")
	$('.diffRecov').append(france.recovered_diff + "<br> récupérés")
	
	// Stats totals
	$('.totCase').append(france.confirmed + "<br> cas confirmés")
	$('.totDeath').append(france.deaths + "<br> décès")
	$('.totRecov').append(france.recovered + "<br> récupérés")
	
	// Date de maj
	var date = new Date(france.last_update)
	var options = {year: 'numeric', month : '2-digit', day : '2-digit', hour : '2-digit', minute : '2-digit', second : '2-digit'}
	$('.lastUpdate').append("Dernière mise à jour:<br>" + date.toLocaleDateString('fr-FR', options))
}