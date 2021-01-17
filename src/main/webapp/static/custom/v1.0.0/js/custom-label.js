$(document).ready(function() {
	// input
	$(".input-area input").bind('input change propertychange', function() {
		var name = $(this).attr("name");
		$("span[data-v='"+name+"']").html($(this).val().length);
	});
	
	// textarea
	$(".textarea-area textarea").bind('input change propertychange', function() {
		var name = $(this).attr("name");
		$("span[data-v='"+name+"']").html($(this).val().length);
	}).keydown(function(event) {
		if (event.keyCode == 13) return false;
	});
});
