<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>电影列表</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
 <%@ include file="../include/css.jsp" %>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

<%@ include file="../include/header.jsp" %>
<%-- <%@ include file="../include/sider.jsp" %>--%>
<jsp:include page="../include/sider.jsp">
		<jsp:param name="menu" value="movie"/> 
</jsp:include>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
  
    <!-- Main content -->
    <section class="content">
      <!-- Default box -->
      <div class="box">
        <div class="box-header with-border">
          <form action="/movie/list" class="form-inline pull-left" >
            <input type="text" class="form-control" name="keys" id="keys" value="${param.keys}" placeholder="关键字"/>
            <button class="btn btn-primary"><i class="fa fa-search"></i></button>
          </form>
          
          <a href="/movie/add" class="btn btn-success pull-right">新增电影</a>
        </div>

        <div class="box-body">
          
          <table class="table" id="cust_table">
            <thead>
              <tr >
                <th>名称</th>
                <th>导演</th>
                <th>类型</th>
                <th>上映年份</th>
                <th>地区</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.items}" var="movie">
					<tr>
						<td>${movie.movieName}</td>
						<td>${movie.directorName}</td>
						<td>
							<c:forEach items="${movie.typeList}" var="type" varStatus="vs">
								${type.text}
								<c:if test="${not vs.last}">,</c:if>
							</c:forEach>
						</td>
						<td>${movie.year}</td>
						<td>${movie.area}</td>
						<td><a href="javascript:;" rel="${movie.id}" class="del">删除</a>
						 <a href="/movie/edit?id=${movie.id}" >修改</a>
						</td>
					</tr>
				</c:forEach>
            </tbody>
          </table>
          
          <%-- <nav aria-label="Page navigation">
			  <ul class="pagination pull-right">
			    
			    <li><a href="/movie/list">首页</a></li>
			    <li><a href="/movie/list?p=${page.pageNo - 1}">上一页</a></li>
			    <li><a href="/movie/list?p=${page.pageNo + 1}">下一页</a></li>
			    <li><a href="/movie/list?p=${page.totalPage}">末页</a></li>
			  
			  </ul>
			</nav> --%>
          
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
<!-- page -->
<script src="/static/dist/js/jquery.twbsPagination.min.js"></script>
<script>
    $(function(){
      
    	var keys = "${param.keys}";   
        //keys = decodeURIComponent(keys);

    	
        $("#pagination").twbsPagination({
         totalPages:"${page.totalPage}",
         visiblePages:3,
         href:"/movie/list?p={{number}}&keys=" + keys,
         first: "首页",
         prev: "上一页",
         next:"下一页",
         last:"末页"
       });  
      
       $(".del").click(function(){
    	   var id = $(this).attr("rel");
    	   layer.confirm("确定要删除吗？",function(){
    		   $.get("/del",{"id":id}).done(function(json){
    			   if(json.state == "success"){
    				   layer.alert("删除成功",function(){
    					   // 0:刷新当前页面 -1:后退 1:前进
    					   history.go(0);
    				   })
    			   }
    		   }).fail(function(){
    			   layer.alert("系统繁忙");
    		   })
    	   })
       })
       
       
       
       
       /* $(".del").click(function(){
			if(confirm("确定要删除么?")) {
				var id = $(this).attr("rel");
				window.location.href = "/del?id=" + id;
			}				
		})		 */
       
    });
    </script>
</body>
</html>
    