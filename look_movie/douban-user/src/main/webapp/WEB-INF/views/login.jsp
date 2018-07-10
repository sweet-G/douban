<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>登录</title>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<%@ include file="include/css.jsp"%>
<link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
<style type="text/css">
	body {
		background-image: url(/static/dist/img/body-bg.png);
		padding-top: 120px;
	}
</style>
</head>

<body class="col-sm-offset-3 col-sm-6 col-lg-offset-4 col-lg-4 ">
<div style="background-color: #fff;">
<section class="content">

<div class="" style="border-bottom: 1px solid #f0f0f0">
	<div class="box-header">
		<h3 class="text-center">用户登录 | <a href="/signin"> 注册</a></h3>
	</div>
</div>

<div class="box-solid">
<div class="box-body">
	<form action="/login" class="form-horizontal" method="post" id="loginForm">
		<div class="box-body">
			<div class="alert alert-error" hidden id="error">
				<button class="close" data-dismiss="alert">×</button>
				用户名或密码错误
			</div>
			
			<fieldset>
			  <div class="form-group">
				<label class="col-sm-1 control-label">
				  <i class="fa fa-user"></i>
				</label>
				<div class="col-sm-11">
				  <input type="text" class="form-control" id="username" name="username" value="${userName}" placeholder="登录帐号">
				</div>
			  </div>
			  
			  <div class="form-group">
				<label class="col-sm-1 control-label">
				  <i class="fa fa-lock"></i>
				</label>
				<div class="col-sm-11">
				  <input type="password" class="form-control" id="password" name="password" value="${password}" placeholder="登录密码">
				</div>
			  </div>
			</fieldset><br>
			
			<div id="remember-me" class="form-group">
			  <div class="col-sm-offset-1 col-sm-11">
				<input type="checkbox" <c:if test="${not empty password}">checked</c:if> name="remember" id="remember" style="margin-right: 4px;">
				<label id="remember" for="remember">记住密码</label>
			  </div>
			</div>
		</div><br>
	</form>
	
	<div>
		<button type="button" id="loginBtn" class="btn btn-info  btn-lg btn-block">进入系统</button>
	</div>
</div>
</div>
</section>
</div>

<%@ include file="include/js.jsp"%>

<script>
$(function(){
	var path = "${param.path}";
	$("#loginBtn").click(function(){
		$("#loginForm").submit();
	});
	$("#loginForm").validate({
		errorElement : "span",
		errorClass : "text-danger",
		rules : {
			"username" : {
				required : true 
			},
			"password" : {
				required : true
			},
		},
		messages : {
			"username" : {
				required : "账号不能为空" 
			},
			"password" : {
				required : "密码不能为空"
			},
		},
		submitHandler : function(){
			$.ajax({
				url : "/login",
				type : "POST",
				data : $("#loginForm").serialize(),
				beforeSend : function(){
					$("#loginBtn").attr("disabled", "disabled").text("登录中...")
				},
				success : function(res){
					if(res.state == "success"){
						if(path){
							window.location.href = path;
						} else {
							window.location.href = "/user/homepage";
						}
					} else {
						$("#error").text(res.message).show()
					}
				},
				error : function(){
					layer.msg("系统繁忙,请稍后再试!")
				},
				complete : function(){
					$("#loginBtn").removeAttr("disabled").text("进入管理系统")
				}
			});
		}
	});

})
</script>
</body>
</html>
