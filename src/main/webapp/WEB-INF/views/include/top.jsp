<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<body>
	<div class="bg-cover"></div>
	<div class="bg-image"></div>
	<div class="content-guide">
		<div class="guide-container">
			<div class="menu-container">
				<img class="menu-logo" src="/image/logo.png" alt="logo">
				<a class="menu-item" href="/question">会计题库</a>
			</div>
			<% String item = request.getParameter("item"); %>
			<a class="guide-item <% if("0".equals(item)){%>active<%} %>" href="/zswd">知识问答</a>
			<a class="guide-item <% if("1".equals(item)){%>active<%} %>">趣味测试</a>
			<a class="guide-item <% if("2".equals(item)){%>active<%} %>">全部题库</a>
		</div>
	</div>
</body>
</html>
