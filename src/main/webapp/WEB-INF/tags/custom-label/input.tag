<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ attribute name="id" type="java.lang.String" description=""%>
<%@ attribute name="name" type="java.lang.String" required="true" description=""%>
<%@ attribute name="maxlength" type="java.lang.Integer" required="true" description=""%>
<%@ attribute name="classStyle" type="java.lang.String" description=""%>
<%@ attribute name="placeholder" type="java.lang.String" description=""%>

<div class="input-area">
	<input id="${id}" name="${name}"  maxlength="${maxlength}" class="${classStyle}" autocomplete="off" class="${classStyle}" placeholder="${placeholder}"/>
	<div class="input-count"><span data-v="${name}">0</span> / ${maxlength}</div>
</div>
<c:if test="${fn:contains(classStyle, 'required')}"><div class="required-tip">*</div></c:if>
