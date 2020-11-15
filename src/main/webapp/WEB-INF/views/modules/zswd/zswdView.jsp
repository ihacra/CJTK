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
	function btnEdit(id) {
		window.location.href = "/question/?id=" + id;
	}
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/top.jsp">
		<jsp:param name="item" value="0"/>
	</jsp:include>
	<div class="main">
		<input type="hidden" name="id" value="${question.id}"/>
		<div class="area-zswd">
			<div class="zswd-row">
				<span class="zswd-left bg-color1">题目</span>
				<div class="zswd-right">
					<c:if test="${question.type eq '0'}">
						填空题：${question.title}
					</c:if>
					<c:if test="${question.type eq '1'}">
						选择题：${question.title}
						<br/>A. ${question.optionA}
						<br/>B. ${question.optionB}
						<br/>C. ${question.optionC}
						<br/>D. ${question.optionD}
					</c:if>
					<c:if test="${question.type eq '2'}">
						判断题：${question.title}
					</c:if>
				</div>
			</div>
			<div class="zswd-row">
				<span class="zswd-left bg-color2">答案</span>
				<div class="zswd-right">${question.answer}</div>
			</div>
			<div class="zswd-row">
				<span class="zswd-left bg-color3">解析</span>
				<div class="zswd-right">${question.analysis}</div>
			</div>
			<div class="zswd-row">
				<span class="zswd-left bg-color4">标签</span>
				<div class="zswd-right">${question.label}</div>
			</div>
			<div class="zswd-row zswd-end">
				<div class="btn-group">
					<button class="bg-color2" onclick="btnEdit('${question.id}')">编辑</button>
					<button class="bg-color3" onclick="btnShow()">显示答案</button>
					<button class="bg-color1" onclick="btnNext()">下一题</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
