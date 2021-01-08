$('document').ready(function() {
	$('input[type="search"]').keyup(function() {
		var url = location.pathname
		var split = url.split("/")
		split[split.length - 1] = "find"
		url = split.join("/")

		// Mise en place des paramètres de la requête
		var partial = $(this).val()

		// Exécution de la requête post
		if (partial !== "" && partial !== " ") {
			var data = {
				partialLogin: partial,
			}

			$.post({
				url: url,
				data: data,
				dataType: 'json',
				success: updateList,
				error: updateList
			});
		}
	})

	$('.btnAdd').click(function() {
		var search = $(this).parent().find('input[type="search"]')
		var login = search.val()

		var url = location.pathname
		var split = url.split("/")
		split[split.length - 1] = "notif"
		url = split.join("/")

		// Exécution de la requête post
		if (login !== "" && login !== " ") {
			var data = {
				s: "ask",
				user: login
			}
			$.post({
				url: url,
				data: data,
				dataType: 'json',
				success: updateFriends,
				error: updateFriends
			}); 
		}
	})

	$('.btnDelete').click(function() {
		var friend = $(this).parent()
		var id = friend.find('.userID').val()

		var url = location.pathname
		var split = url.split("/")
		split[split.length-1] = "friend?friendID="+id
		url = split.join("/")

		$.ajax({
			method: "DELETE",
			url: url,
			dataType: 'json',
			success: function(data, status) {
				console.log(data)
				if (data.result) {
					friend.remove()
				}
			}
		});
	})
})

function updateFriends(data, status) {
	var result = data.result
	var resultText = $('.resultText')
	if (result) {
		resultText.text("La demande a été envoyée avec succès !")
		resultText.removeClass("text-danger")
		resultText.addClass("text-success")
	} else {
		resultText.text("Une erreur s'est produite. Vérifiez que le nom d'utilisateur existe.")
		resultText.addClass("text-danger")
		resultText.removeClass("text-success")
	}

	resultText.removeAttr('hidden', false)
}

function updateList(data, status) {
	var dropdown = $(".dropdown-menu")
	dropdown.empty()
	$.each(data, function(i, val) {
		var li = $('<li class="dropdown-item">' + val + '</li>')
		li.click(function() {
			dropdown.parent().find('input[type="search"]').val(li.text())
		})
		dropdown.prepend(li)
	})
}

