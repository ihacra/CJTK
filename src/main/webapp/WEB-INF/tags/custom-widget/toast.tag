<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ attribute name="content" type="java.lang.String" required="true" description=""%>

<c:if test="${not empty content}">
	<c:set var="type" value="toast-success"></c:set>
	<c:if test="${fn:contains(content, '失败')}">
		<c:set var="type" value="toast-error"></c:set>
	</c:if>
	<div class="toast-area ${type}">
		${content}
		<div class="toast-close" onclick="$('.toast-area').fadeOut('slow');">×</div>
	</div>
	<script>
		toast1("toast-area");
	</script>
</c:if>
