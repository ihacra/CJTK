<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<!DOCTYPE html>
<div class="footer-wrapper-part"></div>
<div class="footer-wrapper">
	<div class="footer">
		<ul>
			<li>会计题库</li>
			<li>用于记录会计考试学习过程中的习题</li>
		</ul>
		<ul>
			<li>知识问答</li>
			<li><a href="/zswd/">知识问答</a></li>
		</ul>
		<ul>
			<li>全部题库</li>
			<li><a href="/qbtk/">全部题库</a></li>
		</ul>
		<ul>
			<li>批量处理</li>
			<li><a href="/question/">添加题库</a></li>
			<li><a href="javascript:void(0)" onclick="btnImport()">导入题库</a></li>
			<li><a href="javascript:void(0)" onclick="btnExport()">导出题库</a></li>
		</ul>
		<div class="copyright">
			<jsp:useBean id="now" class="java.util.Date"/>
			Copyright © <fmt:formatDate value="${now}" pattern="yyyy"/> Hacra. All rights reserved.
		</div>
	</div>
	<script>
		// 导入方法
		function btnImport() {
			dialog({
				title: "导入题库",
				content: $("#importFormTemplate").html(),
				confirm: function() {
					if ($("#importForm").valid()) {
						loading();
						$("#importForm").submit();
						return true;
					} else {
						return false;
					}
				}
			});
		}
		
		// 导出方法
		function btnExport() {
			dialog({
				title: "导出题库",
				content: "是否确认导出全部题库？",
				confirm: function() {
					window.location.href = "/question/exportExcel";
					return true;
				}
			});
		}
	</script>
	<script type="text/html" id="importFormTemplate">
		<form id="importForm" action="/question/importExcel" method="post" enctype="multipart/form-data">
			<input id="file" name="file" type="file" accept=".xlsx" class="required" style="width:298px; border: none;"/>
		</form>
	</script>
</div>
