<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<title>会计题库</title>
</head>
<head>
<script>
	// 修改题目
	function btnEdit() {
		dialog({
			content: "是否确认修改该问题条目？",
			confirm: function() {
				window.location.href = "/question/modify?path=qbtk&id=" + $("#id").val();
			}
		});
	}
	
	// 显示答案
	function btnShow() {
		$("#answer").toggle();
		$("#analysis").toggle();
		$("#label").toggle();
	}
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/top.jsp">
		<jsp:param name="item" value="1"/>
	</jsp:include>
	<div class="main">
		<cw:toast content="${message}"></cw:toast>
		<input type="hidden" id="id" name="id" value="${question.id}"/>
		<div class="area-zswd">
			<div class="zswd-row">
				<span class="zswd-left bg-color4 btn" onclick="btnEdit()" title="修改题目">题目</span>
				<div class="zswd-right" id="title">
					<c:if test="${question.type eq '0'}">
						<b>${question.code}：</b>${question.title}
						<p><br/>A. ${question.optionA}
						<br/>B. ${question.optionB}
						<br/>C. ${question.optionC}
						<br/>D. ${question.optionD}</p>
					</c:if>
					<c:if test="${question.type eq '1'}">
						<b>${question.code}：</b>${question.title}
					</c:if>
				</div>
			</div>
			<div class="zswd-row">
				<span class="zswd-left bg-color2 btn" onclick="btnShow()" title="显示答案">答案</span>
				<div class="zswd-right" id="answer" style="display: none; color: sienna;">
					${question.answer}
				</div>
			</div>
			<div class="zswd-row">
				<span class="zswd-left bg-color1">解析</span>
				<div class="zswd-right" id="analysis" style="display: none; color: sienna;">
					${question.analysis}
				</div>
			</div>
			<div class="zswd-row">
				<span class="zswd-left bg-color3">标签</span>
				<div class="zswd-right" id="label" style="display: none; color: sienna;">
					${question.label}
				</div>
			</div>
			<div class="zswd-row zswd-end">
				<div class="btn-group">
					<button class="bg-color2" onclick="window.location.href='/qbtk/'">返回</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
