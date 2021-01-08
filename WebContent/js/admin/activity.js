$('document').ready(function() {
	$('.btnDel').click(function() {
		var parent = $(this).parent().parent()
		var actID = parent.find("th").text()
		
		var url = location.pathname
		var split = url.split("/")
		split[split.length-1] = "admin?s=activity&id=" + actID
		url = split.join("/")
		
		// Exécution de la requête post
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
		var title = $('#title').val()
		var sd = $('#startDate').val()
		var ed = $('#endDate').val()
		var loc = $('#location').val()
		var user = $('#user').val()
		
		var url = location.pathname
		var split = url.split("/")
		split[split.length-1] = "admin?s=activity&title=" + title + "&sd=" + sd + "&ed=" + ed + "&loc=" + loc + "&user=" + user
		url = split.join("/")
		
		// Exécution de la requête post
		$.ajax({
			method: "PUT",
			url : url,
			dataType : 'json',
			success: function(data, result) {
				if (data.result) {
					var tbody = $('table').find('tbody')
					var tr = $('<tr></tr>')
					tr.append(`<th scope="row">${data.id}</th>`)
					tr.append(`<td>${data.title}</td>`)
					tr.append(`<td>${data.sd}</td>`)
					tr.append(`<td>${data.ed}</td>`)
					tr.append(`<td>${data.loc}</td>`)
					tr.append(`<td>${data.user}</td>`)
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
