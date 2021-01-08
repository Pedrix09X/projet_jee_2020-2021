$('document').ready(function() {
	$('.btnDel').click(function() {
		var parent = $(this).parent().parent()
		var userID = parent.find("th").text()
		
		var url = location.pathname
		var split = url.split("/")
		split[split.length-1] = "admin?s=user&id=" + userID
		url = split.join("/")
		
		// Execution de la requête post
		$.ajax({
			method: "DELETE",
			url : url,
			dataType : 'json',
			success: function(data, result) {
				if (data.result) {
					parent.remove()
				}
			}
	    });
	})
	
	$('.btnAdd').click(function() {
		var login = $('#login').val()
		var password = $('#password').val()
		var first = $('#first').val()
		var last = $('#last').val()
		var birth = $('#birth').val()
		var infected = $('#infected').val()
		var contact = $('#contact').val()
		var admin = $('#admin').val()
		
		var url = location.pathname
		var split = url.split("/")
		split[split.length-1] = "admin?s=user&login=" + login + "&password=" + password + "&first=" + first + "&last=" + last + "&birth=" + birth + "&infected=" + infected + "&contact=" + contact + "&admin=" + admin
		url = split.join("/")
		
		// Execution de la requête post
		$.ajax({
			method: "PUT",
			url : url,
			dataType : 'json',
			success: function(data, result) {
				if (data.result) {
					var tbody = $('table').find('tbody')
					var tr = $('<tr></tr>')
					tr.append(`<th scope="row">${data.id}</th>`)
					tr.append(`<td>${data.login}</td>`)
					tr.append(`<td>${data.password}</td>`)
					tr.append(`<td>${data.first}</td>`)
					tr.append(`<td>${data.last}</td>`)
					tr.append(`<td>${data.birth}</td>`)
					tr.append(`<td>${data.infected}</td>`)
					tr.append(`<td>${data.contact}</td>`)
					tr.append(`<td>${data.admin}</td>`)
					tr.append('<td><button class="btn btn-danger btnDel">-</button></td>')
					tbody.append(tr)
					$('.errorText').prop("hidden", true)
				}
			},
			error: function(data, status) {
				$('.errorText').prop("hidden", false)
			}
	    });
	})
})