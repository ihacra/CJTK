<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ attribute name="path" type="java.lang.String" required="true" description=""%>
<%@ attribute name="maxlength" type="java.lang.Integer" required="true" description=""%>
<%@ attribute name="value" type="java.lang.String" description=""%>
<%@ attribute name="classStyle" type="java.lang.String" description=""%>
<%@ attribute name="rows" type="java.lang.Integer" description=""%>
<%@ attribute name="placeholder" type="java.lang.String" description=""%>

<div class="textarea-area">
	<textarea id="${path}" name="${path}" rows="${rows}" maxlength="${maxlength}" class="${classStyle}" placeholder="${placeholder}">${value}</textarea>
	<div class="textarea-count">
		<span data-v="${path}">${fn:length(value)}</span>/${maxlength}
	</div>
</div>
<c:if test="${fn:contains(classStyle, 'required')}">
	<div class="required-tip" style="position: absolute;">*</div>
</c:if>
