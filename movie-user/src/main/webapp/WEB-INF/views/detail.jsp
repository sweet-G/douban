<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>电影详情</title>
      <!-- Tell the browser to be responsive to screen width -->
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

              <!-- 文章列表开始 -->

              <div class="container">
                    <div class="box">
                           <div class="topic-head">
                                <span class="title">${movie.movieName}</span>
                                <div class="info">导演:${movie.directorName}</div>
                                <ul class="list-inline" style="background-color: #fff;margin-right: 6px;">
									<c:forEach items="${movie.typeList }" var="type">
	                                    <li><span class="label label-info">${type.text}</span> </li>
                                   </c:forEach>                                
                                </ul>
                            </div>
                            <div class="topic-body">
								${movie.content}
                             </div>
                            <div class="topic-toolbar">
                                   
                                <ul class="list-inline text-muted">
                                    <li><i class="fa fa-eye"></i>${movie.scanNum}</li>
                                    <li><i class="fa fa-commenting"></i>${movie.replyNum}</li>
                                </ul>
                            </div>
                        </div>
                        <!--box end-->
                
                        <div class="box" style="margin-top:20px;">
                            <div class="talk-item muted" style="font-size: 12px">
                                ${movie.replyNum}个回复 | 直到<span id="lastTime">${movie.lastTime}</span>
                            </div>
                            <c:forEach items="${replyList}" var="reply">
                            	<div class="talk-item">
                                <table class="talk-table">
                                    <tr>
                                        <td width="auto">
                                            <a href="" style="font-size: 12px">${reply.userName}</a> <span style="font-size: 12px" class="reply">${reply.createTime}</span>
                                            <br>
                                            <p style="font-size: 14px">${reply.content}</p>
                                        </td>
                                        
                                    </tr>
                                </table>
                            </div>
                            </c:forEach>
                        </div>
                
                        <div class="box" style="margin:20px 0px;">
                            <div class="talk-item muted" style="font-size: 12px"><i class="fa fa-plus"></i> 添加一条新回复</div>
                            <form action="/user/reply" method="post" id="replyForm" style="padding: 15px;margin-bottom:0px;">
                            	<input type="hidden" name="movieId" value="${movie.id}"/>
                                <textarea name="content" id="editor"></textarea>
                            </form>
                            <div class="talk-item muted" style="text-align: right;font-size: 12px">
                                <span class="pull-left">请发布自己的看法</span>
                                <button class="btn btn-primary" id="replyBtn">发布</button>
                            </div>
                        </div>
              </div>

             

              
	<%@ include file="include/js.jsp"%>

<script src="/static/plugins/editer/scripts/module.min.js"></script>
<script src="/static/plugins/editer/scripts/hotkeys.min.js"></script>
<script src="/static/plugins/editer/scripts/uploader.min.js"></script>
<script src="/static/plugins/editer/scripts/simditor.min.js"></script>

<script src="/static/plugins/moment/moment.js"></script>
<script src="/static/plugins/moment/moment.locals.js"></script>

<script>
    $(function(){
        var editor = new Simditor({
                textarea: $('#editor')
            });
       
       /*  moment.locale("zh-cn");
        $(".reply").text(function(){
        	var text = $(this).text();
        	return moment(text).forNow();
        }); */
        
        $("#replyBtn").click(function(){
        	var content = editor.getValue();
        	if(content){
        		alert("评论成功，请耐心等待审核");
        		$("#replyForm").submit();
        	} else{
        		layer.alert("请输入评论");
        	}
        })
        
    });
</script>   
</body>
</html>