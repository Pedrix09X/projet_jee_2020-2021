$('document').ready(function() {
	$('.btnDel').click(function() {
		var parent = $(this).parent().parent()
		var locID = parent.find("th").text()
		
		var url = location.pathname
		var split = url.split("/")
		split[split.length-1] = "admin?s=location&id=" + locID
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
		var name = $('#name').val()
		var address = $('#address').val()
		var gps = $('#gps').val()
		
		var url = location.pathname
		var split = url.split("/")
		split[split.length-1] = "admin?s=location&name=" + name + "&address=" + address + "&gps=" + gps
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
					tr.append(`<td>${data.name}</td>`)
					tr.append(`<td>${data.address}</td>`)
					tr.append(`<td>${data.gps}</td>`)
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