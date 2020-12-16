<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<title>${title}</title>
<script>
	$(document).ready(function() {
		var index = 0;
		
		// 上一题
		$("#btnPre").click(function() {
			index = index - 1;
			if (index <= 0) {
				index = 0;
				$("#btnPre").attr("disabled", true);
			}
			getQuestion(index);
			$("#btnNext").attr("disabled", false);
		});
		
		// 下一题
		$("#btnNext").click(function() {
			var length = $("#length").val();
			index = index + 1;
			if (index >= length - 1) {
				index = length - 1;
				$("#btnNext").attr("disabled", true);
			}
			getQuestion(index);
			$("#btnPre").attr("disabled", false);
		});
	});
	
	// 获取问题
	function getQuestion(index) {
		$.ajax({
			type: 'post',
			dataType: 'json',
			data: {"index": index},
			url: '/zswd/show',
			error: function() {
				toast("获取题目失败，请重试!");
			},
			success: function(qs) {
				$("#id").val(qs.id);
				if (qs.type == '0') {
					$("#title").html("<b>"+qs.id+"：</b>"+qs.title+"<p><br/>A. "+qs.optionA+"<br/>B. "+qs.optionB+"<br/>C. "+qs.optionC+"<br/>D. "+qs.optionD+"</p>");
				} else if (qs.type == '1') {
					$("#title").html("<b>"+qs.id+"：</b>"+qs.title);
				}
				$("#answer").html(qs.answer).hide();
				$("#analysis").html(qs.analysis).hide();
				$("#label").html(qs.label).hide();
				$("#pageNumber").html((index+1)+"/"+$("#length").val());
			}
		});
	}
	
	// 修改题目
	function btnEdit() {
		dialog({
			content: "是否确认修改该问题条目？",
			confirm: function() {
				window.location.href = "/question/modify?path=zswd&id=" + $("#id").val();
			}
		});
	}
	
	// 显示答案
	function btnShow() {
		$("#answer").toggle();
		$("#analysis").toggle();
		$("#label").toggle();
	}
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/top.jsp">
		<jsp:param name="item" value="0"/>
	</jsp:include>
	<div class="main">
		<cw:toast content="${message}"></cw:toast>
		<input type="hidden" id="id" name="id" value="${question.id}"/>
		<input type="hidden" id="length" value="${length}" />
		<div class="area-zswd">
			<div class="zswd-row">
				<span class="zswd-left bg-color4 btn" onclick="btnEdit()" title="修改题目">题目</span>
				<div class="zswd-right" id="title">
					<c:if test="${question.type eq '0'}">
						<b>${question.id}：</b>${question.title}
						<p><br/>A. ${question.optionA}
						<br/>B. ${question.optionB}
						<br/>C. ${question.optionC}
						<br/>D. ${question.optionD}</p>
					</c:if>
					<c:if test="${question.type eq '1'}">
						<b>${question.id}：</b>${question.title}
					</c:if>
				</div>
			</div>
			<div class="zswd-row">
				<span class="zswd-left bg-color2 btn" onclick="btnShow()" title="显示答案">答案</span>
				<div class="zswd-right" id="answer" style="display: none; color: sienna;">
					${question.answer}
				</div>
			</div>
			<div class="zswd-row">
				<span class="zswd-left bg-color1">解析</span>
				<div class="zswd-right" id="analysis" style="display: none; color: sienna;">
					${question.analysis}
				</div>
			</div>
			<div class="zswd-row">
				<span class="zswd-left bg-color3">标签</span>
				<div class="zswd-right" id="label" style="display: none; color: sienna;">
					${question.label}
				</div>
			</div>
			<div class="zswd-row zswd-end">
				<div class="btn-group">
					<button id="btnPre" class="bg-color2" disabled="disabled">上一题</button>
					<span id="pageNumber">1/${length}</span>
					<button id="btnNext" class="bg-color1">下一题</button>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/bottom.jsp"%>
</body>
</html>
