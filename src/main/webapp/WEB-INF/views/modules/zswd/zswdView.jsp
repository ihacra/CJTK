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
	function btnEdit(id) {
		window.location.href = "/question/?id=" + id;
	}
	
	// 显示元素
	function btnShow(id) {
		$("#"+id).toggle();
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
				<span class="zswd-left bg-color1 btn" onclick="btnEdit('${question.id}')" title="修改题目">题目</span>
				<div class="zswd-right">
					<c:if test="${question.type eq '0'}">
						选择题：${question.title}
						<br/>A. ${question.optionA}
						<br/>B. ${question.optionB}
						<br/>C. ${question.optionC}
						<br/>D. ${question.optionD}
					</c:if>
					<c:if test="${question.type eq '1'}">
						填空题：${question.title}
					</c:if>
					<c:if test="${question.type eq '2'}">
						判断题：${question.title}
					</c:if>
				</div>
			</div>
			<div class="zswd-row">
				<span class="zswd-left bg-color2 btn" onclick="btnShow('answer')" title="显示答案">答案</span>
				<div class="zswd-right" id="answer" style="display: none;">${question.answer}</div>
			</div>
			<div class="zswd-row">
				<span class="zswd-left bg-color3 btn" onclick="btnShow('analysis')" title="显示解析">解析</span>
				<div class="zswd-right" id="analysis" style="display: none;">
					<c:if test="${empty question.analysis}">无</c:if>
					<c:if test="${not empty question.analysis}">${question.analysis}</c:if>
				</div>
			</div>
			<div class="zswd-row">
				<span class="zswd-left bg-color4 btn" onclick="btnShow('label')" title="显示标签">标签</span>
				<div class="zswd-right" id="label" style="display: none;">
					<c:if test="${empty question.label}">无</c:if>
					<c:if test="${not empty question.label}">${question.label}</c:if>
				</div>
			</div>
			<div class="zswd-row zswd-end">
				<div class="btn-group">
					<button class="bg-color1" onclick="btnNext()">下一题</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
