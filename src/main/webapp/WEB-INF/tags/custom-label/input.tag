<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ attribute name="path" type="java.lang.String" required="true" description=""%>
<%@ attribute name="maxlength" type="java.lang.Integer" required="true" description=""%>
<%@ attribute name="value" type="java.lang.String" description=""%>
<%@ attribute name="classStyle" type="java.lang.String" description=""%>
<%@ attribute name="placeholder" type="java.lang.String" description=""%>

<div class="input-area">
	<input id="${path}" name="${path}"  maxlength="${maxlength}" class="${classStyle}" autocomplete="off" value="${value}" placeholder="${placeholder}"/>
	<div class="input-count">
		<span data-v="${path}">${fn:length(value)}</span>/${maxlength}
	</div>
</div>
<c:if test="${fn:contains(classStyle, 'required')}"><div class="required-tip">*</div></c:if>
