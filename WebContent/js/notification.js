$('document').ready(function(){
	$(".notif").mouseenter(function(){
		var notifID = $(this).find(".notifID").val()
		var notifSeen = $(this).find(".notifSeen").val()
		var notifType = $(this).find(".notifType").val()
		
		if (notifSeen == "false" && notifType != "1") {
			// Préparation de l'url
			var url = location.pathname
			var split = url.split("/")
			split[split.length-1] = "notif"
			url = split.join("/")

			// Préparation des données à envoyer
			var data = {
				r: "json",
				s: "seen",
				id: notifID
			}
			
			$.post({
				url : url,
				data : data,
				dataType : 'json',
				success: updateNotif,
				error: updateNotif
		    });
		}
	})
})

function updateNotif(data, status) {
	if (data.result) {
		var notif = $(".notif :input[value=\"" + data.id + "\"]").parent()
		var listClass = notif.attr("class")
		
		if (listClass.includes("bg-danger")) {
			notif.removeClass("bg-danger text-white")
			notif.addClass("border-danger border-3")
		} else if (listClass.includes("bg-warning")) {
			notif.removeClass("bg-warning text-white")
			notif.addClass("border-warning border-3")
		} else if (listClass.includes("bg-secondary")){
			notif.removeClass("bg-secondary text-white")
			notif.addClass("border-secondary border-3")
		}
		
		notif.find(".notifSeen").val("true")
	}
}