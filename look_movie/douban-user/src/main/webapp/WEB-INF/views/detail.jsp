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
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="/static/plugins/editer/styles/simditor.css">
    <style>
        body {
            margin-top: 60px;
        }
    </style>
</head>
<body>
<%@ include file="include/header.jsp"%>
<div class="container">
	<!-- ↓↓电影信息↓↓ -->
<div class="box">
	<div class="topic-head">
		<span class="title">${movie.filmName}</span>
		<div class="info">导演 : ${movie.filmDirector}</div>
		<ul class="list-inline" style="background-color: #fff; margin-right: 6px;">
			<c:forEach items="${movie.typeList}" var="type" varStatus="vs">
				<li><span class="label 
					<c:choose>
						<c:when test="${vs.count == 1}"> 
							label-info
						</c:when>
						<c:when test="${vs.count == 2}">
							label-primary
						</c:when>
						<c:otherwise>
							label-default
						</c:otherwise>
					</c:choose>
				">${type.text}</span></li>
			</c:forEach>
		</ul>
	</div>
	<div class="topic-body">${movie.content}</div>
	
	<div class="topic-toolbar">
		<ul class="list-inline text-muted">
			<li><i class="fa fa-eye"></i> ${movie.scanNum}</li>
			<li><i class="fa fa-commenting"></i> ${movie.replyNum}</li>
		</ul>
	</div>
</div>
<!-- ↑↑电影信息↑↑ -->

<div class="box" style="margin-top:20px;">
	<div class="talk-item muted" style="font-size: 12px">
		${movie.replyNum}个回复 <span 
		<c:if test="${movie.replyNum == 0}">
			hidden
		</c:if>
		id="lastReply">| 直到<span id="lastReplyTime">${movie.lastReplyTime}</span></span>
	</div>
	<c:if test="${empty replyList}">
		<div class="talk-item">
			<h4>暂无评论</h4>
		</div>
	</c:if>
	
	<c:forEach items="${replyList}" var="reply">
		<div class="talk-item">
			<table class="talk-table">
				<tr>
					<td width="auto">
						<a href="" style="font-size: 12px">${reply.pickName}</a>
						<span style="font-size: 12px" class="reply">${reply.createTime}</span><br>
						<p style="font-size: 14px">${reply.content}</p>
					</td>
				</tr>
			</table>
		</div>
	</c:forEach>
</div>

<!-- ↓↓评论↓↓ -->
<div class="box" style="margin:20px 0px;">
	<div class="talk-item muted" style="font-size: 12px"><i class="fa fa-plus"></i> 添加一条新回复</div>
	<form id="replyForm" action="/user/addreply" method="POST" style="padding: 15px;margin-bottom:0px;">
		<textarea name="reply" id="reply"></textarea>
		<input type="hidden" id="movieid" name="movieid" value="${movie.id}" />
	</form>
	<div class="talk-item muted" style="text-align: right;font-size: 12px">
		<span class="pull-left">你现在的气质里，藏着你走过的路、读过的书和爱过的人... ...</span>
		<button id="replyBtn" class="btn btn-primary">发布</button>
	</div>
</div>
<!-- ↑↑评论↑↑ -->
</div>
<%@ include file="include/js.jsp"%>        
<script src="/static/plugins/editer/scripts/module.min.js"></script>
<script src="/static/plugins/editer/scripts/hotkeys.min.js"></script>
<script src="/static/plugins/editer/scripts/uploader.min.js"></script>
<script src="/static/plugins/editer/scripts/simditor.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/dist/js/jquery.validate.min.js"></script>
<script src="/static/dist/js/moment.js"></script>
<script src="/static/dist/js/moment-with-locales.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script>
$(function(){
	var editor = new Simditor({
		textarea: $('#reply'),
		toolbarHidden: true
	});
	
	$("#replyBtn").click(function(){
		if(editor.getValue()){
			$("#replyForm").submit();
		} else {
			layer.msg("您还没有评论!");			
		}
	});
	$("#replyForm").validate({
		errorElement : "span",
		errorClass : "text-danger",
		rules : {
			"movieid" : {
				required : true 
			},
		},
		messages : {
			"movieid" : {
				required : "电影类型不能为空" 
			},
		},
		submitHandler : function(){
			$.ajax({
				url : "/user/addreply",
				type : "POST",
				data : {
					"reply" : editor.getValue(),
					"movieid" : $("#movieid").val()
				},
				beforeSend : function(){
					$("#btn").attr("disabled", "disabled").text("拼命发布中...")
				},
				success : function(res){
					if(res.state == "success"){
						layer.msg("评论成功,等待管理员审核中......!");	
						editor.setValue("");
					} else {
						layer.msg("参数问题" + res.messgae);
					}
				},
				error : function(){
					alert("系统繁忙,请稍后再试!")
				},
				complete : function(){
					$("#btn").removeAttr("disabled").text("发布")
				}
			}) 
		}
	});
	
	moment.locale("zh-cn");
	$(".reply").text(function(){
		var text = $(this).text(); 
		return moment(text).fromNow();
	});
	
	$("#lastReplyTime").text(
		moment($("#lastReplyTime").text()).format("YYYY年MM月DD日 HH:mm:ss")
	);
	
	
});
</script>   
</body>
</html>