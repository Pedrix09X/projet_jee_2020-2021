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
	$('.diffCase p').text(transformNumber(france.confirmed_diff))
	$('.diffDeath p').text(transformNumber(france.deaths_diff))
	$('.diffRecov p').text(transformNumber(france.recovered_diff))
	
	// Stats totals
	$('.totCase p').text(transformNumber(france.confirmed))
	$('.totDeath p').text(transformNumber(france.deaths))
	$('.totRecov p').text(transformNumber(france.recovered))
	
	// Date de maj
	var date = new Date(france.last_update)
	var options = {year: 'numeric', month : '2-digit', day : '2-digit', hour : '2-digit', minute : '2-digit', second : '2-digit'}
	$('.lastUpdate').append("Dernière mise à jour:<br>" + date.toLocaleDateString('fr-FR', options))
}

function transformNumber(number) {
	number = number.toString().split("").reverse().join("");
	var val = "";
	for (var i = 0; i < number.length; i++) {
		if (i % 3 == 0) {
			val += " "
		}
		
		val += number.charAt(i)
	}
	return val.split("").reverse().join("");
}