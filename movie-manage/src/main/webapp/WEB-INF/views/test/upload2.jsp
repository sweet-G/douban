<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
	<h3>文件上传</h3>
    <form action="" method="post" enctype="multipart/form-data">
    	<input type="text" name="desc"/> <br />
    	<input type="file" name="file"/> <br />
    	<button id="btn" >开始上传</button>
    </form>
     <a href="/downloads?file=f5759f6b-c261-4558-97aa-ea94f2ebd14c.jpg&name=我的照片.jpg">下载</a>
    <a href="/downloads?file=f5759f6b-c261-4558-97aa-ea94f2ebd14c.jpg">预览</a>
</body>
</html>