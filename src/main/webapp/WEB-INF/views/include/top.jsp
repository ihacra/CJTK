<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<div class="bg-cover"></div>
<div class="bg-image"></div>
<div class="content-guide">
	<div class="guide-container">
		<div class="menu-container">
			<a href="/question/"><img class="menu-logo" src="/image/logo.png" alt="logo"></a>
			<a class="menu-item" href="/zswd/">会计题库</a>
		</div>
		<% String item = request.getParameter("item"); %>
		<a class="guide-item <% if("0".equals(item)){%>active<%} %>" href="/zswd/">知识问答</a>
		<a class="guide-item <% if("1".equals(item)){%>active<%} %>" href="/qbtk/">全部题库</a>
	</div>
</div>
