<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ attribute name="name" type="java.lang.String" required="true" description="名称"%>
<%@ attribute name="label" type="java.lang.String" required="true" description="标签"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="值"%>
<%@ attribute name="classStyle" type="java.lang.String" description=""%>
<%@ attribute name="checked" type="java.lang.String" description=""%>
<%@ attribute name="onchange" type="java.lang.String" description=""%>

<div class="wrapper">
	<c:set var="labs" value="${fn:split(label, ',')}"></c:set>
	<c:set var="vals" value="${fn:split(value, ',')}"></c:set>
	<c:forEach items="${vals}" var="vv" varStatus="vs">
		<div class="radio-box btn">
			<input type="radio" id="${name}_${vv}" name="${name}" value="${vv}" 
				class="${classStyle}" onchange="${onchange}('${name}_${vv}','${vv}')"
				<c:if test="${vv eq checked}">
					checked="checked"
					<c:set var="v" value="${vv}"></c:set>
				</c:if>/> 
			<span></span>
		</div>
		<label class="btn" for="${name}_${vv}">${labs[vs.index]}</label>
	</c:forEach>
	<c:if test="${fn:contains(classStyle, 'required')}"><div class="required-tip">*</div></c:if>
	<c:if test="${not empty checked}">
		<script>
			$(document).ready(function() {
				${onchange}('${name}_${v}','${v}');
			})
		</script>
	</c:if>
</div>
