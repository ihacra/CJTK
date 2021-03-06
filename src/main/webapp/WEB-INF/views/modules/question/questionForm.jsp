<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<title>${title}</title>
<script>
	$(document).ready(function() {
		$(".guide-item").removeClass("active");
		$("#title").bind('input propertychange', function() {
			var regex = /^([\w\W]+?)A.([\w\W]+?)B.([\w\W]+?)C.([\w\W]+?)D.([\w\W]+?)$/;
	        if (regex.test($("#title").val())) {
	        	data = [RegExp.$1, RegExp.$2, RegExp.$3, RegExp.$4, RegExp.$5];
	        	$("#title").val($.trim(data[0])).change();
				$("#optionA").val($.trim(data[1])).change();
				$("#optionB").val($.trim(data[2])).change();
				$("#optionC").val($.trim(data[3])).change();
				$("#optionD").val($.trim(data[4])).change();
	        }
		});
	});
	
	// 提交表单
	function btnSaveOnclick() {
		dialog({
			content: "是否确认提交？",
			confirm: function() {
				if ($("#inputForm").valid()) {
					loading();
					$("#inputForm").submit();
					return true;
				} else {
					return false;
				}
			}
		});
	}
	
	// 类型为选择题时显示ABCD选项输入框
	function typeOnchange(id, value) {
		if (value == '2') {
			$("#option").slideUp("slow");
			$("#optionA").val(null).change();
			$("#optionB").val(null).change();
			$("#optionC").val(null).change();
			$("#optionD").val(null).change();
		} else {
			$("#option").slideDown("slow");
		}
	}
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/top.jsp">
		<jsp:param name="item" value="0"/>
	</jsp:include>
	<div class="main">
		<cw:toast content="${message}"></cw:toast>
		<form:form id="inputForm" modelAttribute="question" action="/question/save" method="post">
			<div class="area-zswd">
				<div class="zswd-row">
					<span class="zswd-left bg-color5">类型</span>
					<div class="zswd-right">
						<cl:radio name="type" label="单选题,多选题,判断题" value="0,1,2" checked="0" classStyle="required" onchange="typeOnchange"></cl:radio>
					</div>
				</div>
				<div class="zswd-row">
					<span class="zswd-left bg-color1">题目</span>
					<div class="zswd-right">
						<cl:textarea path="title" rows="3" maxlength="300" classStyle="required" placeholder="请输入问题题目"></cl:textarea>
						<div id="option">
							<div style="margin: 12px 0 6px 0; display: inline-flex;">
								A.&nbsp;&nbsp;<cl:input path="optionA" maxlength="60" classStyle="required" placeholder="选项A"></cl:input>
							</div>
							<div style="margin: 6px 0; display: inline-flex;">
								B.&nbsp;&nbsp;<cl:input path="optionB" maxlength="60" classStyle="required" placeholder="选项B"></cl:input>
							</div>
							<div style="margin: 6px 0; display: inline-flex;">
								C.&nbsp;&nbsp;<cl:input path="optionC" maxlength="60" classStyle="required" placeholder="选项C"></cl:input>
							</div>
							<div style="margin: 6px 0 0 0; display: inline-flex;">
								D.&nbsp;&nbsp;<cl:input path="optionD" maxlength="60" classStyle="required" placeholder="选项D"></cl:input>
							</div>
						</div>
					</div>
				</div>
				<div class="zswd-row">
					<span class="zswd-left bg-color2">答案</span>
					<div class="zswd-right">
						<cl:textarea path="answer" rows="3" maxlength="150" classStyle="required" placeholder="请输入问题答案"></cl:textarea>
					</div>
				</div>
				<div class="zswd-row">
					<span class="zswd-left bg-color3">解析</span>
					<div class="zswd-right">
						<cl:textarea path="analysis" rows="3" maxlength="300" placeholder="请输入问题解析"></cl:textarea>
					</div>
				</div>
				<div class="zswd-row">
					<span class="zswd-left bg-color4">标签</span>
					<div class="zswd-right">
						<cl:input path="label" maxlength="30" placeholder="请输入问题标签（多个标签以逗号分割）"></cl:input>
					</div>
				</div>
				<div class="zswd-row zswd-end">
					<div class="btn-group">
						<button type="button" class="bg-color2" onclick="btnSaveOnclick()">保存</button>
					</div>
				</div>
			</div>
		</form:form>
	</div>
	<%@ include file="/WEB-INF/views/include/bottom.jsp"%>
</body>
</html>
