<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<title>${title}</title>
<style>
	.btn-group button {
		width: 90px;
	}
</style>
<script>
	// 查看问题
	function btnView(id) {
		window.location.href = "/qbtk/view?id=" + id;
	}
	
	// 显示答案
	function btnShow(id) {
		var node = $("#"+id);
		if (node.is(":hidden")) {
			$("div[data-v='an']").hide();
			node.show();
		} else {
			node.hide();
		}
	}
	
	// 换页
	function page(n) {
		$("#pageNo").val(n);
		$("#inputForm").submit();
	}
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/top.jsp">
		<jsp:param name="item" value="1"/>
	</jsp:include>
	<div class="main">
		<cw:toast content="${message}"></cw:toast>
		<form:form id="inputForm" modelAttribute="question" action="/qbtk/" method="get">
			<input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo}"/>
			<input type="hidden" id="count" value="${page.count}" />
			<div class="area-zswd" style="background: hsla(0,0%,97%,.98);">
				<div class="zswd-row zswd-end">
					<div class="zswd-right">
						题目：<input name="title" autocomplete="off" value="${question.title}">&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="bg-color2">查询</button>
					</div>
				</div>
			</div>
		</form:form>
		<br/>
		<div class="area-zswd">
			<c:forEach items="${page.list}" var="question" varStatus="vs">
				<c:if test="${question.type eq '0'}">
					<div class="zswd-row">
						<div class="zswd-left bg-color${(vs.index%5)+1} btn" onclick="btnView('${question.id}')" title="查看问题">${question.id}</div>
						<div class="zswd-right zswd-title">
							<span class="btn" onclick="btnShow('${question.id}')" title="显示答案">单选题：</span>${question.title}
							<p><br/>A. ${question.optionA}
							<br/>B. ${question.optionB}
							<br/>C. ${question.optionC}
							<br/>D. ${question.optionD}</p>
							<div id="${question.id}" data-v="an" style="display: none; margin-top: 4px; color: sienna;">
								<b>答案：</b>${question.answer}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<b>标签：</b>${question.label}
								<br/><b>解析：</b>${question.analysis}
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${question.type eq '1'}">
					<div class="zswd-row">
						<div class="zswd-left bg-color${(vs.index%5)+1} btn" onclick="btnView('${question.id}')" title="查看问题">${question.id}</div>
						<div class="zswd-right zswd-title">
							<span class="btn" onclick="btnShow('${question.id}')" title="显示答案">多选题：</span>${question.title}
							<p><br/>A. ${question.optionA}
							<br/>B. ${question.optionB}
							<br/>C. ${question.optionC}
							<br/>D. ${question.optionD}</p>
							<div id="${question.id}" data-v="an" style="display: none; margin-top: 4px; color: sienna;">
								<b>答案：</b>${question.answer}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<b>标签：</b>${question.label}
								<br/><b>解析：</b>${question.analysis}
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${question.type eq '2'}">
					<div class="zswd-row">
						<div class="zswd-left bg-color${(vs.index%5)+1} btn" onclick="btnView('${question.id}')" title="查看问题">${question.id}</div>
						<div class="zswd-right zswd-title">
							<span class="btn" onclick="btnShow('${question.id}')" title="显示答案">判断题：</span>${question.title}
							<div id="${question.id}" data-v="an" style="display: none; margin-top: 4px; color: sienna;">
								<b>答案：</b>${question.answer}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<b>标签：</b>${question.label}
								<br/><b>解析：</b>${question.analysis}
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
			<div class="zswd-row zswd-end">
				<div class="btn-group">
					<button type="button" id="btnPrev" class="bg-color1" type="button" onclick="page('1')" <c:if test="${page.prev >= page.pageNo}">disabled="disabled"</c:if>>首页</button>
					<button type="button" id="btnPrev" class="bg-color2" type="button" onclick="page('${page.prev}')" <c:if test="${page.prev >= page.pageNo}">disabled="disabled"</c:if>>上一页</button>
					${page.pageNo}/${page.totalPage}
					<button type="button" id="btnNext" class="bg-color3" type="button" onclick="page('${page.next}')" <c:if test="${page.next <= page.pageNo}">disabled="disabled"</c:if>>下一页</button>
					<button type="button" id="btnPrev" class="bg-color4" type="button" onclick="page('${page.totalPage}')" <c:if test="${page.next <= page.pageNo}">disabled="disabled"</c:if>>尾页</button>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/bottom.jsp"%>
</body>
</html>
