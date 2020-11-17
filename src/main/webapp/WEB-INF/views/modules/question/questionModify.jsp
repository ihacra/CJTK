<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<title>会计题库</title>
<script>
	$(document).ready(function() {
		$(".guide-item").removeClass("active");
		$("#inputForm").validate({
			submitHandler: function(form){
				$("#btnSave").attr("disabled", true);
                form.submit();
            },
            errorPlacement: function(error, element) {
                if (element.is(":checkbox")||element.is(":radio")){
                    error.appendTo(element.parent().parent());
                } else {
                    error.insertAfter(element);
                }
            }
		});
	});
	
	// 提交表单
	function btnSaveOnclick() {
		dialog2({
			trigger: "btnSave",
			content: "是否确认提交？",
			confirm: function() {
				$("#inputForm").submit();
			}
		});
	}
	
	// 返回上一页
	function btnBackOnclick() {
		var path = $("#path").val();
		if (path == "zswd") {
			window.location.href = "/zswd/?id=" + $("#id").val();
		}
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
			<input type="hidden" id="id" name="id" value="${question.id}"/>
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
							<div style="margin: 6px 0 6px 0; display: inline-flex;">
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
						<button id="btnSave" class="bg-color2" type="button" onclick="btnSaveOnclick()">保存</button>
						<button id="btnBack" class="bg-color1" type="button" onclick="btnBackOnclick()">返回</button>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>
