<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ attribute name="content" type="java.lang.String" required="true" description="消息内容"%>
<%@ attribute name="title" type="java.lang.String" description="标题：默认系统提示"%>

<c:if test="${not empty content}">
    <style>
        .show {
            position: fixed;
            right: 1rem;
            top: 1rem;
        }
    </style>
	<div id="toast" class="toast show" style="display: none">
		<div class="toast-header">
			<img src="/image/sys/icon32x32.png" class="rounded mr-2" width="20px" height="20px" alt="">
			<strong class="mr-auto">
                <c:if test="${empty title}">系统提示</c:if>
                <c:if test="${not empty title}">${title}</c:if>
			</strong>
			<div class="ml-2 mb-1 close" onclick="$('#toast').hide(300);">&times;</div>
		</div>
		<div class="toast-body">信息：${content}</div>
	</div>
	<script>
	   var toast = $("#toast");
	   toast.show(300);
	   setTimeout(function() {
		   if (toast.is(":visible")) {
		       toast.hide(300);
		   }
	   }, 5000);
	</script>
</c:if>
