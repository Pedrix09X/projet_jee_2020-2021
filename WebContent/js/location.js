$("#selectRadio").change(function (){
	$(".selectLoc").prop("disabled", false)
	$(".createLoc input, .createLoc button").prop("disabled", true)
})

$("#createRadio").change(function (){
	$(".selectLoc").prop("disabled", true)
	$(".createLoc input, .createLoc button").prop("disabled", false)
})

$("#locSubmit").click(function (){
	var url = location.pathname
	var split = url.split("/")
	split[split.length-1] = "location"
	url = split.join("/")
	
	var data = {
		r: "json",
		s: "add",
		name: $("#locName").val(),
		address: $("#locAddress").val(),
		gps: $("#locGPS").val()
	}
	
	$.post({
		url : url,
		data : data,
		dataType : 'json',
		success: updateList
    });
})

function updateList(data, status) {
	$(".selectLoc").prepend("<option value=\"" + data.id + "\">" + data.name + "</option>")
}
