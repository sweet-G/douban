<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>修改电影</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<%@ include file="../include/css.jsp"%>

<!-- 富文本编辑器 simditor css -->
<link rel="stylesheet" href="/static/plugins/editer/styles/simditor.css">
<!-- 增强型下拉框 css -->
<link rel="stylesheet" href="/static/plugins/select2/select2.min.css">
<!--引入webuploader CSS-->
<link rel="stylesheet" type="text/css"
	href="/static/plugins/uploader/webuploader.css">
<style>
.select2-container--default .select2-selection--multiple .select2-selection__choice
	{
	color: #555;
}
</style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

<%@ include file="../include/header.jsp"%>
<%@ include file="../include/left.jsp"%>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
<!-- Main content -->
<section class="content">
<!-- Default box -->
<div class="box box-primary">
<div class="box-body">
	<form action="" method="POST" id="editForm">
		<div class="row">
			<div class="col-md-9">
				<div class="form-group">
					<input type="hidden" value="${movie.id}" class="id" name="id" />
					<input type="text" class="form-control" value="${movie.filmName}" name="filmName" placeholder="请输入电影名称">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" value="${movie.filmDirector}" name="director" placeholder="请输入导演">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" value="${movie.area}" name="area" placeholder="请选择地区">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" value="${movie.year}" name="year" placeholder="请输入上映年份">
				</div>
				<div class="form-group">
					<select name="type" id="classic" class="form-control">
					</select>
				</div>
				<br>
			</div>
			<div class="col-md-3">
				<div class="box">
					<div class="box-body">
						<div id="preview" style="height: 138px; text-align: center">
							<img alt="" src="${movie.imgPath}" id="img" style="height: 150px" />
						</div>
						<input type="hidden" id="imageName" value="${oldImgPath}" name="imageName">
					</div>
					<div class="box-footer">
						<div id="picker" style="text-align: center">选择封面</div>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<textarea name="content" class="from-control" id="editor"
				placeholder="电影的内容简介...">${movie.content}</textarea>
		</div>
	</form>
	<button id="btn" class="btn btn-primary">确认修改</button>
</div>
</div>
<!-- /.box -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="../include/footer.jsp"%>
</div>
<!-- ./wrapper -->
<%@ include file="../include/js.jsp"%>
<script src="/static/plugins/editer/scripts/module.min.js"></script>
<script src="/static/plugins/editer/scripts/hotkeys.min.js"></script>
<script src="/static/plugins/editer/scripts/uploader.min.js"></script>
<script src="/static/plugins/editer/scripts/simditor.min.js"></script>
<!-- 下拉框筛选插件 -->
<script src="/static/plugins/select2/select2.min.js"></script>
<script src="/static/plugins/select2/select2.full.min.js"></script>
<!--引入webuploader JS-->
<script src="/static/plugins/uploader/webuploader.js"></script>
</body>
<script>
	$(function() {
		
		var editor = new Simditor({
			textarea : $('#editor'),
			upload:{
	        	url:"/img/uploader",
	        	fileKey:'file'
	        }
		
		});

		var uploader = WebUploader.create({
			// swf文件路径
			swf : '/static/plugins/uploader/Uploader.swf',
			// 文件接收服务端。
			server : '/img/uploader',
			// 选择文件的按钮。可选。
			// 内部根据当前运行是创建，可能是input元素，也可能是flash.
			pick : '#picker',
			// 自动上传
			auto : true,
			// 最多上传一个文件到文件队列
			fileNumLimit : 1,
			// 文件元素的name属性
			fileval : 'file',
			// 只允许选择图片文件。
			accept : {
				title : 'Images',
				extensions : 'gif,jpg,jpeg,bmp,png',
				mimeTypes : 'image/*'
			}
		});

		// 当有文件被添加进队列的时候
		uploader.on('uploadSuccess', function(file, resp) {
			$("#img").removeAttr("src");
			$("#img").attr('src', resp.file_path);
			// 将文件名放入隐藏域，等待表单提交到服务器
			$("#imageName").val(resp.fileName);
		});

		$("#classic").select2({
			placeholder : '请选择电影类型',
			tags : true,
			multiple : true,
			height : '40px',
			maximumSelectionLength : 3,
			allowClear : true,
			language : "zh-CN",
			width : "100%",
		});
		$.ajax({
		    type: 'GET',
		    url: '/type/list?id=${movie.id}',
			success : function(res){
				for(var i = 0; i < res.typeList.length; i++){
					var type = res.typeList[i];
				    var option = new Option(type.text, type.id, type.selected, type.selected);
			    	$('#classic').append(option);
				}
			},
			error : function() {
				alyer.alert("系统异常");
			}
		});
		
		$("#btn").click(function(){
			var imgName = $("#imageName").val();
			var content = editor.getValue();
			if(imgName){
				if(content){
					$("#editForm").submit();
				} else {
					layer.msg("电影简介不能为空哦~ ^_^");
				}
			} else {
				layer.msg("电影封面不能为空哦~ ^_^");
			}
		});
		$("#editForm").validate({
			errorElement : "span",
			errorClass : "text-danger",
			rules : {
				"filmName" : {
					required : true,
					remote : "/movie/checkmoviename?id=" + $(".id").val(),
				},
				"director" : {
					required : true 
				},
				"area" : {
					required : true 
				},
				"year" : {
					required : true 
				},
				"type" : {
					required : true 
				},
			},
			messages : {
				"filmName" : {
					required : "电影名称不能为空",
					remote : "该电影已经被放入库中"
				},
				"director" : {
					required : "电影导演不能为空" 
				},
				"area" : {
					required : "拍摄地点不能为空" 
				},
				"year" : {
					required : "上映时间不能为空" 
				},
				"type" : {
					required : "电影类型不能为空" 
				},
			},
			errorPlacement : function(error, element) {   //错误信息位置设置方法 
				error.appendTo(element.parent()); //这里的element是录入数据的对象 
			}, 
			submitHandler : function(){
				$.ajax({
					url : "/movie/edit",
					type : "POST",
					data : $("#editForm").serialize(),
					beforeSend : function(){
						$("#btn").attr("disabled", "disabled").text("提交中...")
					},
					success : function(res){
						if(res.state == "success"){
							window.location.href = "/movie/list";
						} else {
							alert(res.message)
						}
					},
					error : function(){
						alert("系统繁忙,请稍后再试!")
					},
					complete : function(){
						$("#btn").removeAttr("disabled").text("确认修改")
					}
				})
			}
		})
	});
</script>
</html>
