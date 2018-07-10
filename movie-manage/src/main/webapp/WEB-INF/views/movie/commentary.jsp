<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>待审核列表</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
   <%@ include file="../include/css.jsp" %>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

   <%@ include file="../include/header.jsp" %>
<%--   <%@ include file="../include/sider.jsp" %>--%>
<jsp:include page="../include/sider.jsp">
		<jsp:param name="menu" value="commentary"/> 
</jsp:include>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
  
    <!-- Main content -->
    <section class="content">
      <!-- Default box -->
      <div class="box">
        <div class="box-header with-border">
          <!--  -->
         <!--  <h5 class="pull-left">文章列表</h5> -->
         
        </div>
		
        <div class="box-body">
        <c:if test="${ empty page.items}">
        	 <div class="panel panel-default">
	                  <div class="panel-body">
	                    	暂无评论
	                  </div>
	            </div>
        </c:if>
        
        <c:forEach items="${page.items}" var="reply">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <a href=""><span class="media-heading"> 《${reply.movieName}》</span></a>
                     <span >评论者：${reply.userName}</span>
                  </div>
                  <div class="panel-body">
					 ${reply.content}
                  </div>
                  <div class="panel-footer">
                    <button class="btn btn-success pass" rel="${reply.id}">审核通过</button>
                    <button class="btn btn-default unpass" rel="${reply.id}">审核不通过</button>
                  </div>
            </div>
            
		</c:forEach>
        <br> 
        <ul id="pagination" class="pagination pull-right"></ul>
        </div>
        <!-- /.box-body -->
        
      </div>
      <!-- /.box -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

 <%@ include file="../include/foot.jsp" %>
</div>
<!-- ./wrapper -->

 <%@ include file="../include/js.jsp" %>

<script src="/static/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="/static/dist/js/jquery.twbsPagination.min.js"></script>
<script>
    $(function(){
      
        $("#pagination").twbsPagination({
         totalPages:"${page.totalPage}",
         visiblePages:3,
         href:"/commentary?p={{number}}",
         first: "首页",
         prev: "上一页",
         next:"下一页",
         last:"末页"
       });  
       
        $(".pass").click(function(){
        	var replyId = $(this).attr("rel");
        	$.post("/commentary",{"replyId": replyId, "state":1}).done(function(){
        		history.go(0);
        		alert("审核成功");
        	})
        	
        });
        
        $(".unpass").click(function(){
        	var replyId = $(this).attr("rel");
        	$.post("/commentary",{"replyId": replyId, "state":2}).done(function(){
        		history.go(0);
        	})
        })
       
    });
    </script>
</body>
</html>
    