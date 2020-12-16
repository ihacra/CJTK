<%@page import="com.hacra.cjtk.commons.util.StringUtils"%>
<%@page import="com.hacra.cjtk.commons.util.ExceptionUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<title>
	<c:if test="${fn:length(title)<=4}">会计题库</c:if>
	<c:if test="${fn:length(title)>5}">${title}</c:if>
</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/top.jsp"/>
	<div class="main">
		<%
			response.setStatus(500);
			Throwable ex = ExceptionUtils.getThrowable(request);
			out.println("错误信息：");
			if (ex != null) {
				String message = ExceptionUtils.getStackTraceAsString(ex);
				message = message.replaceAll("at com.hacra.cjtk[\\w\\W]+?\\n", "<span style='color: red;'>$0</span>");
				message = message.replace("\n", "<br/>");
				message = message.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
				out.println(message);
			} else {
				out.println("未知错误。");
			}
		%>
	</div>
	<%@ include file="/WEB-INF/views/include/bottom.jsp"%>
</body>
</html>
