<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ attribute name="name" type="java.lang.String" required="true" description=""%>
<%@ attribute name="maxlength" type="java.lang.Integer" required="true" description=""%>
<%@ attribute name="classStyle" type="java.lang.String" description=""%>
<%@ attribute name="rows" type="java.lang.Integer" description=""%>
<%@ attribute name="placeholder" type="java.lang.String" description=""%>

<div class="textarea-area">
	<textarea name="${name}" rows="${rows}" maxlength="${maxlength}" class="${classStyle}" placeholder="${placeholder}"></textarea>
	<div class="textarea-count"><span data-v="${name}">0</span> / ${maxlength}</div>
</div>
<c:if test="${fn:contains(classStyle, 'required')}"><div class="required-tip" style="position: absolute;">*</div></c:if>
