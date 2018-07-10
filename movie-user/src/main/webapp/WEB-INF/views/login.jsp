<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>登录</title>
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
        <h3 class="text-center">用户登录</h3>
        </div>
      </div>
    

       <div class="box-solid">
        <div class="box-body">
            <!-- /.box-header -->
            <!-- form start -->
            <form action="" class="form-horizontal" method="post" id="loginForm">
              <div class="box-body">
                <div id="error" class="alert alert-error" hidden><button class="close" data-dismiss="alert">×</button></div>
                <fieldset>
                  <div class="form-group">
                     	<label class="col-sm-1 control-label"><i class="fa fa-user"></i></label>
 					    <div class="col-sm-11">
					      <input type="text" class="form-control" id="userName" name="userName" value="${userName}" placeholder="登录帐号">
					    </div> 
                  </div>
                  <div class="form-group">
                      	<label class="col-sm-1 control-label"><i class="fa fa-lock"></i></label>
					    <div class="col-sm-11">
					      <input type="password" class="form-control" id="password" name="password" placeholder="登录密码">
					    </div>
                  </div>
                </fieldset>
                <br>  
                <div id="remember-me" class="form-group">
                  <div class="col-sm-offset-1 col-sm-11">
                    <input type="checkbox" name="remember" value="remember" id="remember" 
                    <c:if test="${not empty userName}">
                    	checked
                    </c:if>
                    style="margin-right:4px;">
                    <label id="remember" for="remember">记住用户名</label>
                    <a href="/regsiter" style="margin-left:220px; color:#000;font-weight:bold;">注册</a>
                  </div>
                  
                </div>
                
                </div>
                <!-- /.box-body -->
               
                <br>
            </form>
            <div>
              <button type="button" id="loginBtn" class="btn btn-info  btn-lg btn-block">
                	进入管理系统
              </button>
            </div>
        </div>
        </div>
    </section>
  </div>


<!-- jQuery 2.2.3 -->
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/static/dist/js/jquery.validate.min.js"></script>
<script>
	$(function(){
		var callback = "${param.callback}";
		/* 
		var adminName = $("#adminName").val();
		if(adminName){
			$("#rememberme")[0].checked = 'checked';
		}
		 */
		$("#loginBtn").click(function(){
			$("#loginForm").submit();
		});
		$("#loginForm").validate({
			errorElement:'span',
			errorClass:'text-danger',
			rules:{
				userName : {
					required : true
				},
				password : {
					required : true
				}
			},
			messages:{
				userName : {
					required : "请输入用户名"
				},
				password : {
					required : "请输入密码"
				}
			},
			submitHandler:function(){
				$.ajax({
					url:"/login",
					type:"post",
					data:$("#loginForm").serialize(),
					beforeSend:function(){
						$("#loginBtn").attr("disabled","disabled").text("登录中...");
					},
					success:function(res){
						if(res.state == "success") {
							if(callback) {
								window.location.href = callback;
							} else {
								window.location.href = "/user/index";
							}
						} else {
							$("#error").text(res.message).show();
						}
					},
					error:function(){
						alert("系统繁忙");
					},
					complete:function(){
						$("#loginBtn").removeAttr("disabled").text("进入管理系统");
					}
				})
			}
			
			
		});
		
		
	})

</script>
</body>
</html>
