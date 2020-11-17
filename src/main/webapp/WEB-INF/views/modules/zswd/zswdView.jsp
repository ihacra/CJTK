<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<title>会计题库</title>
</head>
<head>
<script>
	$(document).ready(function() {
		var index = 0;
		var valiable = window.location.href.split("?")[0];
		window.history.pushState({}, 0, valiable);
		
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
				toast1("获取题目失败，请重试!");
			},
			success: function(qs) {
				$("#id").val(qs.id);
				if (qs.type == '0') {
					$("#title").html(qs.id+"、"+qs.title+"<br/>A. "+qs.optionA+"<br/>B. "+qs.optionB+"<br/>C. "+qs.optionC+"<br/>D. "+qs.optionD);
				} else if (qs.type == '1') {
					$("#title").html(qs.id+"、"+qs.title);
				}
				$("#answer").html(qs.answer).hide();
				$("#analysis").html(qs.analysis).hide();
				$("#label").html(qs.label).hide();
			}
		});
	}
	
	// 修改题目
	function btnEdit(id) {
		window.location.href = "/question/modify?path=zswd&id=" + $("#id").val();
	}
	
	// 显示元素
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
				<span class="zswd-left bg-color1 btn" onclick="btnEdit()" title="修改题目">题目</span>
				<div class="zswd-right" id="title">
					<c:if test="${question.type eq '0'}">
						${question.id}、${question.title}
						<br/>A. ${question.optionA}
						<br/>B. ${question.optionB}
						<br/>C. ${question.optionC}
						<br/>D. ${question.optionD}
					</c:if>
					<c:if test="${question.type eq '1'}">
						${question.id}、${question.title}
					</c:if>
				</div>
			</div>
			<div class="zswd-row">
				<span class="zswd-left bg-color2 btn" onclick="btnShow()" title="显示答案">答案</span>
				<div class="zswd-right" id="answer" style="display: none;">
					${question.answer}
				</div>
			</div>
			<div class="zswd-row">
				<span class="zswd-left bg-color3">解析</span>
				<div class="zswd-right" id="analysis" style="display: none;">
					${question.analysis}
				</div>
			</div>
			<div class="zswd-row">
				<span class="zswd-left bg-color4">标签</span>
				<div class="zswd-right" id="label" style="display: none;">
					${question.label}
				</div>
			</div>
			<div class="zswd-row zswd-end">
				<div class="btn-group">
					<button id="btnPre" class="bg-color2" disabled="disabled">上一题</button>
					<button id="btnNext" class="bg-color1">下一题</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
