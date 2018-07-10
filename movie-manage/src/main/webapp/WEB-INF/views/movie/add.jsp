<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>新增电影</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@ include file="../include/css.jsp" %>
  <!-- 富文本编辑器 simditor css -->
  <link rel="stylesheet" href="/static/plugins/editer/styles/simditor.css">
  <link rel="stylesheet" href="/static/plugins/select2/select2.min.css">
  <!--引入webuploader CSS-->
  <link rel="stylesheet" href="/static/plugins/uploader/webuploader.css">
  <style>
    .select2-container--default .select2-selection--multiple .select2-selection__choice {
      color: #555; 
    }
  </style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

  <%@ include file="../include/header.jsp" %> 
  <%@ include file="../include/sider.jsp" %> 
  
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Main content -->
    <section class="content">
      <!-- Default box -->     
      <div class="box box-primary">
          
        <div class="box-body">
		
            <form action="/movie/add" method="post" id="filmForm">
              <div class="row">
                  <div class="col-md-9">
                        <div class="form-group">
                          <input type="text" class="form-control" name="movieName" placeholder="请输入电影名称">
                      </div>
                      <div class="form-group">
                          <input type="text" class="form-control" name="directorName" placeholder="请输入导演">
                      </div>
                      <div class="form-group">
                          <input type="text" class="form-control" name="area" placeholder="请选择地区">
                      </div>
                      <div class="form-group">
                          <input type="text" class="form-control" name="year" placeholder="请输入上映年份">
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
                              <div id="preview" style="height: 138px;text-align:center"> 
                                <img alt="" id="img" style="height:160px"/>
                              </div> 
                          	<input type="hidden" id="imageName" name="imageName">
                          </div>
                          <div class="box-footer">
                              <div id="picker" style="text-align:center">选择封面</div>
                          </div>
                      </div>
                  </div>
              </div>
               
               <div class="form-group"> 
                  <textarea name="content" class="from-control" id="editor"  placeholder="电影的内容简介..."></textarea>
               </div>
            </form>
            <button class="btn btn-primary" id="btn">发布电影</button>
        
		</div>
  
      </div>
      <!-- /.box -->
    
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

<%@ include file="../include/foot.jsp"%>
</div>
<!-- ./wrapper -->

<%@ include file="../include/js.jsp" %>

<script src="/static/plugins/editer/scripts/module.min.js"></script>
<script src="/static/plugins/editer/scripts/hotkeys.min.js"></script>
<script src="/static/plugins/editer/scripts/uploader.min.js"></script>
<script src="/static/plugins/editer/scripts/simditor.min.js"></script>

<!-- 下拉框筛选插件 -->
<script src="/static/plugins/select2/select2.min.js"></script>
<script src="/static/plugins/select2/select2.full.min.js"></script>

<!--引入webuploader JS-->
<script src="/static/plugins/uploader/webuploader.js"></script>
<!-- 表单验证 -->
<script src="/static/dist/js/jquery.validate.min.js"></script>
</body>
<script>
  $(function(){
   
  var editor = new Simditor({
        textarea: $('#editor'),
        upload:{
        	url:"/img/upload",
        	fileKey:'file'
        }
    });
 
    var uploader = WebUploader.create({
      // swf文件路径
      swf: '/static/plugins/uploader/Uploader.swf',
      // 文件接收服务端。
      server: '/img/upload',
      // 选择文件的按钮。可选。
      // 内部根据当前运行是创建，可能是input元素，也可能是flash.
      pick: '#picker',
      // 这只自动上传
      auto:true,
      // 最多上传一个文件到文件队列
      fileNumLimit:1,
      // 文件元素的name属性
      fileval:'file',
      // 只允许选择图片文件。
      accept: {
          title: 'Images',
          extensions: 'gif,jpg,jpeg,bmp,png',
          mimeTypes: 'image/*'
      }
    }); 

    uploader.on( 'uploadSuccess', function( file, resp ) {
		$("#img").attr( 'src', resp.file_path );
		// 将文件名放入隐藏域，等待表单提交到服务器
		$("#imageName").val(resp.fileName);
    });

    $("#classic").select2({
        placeholder : '请选择电影类型',
        tags : true,
        multiple: true,
        height: '40px',
        maximumSelectionLength : 3,
        allowClear : true,
        language: "zh-CN",
  	  width: "100%",
  	  ajax : {
  		  url : '/type/list',
  		  processResults: function (res) {
  			  if(res.state == "success") {
  				  return {
  					  results: res.results
  				  };
  			  } else {
  				  layer.alert("系统异常");
  				  return {
  					  results: []
  				  };
  			  }
  			  
  		  },
  	  }
      });
    
    $("#filmForm").validate({
    	errorElement:'span',
		errorClass:'text-danger',
		//将错误标签放入下方
		errorPlacement: function(error, element) {  
			   error.appendTo(element.parent());  
			},
    	rules:{
    		movieName:{
    			required:true,
    			remote:"/check/movieName"
    		},
    		directorName:{
    			required:true
    		},
    		area:{
    			required:true
    		},
    		year:{
    			required:true
    		},
    		type:{
    			required:true
    		}
    		
    	},
    	messages:{
    		movieName:{
    			required:"请输入电影名称",
    			remote:"电影名称重复"
    		},
    		directorName:{
    			required:"请输入制作导演"
    		},
    		area:{
    			required:"请输入上映地区"
    		},
    		year:{
    			required:"请输入上映年份"
    		},
    		type:{
    			required:"请输入电影类型"
    		}
    		
    	},
    	submitHandler:function(){
			$.ajax({
				url:"/movie/add",
				type:"post",
				data:$("#filmForm").serialize(),
				beforeSend:function(){
					$("#btn").attr("disabled","disabled").text("电影发布中...");
				},
				success:function(res){
					if(res.state == "success") {
						window.location.href = "/movie/list";
					} else {
					
					}
				},
				error:function(){
					alert("系统繁忙");
				},
				complete:function(){
					$("#btn").removeAttr("disabled").text("发布电影");
				}
			})
		}
        
    });
   
    
    $("#btn").click(function(){
    	var imageName = $("#imageName").val();
    	var content = editor.getValue();
    	if(imageName){
    		if(content){
    			$("#filmForm").submit();
    		} else{
    			layer.alert("请填写电影简介")
    		}
    	} else{
    		layer.alert("请选择封面")
    	}
    })

  });


</script>
</html>
    