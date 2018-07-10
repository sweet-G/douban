<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" href="/static/plugins/uploader/webuploader.css" />
</head>
<body>
    <div id="picker">选择文件</div>
    
    
    <ul class="list-group" id="files">
    
    </ul>
    <img id="prev" ></img>
    <button class="btn btn-primary" id="btn">开始上传</button>
    
    <script src="/static/dist/js/jquery3.js"></script>
    <script src="/static/plugins/uploader/webuploader.js"></script>
    <script>
    	$(function(){
    		var uploader = WebUploader.create({
    			// 使用falsh上传的swf文件路径
    			swf:"/static/plugins/uploader/Uploader.swf",
    			// 接受图片上传的服务器地址
    			server:"/upload3",
    			// 选择文件的按钮。
    			pick:"#picker",
    			// 选完文件后，是否自动上传。
    		    auto: true,
    			// 只允许选择图片文件。
    		    accept: {
    		        title: 'Images',
    		        extensions: 'gif,jpg,jpeg,bmp,png',
    		        mimeTypes: 'image/*'
    		    }
    		});
    		
    		// 当文件进入文件队列后出发该事件
    		uploader.on('fileQueued',function(file){
    			var li = "<li class='list-group-item' id='" + file.id + "' >" + file.name + "<span style='margin-left:5px'></span></li>";
    			$("#files").append(li);
    		});
    		
    		uploader.on( 'uploadProgress', function( file, percentage ) {
    			$("#"+file.id).find('span').text(parseInt(percentage * 100) + "%");
    		});
    		
    		uploader.on( 'uploadSuccess', function( file, resp ) {
    		    $( '#'+file.id ).find('span').text('已上传');
    		   
    		   $("#prev").attr( 'src', resp.img_path );
    		   
    			// 创建缩略图
    		    // 如果为非图片文件，可以不用调用此方法。
    		    // thumbnailWidth x thumbnailHeight 为 100 x 100
    		   /*  uploader.makeThumb( file, function( error, src ) {
    		        if ( error ) {
    		        	 $("#prev").replaceWith('<span>不能预览</span>');
    		            return;
    		        }

    		        $("#prev").attr( 'src', src );
    		    }, 200, 200 ); */
    		});

    		uploader.on( 'uploadError', function( file ) {
    		    $( '#'+file.id ).find('span').text('上传出错');
    		});

    		uploader.on( 'uploadComplete', function( file ) {
    		   
    		});
    		
    		$("#btn").click(function(){
				// 文件开始上传
    			uploader.upload();
    		})
    		
    	})
    
    
    </script>
</body>
</html>