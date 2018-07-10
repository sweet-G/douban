<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>用户界面</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
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
			
			<c:if test="${empty page.items}">
					<div class="article-span">
							<div class="media article">
	
								<div class="media-body"> <p class="">暂无数据</p></div>
	
							</div>
						</div>
				</c:if>

				<c:forEach items="${page.items}" var="movie">
					<div class="article-span">
						<div class="media article">

							<div class="media-body">
								<a href="/user/detail?id=${movie.id}"><span class="media-heading">${movie.movieName}</span></a>
								<span class="">${movie.directorName }</span>

								<p class="">${movie.simpleContent}</p>
								<div class="meta">
									<c:forEach items="${movie.typeList}" var="type" varStatus="vs">
										<span class="label label-info">${type.text}</span>
									</c:forEach>

									<!-- <span class="label label-primary">犯罪</span> 
								<span class="label label-default">美国</span> -->
								</div>
							</div>

							<div class="media-right">
								<a href=""> <img src="${movie.imageName }" style="width: 128px;" class="media-object" alt="">
								</a>
							</div>
						</div>
					</div>
				</c:forEach>
				<div class="text-center">
					<ul id="pagination" class="pagination pagination-lg"></ul>
				</div>
			</div>
			<div class="col-md-3">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">浏览排行</h3>
					</div>

					<!-- List group -->
					<ul class="list-group text-primary">
						<c:forEach items="${sortMovieList}" var="sortMovie" varStatus="vs">
							<li class="list-group-item">${vs.count}.${sortMovie.movieName} 
							<label class="label 
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
							">${sortMovie.scanNum}</label>
							</li>
						</c:forEach>
						<!-- <li class="list-group-item">2. 霸王别姬 <label	class="label label-warning">1036</label></li>
						<li class="list-group-item">3. 阿甘正传 <label	class="label label-info">456</label></li>
						<li class="list-group-item">4. 这个杀手不太冷 <label class="label label-default">120</label></li>
						<li class="list-group-item">5. 辛德勒名单 <label class="label label-default">30</label></li> -->
					</ul>

				</div>
			</div>
		</div>
	</div>

	<%@ include file="include/js.jsp"%>

	<!-- page -->
	<script src="/static/dist/js/jquery.twbsPagination.min.js"></script>
	<script>
		$(function() { 

			$("#pagination").twbsPagination({
				totalPages : "${page.totalPage}",
				visiblePages : 3,
				href : "/user/index?p={{number}}&typeId=${param.typeId}&keys=" + encodeURIComponent("${param.keys}"),
				first : "首页",
				prev : "上一页",
				next : "下一页",
				last : "末页"
			});
		});
	</script>
</body>
</html>