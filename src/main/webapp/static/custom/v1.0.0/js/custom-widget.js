// 弹出确认对话框
// settings = {title, content, confirm, cancel}
function dialogConfirm(settings) {
	var dialogId = "dialog-"+new Date().getTime();
	var dialog = $("<div id='"+dialogId+"' class='dialog-area'></div>");
	var dialogTitle = $("<div class='dialog-area-title'>"+settings.title+"</div>");
	dialog.append(dialogTitle);
	var dialogClose = $("<div class='dialog-area-close'>×</div>");
	dialogClose.attr("title", "关闭");
	dialogClose.click(function() {$("#"+dialogId).remove(); settings.cancel()});
	dialog.append(dialogClose);
	var dialogContent = $("<div class='dialog-area-content'>"+settings.content+"</div>");
	dialog.append(dialogContent);
	$("body").append(dialog);
}

