<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>注册</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<%@ include file="include/css.jsp"%>
<!-- Theme style -->
<link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">

<style type="text/css">
body {
	background-image: url(/static/dist/img/body-bg.png);
	padding-top: 60px;
}
</style>
</head>
<body class="col-sm-offset-3 col-sm-6 col-lg-offset-4 col-lg-4 ">
<div style="background-color: #fff;">
<section class="content">
	<div class="" style="border-bottom: 1px solid #f0f0f0">
		<div class="box-header">
			<h3 class="text-center">欢迎加入豆瓣 | <a href="/login"> 登录</a></h3>
		</div>
	</div>

	<div class="box-solid">
	<div class="box-body">
		<form action="/signin" class="form-horizontal" method="post" id="signinForm">

			<div class="form-group">
				<label class="col-sm-1 control-label"><i class="fa fa-user-circle fa-lg"></i></label>
				<div class="col-sm-10">
					<input type="text" name="username" class="form-control" id="username"
						placeholder="你的账号">
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-1 control-label"><i class="fa fa-user  fa-lg"></i></label>
				<div class="col-sm-10">
					<input type="text" name="pickname" class="form-control" id="pickname"
						placeholder="你的昵称">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-1 control-label"><i class="fa fa-lock fa-lg"></i></label>
				<div class="col-sm-10">
					<input type="password" name="password" class="form-control" id="password"
						placeholder="设置密码">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-1 control-label"><i class="fa fa-lock fa-lg"></i></label>
				<div class="col-sm-10">
					<input type="password" name="checkpassword" class="form-control" id="checkpassword"
						placeholder="验证密码">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-1 control-label"><i class="fa fa-mobile fa-2x"></i></label>
				<div class="col-sm-10">
					<input type="text" name="tel" class="form-control" id="tel"
						placeholder="你的手机号">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-1 control-label"><i class="fa fa-envelope-o  fa-lg"></i></label>
				<div class="col-sm-10">
					<input type="email" name="email" class="form-control" id="email"
						placeholder="你的邮箱">
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-1"></div>
				<div class="col-sm-6">
					<input type="text" name="kaptcha" class="form-control" id="kaptcha"
							placeholder="验证码">
				</div>
				<img src="kaptcha.jpg" id="kaptchaImage" />
			</div>
		</form>
		
		<div>
			<button type="button" id="signinBtn" class="btn btn-info  btn-lg btn-block">注 册</button>
		</div>

	</div>
	</div>
</section>
</div>
<%@ include file="include/js.jsp"%>
<script>
$(function(){
	 $('#kaptchaImage').click(function () {
     	$(this).attr('src', '/kaptcha.jpg?' + Math.floor(Math.random()*100)); 
     });
	 
	 $("#checkpassword").blur(function(){
		 var password = $("#password").val();
		 var checkpassword = $(this).val();
		 if(password != checkpassword){
			 layer.msg("两次输入密码不一致");
		 }
	 });
	 
	 $("#signinBtn").click(function(){
		 $("#signinForm").submit();
	 });
	 $("#signinForm").validate({
		 
		 errorElement : "span",
		 errorClass : "text-danger",
		 rules : {
			"username" : {
				required : true,
				remote : "/checkusername"
			},
			"pickname" : {
				required : true,
				remote : "/checkusername"
			},
			"password" : {
				required : true,
				
			},
			"checkpassword" : {
				required : true,
			},
			"tel" : {
				required : true,
			},
			"email" : {
				required : true,
			},
			"kaptcha" : {
				required : true,
			},
		 },
		 messages : {
			"username" : {
				required : "账号不能为空",
				remote : "该账号已被占用"
			},
			"pickname" : {
				required : "先起个优雅的昵称吧",
				remote : "该昵称已被占用"
			},
			"password" : {
				required : "密码不能为空",
			},
			"checkpassword" : {
				required : "请再次输入密码",
			},
			"tel" : {
				required : "手机号不能为空",
			},
			"email" : {
				required : "邮箱不能为空",
				email : "请输入正确的邮箱"// email:true
			},
			"kaptcha" : {
				required : "验证码不能为空",
			},
		 },
		 submitHandler : function(){
				$.ajax({
					url : "/signin",
					type : "POST",
					data : $("#signinForm").serialize(),
					beforeSend : function(){
						$("#signinBtn").attr("disabled", "disabled").text("注册中...")
					},
					success : function(res){
						if(res.state == "success"){
							window.location.href = "/login";
						} else {
							layer.msg(res.message)
						}
					},
					error : function(){
						layer.msg("系统繁忙,请稍后再试!")
					},
					complete : function(){
						$("#signinBtn").removeAttr("disabled").text("注册")
					}
				})
			}
	 });
	
})
</script>
</body>
</html>
