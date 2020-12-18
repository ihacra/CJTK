// 加载动画
function loading() {
	var content = $("<div class='load_c'>请稍等...</div>");
	var load = $("<div class='load_l'><span></span><span></span><span></span><span></span></div>");
	$("body").append($("<div class='loading-bg'></div>").append(content).append(load));
}

//关闭加载动画
function close() {
	$(".loading-bg").remove();
}

// 提示窗口
function toast(content, autoClose) {
	var type = "toast-success";
	if (content.indexOf("失败") != -1) {
		type = "toast-error";
	}
	var toast = $("<div class='toast-area "+type+"'>"+content+"</div>");
	var toastClose = $("<div class='toast-close'>×</div>");
	toastClose.click(function() {$('.toast-area').fadeOut('slow');});
	toast.append(toastClose);
	$("body").append(toast);
	$(".toast-area").fadeIn("slow");
	if (autoClose == null || autoClose) {
		setTimeout(function() {
			$(".toast-area").fadeOut("slow");
		}, 4000);
	}
}

// 弹出确认对话框
// settings = {title, content, confirm, cancel}
function dialog(settings) {
	if (settings.title == null) settings.title = "信息";
	var dialogId = "dialog-"+new Date().getTime();
	var dialog = $("<div class='dialog-area'></div>");
	var dialogTitle = $("<div class='dialog-area-title'>"+settings.title+"</div>");
	dialog.append(dialogTitle);
	var dialogClose = $("<div class='dialog-area-close btn'>×</div>");
	dialogClose.attr("title", "关闭");
	dialogClose.click(function() {
		if (settings.cancel != null) settings.cancel(); 
		$("#"+dialogId).remove(); 
	});
	dialog.append(dialogClose);
	var dialogContent = $("<div class='dialog-area-content'>"+settings.content+"</div>");
	dialog.append(dialogContent);
	var dialogCancel = $("<div class='dialog-area-btn btn-cancel btn'>取消</div>")
	dialogCancel.click(function() {dialogClose.click()});
	dialog.append(dialogCancel);
	var dialogConfirm = $("<div class='dialog-area-btn btn-confirm btn'>确认</div>")
	dialogConfirm.click(function() {
		if (settings.confirm != null) {
			if (settings.confirm()) $("#"+dialogId).remove();
		} else {
			$("#"+dialogId).remove();
		}
	});
	dialog.append(dialogConfirm);
	$("body").append($("<div id='"+dialogId+"' class='dialog-bg'></div>").append(dialog));
}
