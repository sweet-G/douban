<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>待审核列表</title>
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<%@ include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
<%@ include file="../include/header.jsp"%>
<%@ include file="../include/left.jsp"%>
<div class="content-wrapper">
<section class="content">
<div class="box">
	<div class="box-header with-border">
		<!--  -->
		<!--  <h5 class="pull-left">文章列表</h5> -->
	</div>

	<div class="box-body">
		<c:if test="${empty page.TList}">
			<h3>暂无新评论</h3>
		</c:if>
	
		<c:forEach items="${page.TList}" var="reply">
			<div class="panel panel-default">
				<div class="panel-heading">
					<a href=""><span class="media-heading"> 《${reply.filmName}》</span></a> <span>评论者：${reply.pickName}</span>
				</div>
				<div class="panel-body">${reply.content}</div>
				<div class="panel-footer">
					<button rel="${reply.id}" class="btn btn-success passreply">审核通过</button>
					<button rel="${reply.id}" class="btn btn-default unpassreply">审核不通过</button>
				</div>
			</div>
		</c:forEach>
		<br>
		<ul id="pagination" class="pagination pull-right"></ul>
	</div>
	
</div>
</section>
</div>
<%@ include file="../include/footer.jsp"%>
</div>

<%@ include file="../include/js.jsp"%>
<script src="/static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="/static/dist/js/jquery.twbsPagination.min.js"></script>
<script>
$(function() {

	$("#pagination").twbsPagination({
		totalPages : "${page.totalPage}",
		visiblePages : 3,
		href : "/reply/manage?p={{number}}",
		first : "首页",
		prev : "上一页",
		next : "下一页",
		last : "末页"
	});
	
	$(".passreply").click(function(){
		$.post("/reply/edit",{
			"state" : "1",
			"replyid" : $(this).attr("rel")
		}, function(res){
			if(res.state == "success"){
				history.go(0);
			} else {
				layer.msg(e.message)
			}
		});
	});
	
	$(".unpassreply").click(function(){
		$.post("/reply/edit",{
			"state" : "2", 
			"replyid" : $(this).attr("rel")
		}, function(res){
			if(res.state == "success"){
				history.go(0);
			} else {
				layer.msg(e.message)
			}
		});
	});
	
});
</script>
</body>
</html>
