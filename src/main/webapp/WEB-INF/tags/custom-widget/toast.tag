<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ attribute name="content" type="java.lang.String" required="true" description=""%>

<c:if test="${not empty content}">
	<div class="toast-area">${content}</div>
</c:if>
