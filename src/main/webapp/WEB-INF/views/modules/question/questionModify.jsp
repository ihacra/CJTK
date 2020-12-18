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
		if (value == '0') {
			$("#option").slideDown("slow");
		} else {
			$("#option").slideUp("slow");
			$("#optionA").val(null);
			$("#optionB").val(null);
			$("#optionC").val(null);
			$("#optionD").val(null);
		}
	}
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/top.jsp">
		<jsp:param name="item" value="0"/>
	</jsp:include>
	<div class="main">
		<form:form id="inputForm" modelAttribute="question" action="/question/save" method="post">
			<form:hidden path="id"/>
			<form:hidden path="subject"/>
			<input type="hidden" id="path" name="path" value="${path}" />
			<div class="area-zswd">
				<div class="zswd-row">
					<span class="zswd-left bg-color5">类型</span>
					<div class="zswd-right">
						<cl:radio name="type" label="选择题,填空题" value="0,1" checked="${question.type}" classStyle="required" onchange="typeOnchange"></cl:radio>
					</div>
				</div>
				<div class="zswd-row">
					<span class="zswd-left bg-color1">题目</span>
					<div class="zswd-right">
						<cl:textarea name="title" rows="3" maxlength="300" value="${question.title}" classStyle="required" placeholder="请输入问题题目"></cl:textarea>
						<div id="option">
							<div style="margin: 12px 0 6px 0; display: inline-flex;">
								A.&nbsp;&nbsp;<cl:input id="optionA" name="optionA" maxlength="60" value="${question.optionA}" classStyle="required" placeholder="选项A"></cl:input>
							</div>
							<div style="margin: 6px 0; display: inline-flex;">
								B.&nbsp;&nbsp;<cl:input id="optionB" name="optionB" maxlength="60" value="${question.optionB}" classStyle="required" placeholder="选项B"></cl:input>
							</div>
							<div style="margin: 6px 0; display: inline-flex;">
								C.&nbsp;&nbsp;<cl:input id="optionC" name="optionC" maxlength="60" value="${question.optionC}" classStyle="required" placeholder="选项C"></cl:input>
							</div>
							<div style="margin: 6px 0 0 0; display: inline-flex;">
								D.&nbsp;&nbsp;<cl:input id="optionD" name="optionD" maxlength="60" value="${question.optionD}" classStyle="required" placeholder="选项D"></cl:input>
							</div>
						</div>
					</div>
				</div>
				<div class="zswd-row">
					<span class="zswd-left bg-color2">答案</span>
					<div class="zswd-right">
						<cl:textarea name="answer" rows="3" maxlength="150" value="${question.answer}" classStyle="required" placeholder="请输入问题答案"></cl:textarea>
					</div>
				</div>
				<div class="zswd-row">
					<span class="zswd-left bg-color3">解析</span>
					<div class="zswd-right">
						<cl:textarea name="analysis" rows="3" maxlength="300" value="${question.analysis}" placeholder="请输入问题解析"></cl:textarea>
					</div>
				</div>
				<div class="zswd-row">
					<span class="zswd-left bg-color4">标签</span>
					<div class="zswd-right">
						<cl:input name="label" maxlength="30" value="${question.label}" placeholder="请输入问题标签（多个标签以逗号分割）"></cl:input>
					</div>
				</div>
				<div class="zswd-row zswd-end">
					<div class="btn-group">
						<button type="button" class="bg-color2" onclick="btnSaveOnclick()">保存</button>
						<button type="button" class="bg-color1" onclick="history.go(-1)">返回</button>
					</div>
				</div>
			</div>
		</form:form>
	</div>
	<%@ include file="/WEB-INF/views/include/bottom.jsp"%>
</body>
</html>
