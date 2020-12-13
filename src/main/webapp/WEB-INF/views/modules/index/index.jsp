<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<title>
	<c:if test="${fn:length(title)<=4}">会计题库</c:if>
	<c:if test="${fn:length(title)>5}">${title}</c:if>
</title>
</head>
<head>
<style>
	.title {
		color: #fff;
		display: block;
	    font-weight: bold;
	    font-size: 28px;
	    line-height: 130px;
	    letter-spacing: 4px;
	    border-radius: 8px 8px 0 0;
	}
	
	.info {
		color: #666;
	    display: block;
	    line-height: 50px;
	}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/top.jsp"/>
	<div class="main">
		<div class="area-layout">
			<ul>
				<c:forEach items="${subjectMap}" var="map" varStatus="vs">
					<li>
						<a href="/index/forward?subject=${map.key}">
							<span class="title bg-color${vs.index%5+1}">${map.value}</span>
							<span class="info">选择题：${subjectList[vs.index*2]}条&nbsp;|&nbsp;填空题：${subjectList[vs.index*2+1]}条</span>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/bottom.jsp"%>
</body>
</html>
