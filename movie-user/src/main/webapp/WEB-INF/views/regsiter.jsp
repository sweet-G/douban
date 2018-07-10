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
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="/static/dist/css/font-awesome.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">


  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  <style type="text/css">
		body{
			background-image:url(/static/dist/img/body-bg.png);
			padding-top: 120px;
		}
	
	</style>
</head>
<body class="col-sm-offset-3 col-sm-6 col-lg-offset-4 col-lg-4 ">

  <div style="background-color: #fff;">
    <!-- Main content -->
    <section class="content">
      <div class="" style="border-bottom: 1px solid #f0f0f0">
        <div class="box-header">
        <h3 class="text-center">欢迎注册</h3>
        </div>
      </div>
    

       <div class="box-solid">
        <div class="box-body">
            <!-- /.box-header -->
            <!-- form start -->
            <form action="/regsiter" class="form-horizontal" method="post" id="reForm">
              <div class="box-body">
                <div id="error" class="alert alert-error" hidden><button class="close" data-dismiss="alert">×</button></div>
                <fieldset>
                  <div class="form-group">
 					      <label class="col-sm-1 control-label"><i class="fa fa-user"></i></label>
 					    <div class="col-sm-11">
					      <input type="text" class="form-control" id="userName" name="userName" value="${userName}" placeholder="请输入帐号">
					    </div> 
                  </div>
                  <div class="form-group">
					       <label class="col-sm-1 control-label"><i class="fa fa-key"></i></label>
					    <div class="col-sm-11">
					      <input type="password" class="form-control" id="password" name="password" value="${password}" placeholder="请输入密码">
					    </div>
                  </div>
                  <div class="form-group">
					    	<label class="col-sm-1 control-label"><i class="fa fa-key"></i></label>
					    <div class="col-sm-11">
					      <input type="password" class="form-control" id="newPassword" name="newPassword" value="${newPaassword}" placeholder="请确认密码">
					    </div>
                  </div>
                  <div class="form-group">
					    <label class="col-sm-1 control-label"><i class="fa fa-phone-square"></i></label>
					    <div class="col-sm-11">
					      <input type="text" class="form-control" id="tel" name="tel" value="${tel}" placeholder="请输入电话">
					    </div>
                  </div>
                   <div class="form-group">
					    <label class="col-sm-1 control-label"><i class="fa fa-envelope"></i></label>
					    <div class="col-sm-11">
					      <input type="text" class="form-control" id="eamil" name="eamil" value="${eamil}" placeholder="请输入邮箱">
					    </div>
                  </div>
                  <div class="form-group">
					    <label class="col-sm-1 control-label"><i class="fa fa-code"></i></label>
					    <div class="col-sm-6">
							<input type="text" class="form-control" id="kaptcha" name="kaptcha" value="${kaptcha}" placeholder="验证码">
					    </div>
					    <img src="kaptcha.jpg" id="kaptchaImage" />					    
                  </div>
                </fieldset>
                <br>  
                <div id="remember-me" class="form-group">
                  <div class="col-sm-offset-1 col-sm-11">
                    <a href="/login" style="paddint:0px; margin-left:260px; color:#000;">返回登录</a>
                  </div>
                </div>
                </div>
                <br>
            </form>
            <div>
              <button type="button" id="reBtn" class="btn btn-info  btn-lg btn-block">
                	立即注册
              </button>
            </div>
        </div>
        </div>
    </section>
  </div>


<!-- jQuery 2.2.3 -->
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/static/dist/js/jquery.validate.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>

<script>
	$(function(){
		$("#kaptchaImage").click(function(){
	        $(this).attr('src','/kaptcha.jpg?' + Math.floor(Math.random()*100)); 
		})
		
		$("#reBtn").click(function(){
			$("#reForm").submit();
		});
		$("#reForm").validate({
			errorElement:'span',
			errorClass:'text-danger',
			rules:{
				userName : {
					required : true,
					remote:'/check/reName'
				},
				password : {
					required : true
				},
				newPassword:{
					required : true
				},
				tel:{
					required : true
				},
				eamil:{
					required : true
				}
			},
			messages:{
				userName : {
					required : "请输入用户名",
					remote:"该账户名称已被使用"
				},
				password : {
					required : "请输入密码"
				},
				newPassword:{
					required : "请确认密码"
				},
				tel:{
					required : "请输入电话"
				},
				eamil:{
					required : "请输入邮箱"
				}
			},
			submitHandler:function(){
				$.ajax({
					url:"/regsiter",
					type:"post",
					data:$("#reForm").serialize(),
					beforeSend:function(){
						$("#reBtn").attr("disabled","disabled").text("注册中...");
					},
					success:function(res){
						if(res.state == "success") {
							layer.alert("注册成功,请返回登录页面");
						} else {
							$("#error").text(res.message).show();
						}
					},
					error:function(){
						alert("系统繁忙");
					},
					complete:function(){
						$("#reBtn").removeAttr("disabled").text("立即注册");
					}
				})
			}
			
			
		});
		
		
	})

</script>
</body>
</html>
