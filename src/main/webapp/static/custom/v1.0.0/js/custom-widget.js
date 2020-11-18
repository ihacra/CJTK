// 提示窗口
function toast1(content, autoClose) {
	var type = "toast-success";
	if (content.indexOf("失败") != -1) {
		type = "toast-error";
	}
	var toast = $("<div class='toast-area "+type+"'>"+content+"</div>");
	var toastClose = $("<div class='toast-close'>×</div>");
	toastClose.click(function() {$('.toast-area').fadeOut('slow');});
	toast.append(toastClose);
	$("body").append(toast);
	$(".toast-area").slideDown("slow");
	if (autoClose == null || autoClose) {
		setTimeout(function() {
			$(".toast-area").fadeOut("slow");
		}, 4000);
	}
}

// 弹出确认对话框
// settings = {trigger, title, content, confirm, cancel}
function dialog2(settings) {
	if (settings.trigger != null) $("#"+settings.trigger).attr("disabled", true);
	if (settings.title == null) settings.title = "信息";
	var dialogId = "dialog-"+new Date().getTime();
	var dialog = $("<div id='"+dialogId+"' class='dialog-area'></div>");
	var dialogTitle = $("<div class='dialog-area-title'>"+settings.title+"</div>");
	dialog.append(dialogTitle);
	var dialogClose = $("<div class='dialog-area-close btn'>×</div>");
	dialogClose.attr("title", "关闭");
	dialogClose.click(function() {
		if (settings.cancel != null) settings.cancel(); 
		$("#"+dialogId).remove(); 
		if (settings.trigger != null) $("#"+settings.trigger).attr("disabled", false);
	});
	dialog.append(dialogClose);
	var dialogContent = $("<div class='dialog-area-content'>"+settings.content+"</div>");
	dialog.append(dialogContent);
	var dialogCancel = $("<div class='dialog-area-btn btn-cancel btn'>取消</div>")
	dialogCancel.click(function() {dialogClose.click()});
	dialog.append(dialogCancel);
	var dialogConfirm = $("<div class='dialog-area-btn btn-confirm btn'>确认</div>")
	dialogConfirm.click(function() {
		settings.confirm(); 
		$("#"+dialogId).remove(); 
		if (settings.trigger != null) $("#"+settings.trigger).attr("disabled", false);
	});
	dialog.append(dialogConfirm);
	$("body").append(dialog);
}
