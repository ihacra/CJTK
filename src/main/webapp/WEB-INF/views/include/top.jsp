<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<div class="content-guide">
	<div class="guide-container">
		<div class="menu-container">
			<a href="/question/"><img class="menu-logo" src="/image/logo.png" alt="logo"></a>
			<a class="menu-item" href="/">
				<c:if test="${fn:length(title)<=4}">会计题库</c:if>
				<c:if test="${fn:length(title)>5}">${title}</c:if>
			</a>
		</div>
		<% String item = request.getParameter("item"); %>
		<a class="guide-item <% if("0".equals(item)){%>active<%} %>" href="/zswd/">知识问答</a>
		<a class="guide-item <% if("1".equals(item)){%>active<%} %>" href="/qbtk/">全部题库</a>
	</div>
</div>
