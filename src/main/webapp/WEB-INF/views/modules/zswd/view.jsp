<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<title>会计题库</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/top.jsp">
		<jsp:param name="item" value="0"/>
	</jsp:include>
	<div class="main">
		<div class="area-zswd">
			<div class="zswd-row">
				<span class="zswd-left">题目</span>
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
				<span class="zswd-left">答案</span>
				<div class="zswd-right">${question.answer}</div>
			</div>
			<div class="zswd-row">
				<span class="zswd-left">解析</span>
				<div class="zswd-right">${question.analysis}</div>
			</div>
			<div class="zswd-row">
				<span class="zswd-left">标签</span>
				<div class="zswd-right">${question.label}</div>
			</div>
		</div>
	</div>
</body>
</html>
