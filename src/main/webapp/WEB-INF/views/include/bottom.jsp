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
			<li>
				选择题：<span style="color: #ccc;">${fns:getCountOfQuestionsByType("0")}</span>&nbsp;条<br/>
				填空题：<span style="color: #ccc;">${fns:getCountOfQuestionsByType("1")}</span>&nbsp;条
			</li>
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
			<li><a href="/question/?import">导入题库</a></li>
			<li><a href="/question/export">导出题库</a></li>
		</ul>
		<div class="copyright">
			<jsp:useBean id="now" class="java.util.Date"/>
			Copyright © <fmt:formatDate value="${now}" pattern="yyyy"/> Hacra. All rights reserved.
		</div>
	</div>
</div>
