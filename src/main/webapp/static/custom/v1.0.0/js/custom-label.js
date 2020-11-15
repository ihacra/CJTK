$(document).ready(function() {
	// input
	$(".input-area input").keyup(function() {
		var name = $(this).attr("name");
		$("span[data-v='"+name+"']").html($(this).val().length);
	});
	
	// textarea
	$(".textarea-area textarea").keyup(function() {
		var name = $(this).attr("name");
		$("span[data-v='"+name+"']").html($(this).val().length);
	}).keydown(function(event) {
		if (event.keyCode == 13) return false;
	});
});
