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
				<span class="zswd-left">类型</span>
				<div class="zswd-right">
					<div class="wrapper">
						<div class="radio-box">
							<input type="radio" checked="checked" id="a" name="1"/>
							<span></span>
						</div>
						<label for="a">选择题</label>
						<div class="radio-box">
							<input type="radio" id="b" name="1"/>
							<span></span>
						</div>
						<label for="b">填空题</label>
					</div>
				</div>
			</div>
			<div class="zswd-row">
				<span class="zswd-left">题目</span>
				<div class="zswd-right">
					<c:if test="${question.type eq '0'}">
						填空题：${question.title}
					</c:if>
					<c:if test="${question.type eq '1'}">
						选择题：${question.title}
						<br/>&nbsp;A. ${question.optionA}
						<br/>&nbsp;B. ${question.optionB}
						<br/>&nbsp;C. ${question.optionC}
						<br/>&nbsp;D. ${question.optionD}
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
