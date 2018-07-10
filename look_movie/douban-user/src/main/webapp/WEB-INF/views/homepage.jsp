<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<%@ include file="include/css.jsp"%>

<style>
body {
	margin-top: 60px;
}
</style>
</head>
<body>
<%@ include file="include/header.jsp"%>
<!-- 文章列表开始 -->
<div class="container">
<div class="row">
	<div class="col-md-9">
		<!-- 显示电影内容↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ -->
		<c:if test="${empty page.tList}"><h3>找不到该资源</h3></c:if>
		<c:forEach items="${page.tList}" var="movie">
		<div class="article-span">
			<div class="media article">
				<div class="media-body">
					<a href="/user/detail?filmname=${movie.filmName}"><span class="media-heading">${movie.filmName}</span></a>
					<span class="">${movie.filmDirector}</span>
					<p class="">${movie.simpoContent}</p>
					<div class="meta">
						<c:forEach items="${movie.typeList}" var="type" varStatus="vs">
							<span class="label
							<c:choose>
								<c:when test="${vs.count == 1}"> 
									label-default
								</c:when>
								<c:when test="${vs.count == 2}">
									label-primary
								</c:when>
								<c:otherwise>
									label-info
								</c:otherwise>
							</c:choose>
							">${type.text}</span>
						</c:forEach>
					</div>
				</div>
				<div class="media-right">
					<a href="javascript:;"> <img src="${movie.imgPath}" style="width: 99px; height: 128px" class="media-object" alt=""></a>
				</div>
			</div>
		</div>
		</c:forEach>
		<div class="text-center">
			<ul id="pagination" class="pagination pagination-lg"></ul>
		</div>
	</div>
	
	<!-- 显示浏览排行↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ -->
	<div class="col-md-3">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">浏览排行</h3>
			</div>
			<ul class="list-group text-primary">
				<c:forEach items="${movieList}" var="movie" varStatus="vs">
					<li class="list-group-item"><a href="/user/detail?filmname=${movie.filmName}">${vs.count}. ${movie.filmName} </a><label class="label 
					<c:choose>
						<c:when test="${vs.count == 1}"> 
							label-danger
						</c:when>
						<c:when test="${vs.count == 2}">
							label-warning
						</c:when>
						<c:when test="${vs.count == 3}">
							label-info
						</c:when>
						<c:otherwise>
							label-default
						</c:otherwise>
					</c:choose>
					">${movie.scanNum}</label></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
</div>

<%@ include file="include/js.jsp"%>

<script>
$(function() {
	var keys = "${param.keys}";
	var type = "${param.type}";
	keys = encodeURIComponent(keys);
	type = encodeURIComponent(type);
	
	$("#pagination").twbsPagination({
		totalPages : "${page.totalPage}",
		visiblePages : 3,
		href : "/user/homepage?p={{number}}&keys=" + keys + "&type=" + type,
		first : "首页",
		prev : "上一页",
		next : "下一页",
		last : "末页"
	});
});
</script>
</body>
</html>