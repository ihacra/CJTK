<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<title>${title}</title>
<style>
	.btnStyle {
		opacity: 0;
	}
	
	.btnStyle:hover {
		opacity: 1;
	}
</style>
<script>
	// 修改题目
	function btnEditOnclick() {
		dialog({
			content: "是否确认修改该问题条目？",
			confirm: function() {
				window.location.href = "/question/modify?path=qbtk&id=" + $("#id").val();
				return true;
			}
		});
	}
	
	// 删除题目
	function btnDeleteOnclick() {
		dialog({
			content: "是否确认删除该问题条目？",
			confirm: function() {
				window.location.href = "/question/delete?id=" + $("#id").val();
				return true;
			}
		});
	}
	
	// 增加权重
	function btnAdd() {
		$.ajax({
			type: 'post',
			dataType: 'json',
			data: {"id": $("#id").val()},
			url: '/question/addWeight',
			error: function() {
				loading();
			},
			success: function(num) {
				close();
				toast("已增加问题权重："+num);
			}
		});
	}
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/top.jsp">
		<jsp:param name="item" value="1"/>
	</jsp:include>
	<div class="main">
		<cw:toast content="${message}"></cw:toast>
		<input type="hidden" id="id" name="id" value="${question.id}"/>
		<div class="area-zswd">
			<div class="zswd-row">
				<span class="zswd-left bg-color4">题目</span>
				<div class="zswd-right zswd-title">
					<c:if test="${question.type eq '0'}">
						<span>单选题：</span>${question.title}
						<p><br/>A. ${question.optionA}
						<br/>B. ${question.optionB}
						<br/>C. ${question.optionC}
						<br/>D. ${question.optionD}</p>
					</c:if>
					<c:if test="${question.type eq '1'}">
						<span>多选题：</span>${question.title}
						<p><br/>A. ${question.optionA}
						<br/>B. ${question.optionB}
						<br/>C. ${question.optionC}
						<br/>D. ${question.optionD}</p>
					</c:if>
					<c:if test="${question.type eq '2'}">
						<span>判断题：</span>${question.title}
					</c:if>
				</div>
			</div>
			<div class="zswd-row">
				<span class="zswd-left bg-color2">答案</span>
				<div class="zswd-right" style="color: sienna;">
					${question.answer}
				</div>
			</div>
			<div class="zswd-row">
				<span class="zswd-left bg-color1 btn" onclick="btnAdd()" title="增加权重">解析</span>
				<div class="zswd-right" style="color: sienna;">
					${question.analysis}
				</div>
			</div>
			<div class="zswd-row">
				<span class="zswd-left bg-color3">标签</span>
				<div class="zswd-right" style="color: sienna;">
					${question.label}
				</div>
			</div>
			<div class="zswd-row zswd-end">
				<div class="btn-group">
					<button type="button" class="bg-color1 btnStyle" onclick="btnEditOnclick()">修改</button>
					<button type="button" class="bg-color2" onclick="window.location.href='/qbtk/'">返回</button>
					<button type="button" class="bg-color1 btnStyle" onclick="btnDeleteOnclick()">删除</button>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/bottom.jsp"%>
</body>
</html>
