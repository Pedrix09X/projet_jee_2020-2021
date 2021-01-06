$('document').ready(function() {

	$(".btnAccept").click(function() {
		acceptFriend($(this))
	})

	$(".btnRefuse").click(function() {
		var notif = $(this).parent().parent()
		markAsSeen(notif, true)
	})

	$(".notif").mouseenter(function() {
		markAsSeen($(this), false)
	})
})

// Marque la notification comme lu. Si la notification est une demande d'amis, alors il faut passer le paramêtre force à true.
function markAsSeen(notif, force) {
	var notifID = notif.find(".notifID").val()
	var notifSeen = notif.find(".notifSeen").val()
	var notifType = notif.find(".notifType").val()

	if (notifSeen == "false" && (notifType != "1" || force)) {
		// Préparation de l'url
		var url = location.pathname
		var split = url.split("/")
		split[split.length - 1] = "notif"
		url = split.join("/")

		// Préparation des données à envoyer
		var data = {
			r: "json",
			s: "seen",
			id: notifID
		}

		$.post({
			url: url,
			data: data,
			dataType: 'json',
			success: updateNotif
		});
	}
}

function updateNotif(data, status) {
	if (data.result) {
		var notif = $(".notif :input[value=\"" + data.id + "\"]").parent()
		var notifType = notif.find(".notifType").val()
		var listClass = notif.attr("class")

		if (listClass.includes("bg-danger")) {
			notif.removeClass("bg-danger text-white")
			notif.addClass("border-danger border-3")
		} else if (listClass.includes("bg-warning")) {
			notif.removeClass("bg-warning text-white")
			notif.addClass("border-warning border-3")
		} else if (listClass.includes("bg-secondary")) {
			notif.removeClass("bg-secondary text-white")
			notif.addClass("border-secondary border-3")
		}

		notif.find(".notifSeen").val("true")
		
		if (notifType == "1") {
			notif.find(".btnAccept").remove()
			notif.find(".btnRefuse").remove()
		}
	}
}

function acceptFriend(btn) {
	var notif = btn.parent().parent()
	var notifID = notif.find(".notifID").val()

	// Préparation de l'url
	var url = location.pathname
	var split = url.split("/")
	split[split.length - 1] = "friend"
	url = split.join("/")

	// Préparation des données à envoyer
	var data = {
		r: "json",
		s: "add",
		id: notifID
	}

	$.post({
		url: url,
		data: data,
		dataType: 'json',
		success: function(data, status) {
			if (data.result) {
				markAsSeen(notif, true)
			}
		}
	});

}