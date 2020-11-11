<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ attribute name="name" type="java.lang.String" required="true" description="名称"%>
<%@ attribute name="labels" type="java.lang.String" required="true" description="标签"%>
<%@ attribute name="values" type="java.lang.String" required="true" description="值"%>
<%@ attribute name="checked" type="java.lang.String" description=""%>

<div class="wrapper">
	<c:set var="labs" value="${fn:split(labels, ',')}"></c:set>
	<c:set var="vals" value="${fn:split(values, ',')}"></c:set>
	<c:forEach items="${vals}" var="vv" varStatus="vs">
		<div class="radio-box">
			<input type="radio" <c:if test="${vv eq checked}">checked="checked"</c:if>
				id="${name}_${vv}" name="${name}" value="${vv}" /> 
			<span></span>
		</div>
		<label for="${name}_${vv}">${labs[vs.index]}</label>
	</c:forEach>
</div>
