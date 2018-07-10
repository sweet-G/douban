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
  <%@ include file="../include/css.jsp"%>
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
      <div class="box">
        <div class="box-header with-border">
        
          <form action="/movie/list" class="form-inline pull-left" >
            <input type="text" class="form-control" name="keys" value="${param.keys}" id="keys" placeholder="关键字"/>
            <button class="btn btn-primary"><i class="fa fa-search"></i></button>
          </form>
          
          <a href="/movie/add" class="btn btn-success pull-right">新增电影</a>
        </div>

        <div class="box-body">
          
          <table class="table" id="cust_table">
            <thead>
            <c:if test="${empty page.TList}"><h3>找不到该资源</h3></c:if>
            <c:if test="${not empty page.TList}">
              <tr >
                <th>名称</th>
                <th>导演</th>
                <th>类型</th>
                <th>上映年份</th>
                <th>地区</th>
                <th>操作</th>
              </tr>
              </c:if>
            </thead>
            
            <tbody>
            	
              <c:forEach items="${page.TList}" var="movie">
                <tr>
                  <td>${movie.filmName}</td>
                  <td>${movie.filmDirector}</td>
                  <td>
                  <c:forEach items="${movie.typeList}" var="type" varStatus="vs">
                    ${type.text}
                    <c:if test="${not vs.last}">/</c:if>
                  </c:forEach>
                  </td>
                  <td>${movie.year}</td>
                  <td>${movie.area}</td>
                  <td>
                    <a class="delete" filmName="${movie.filmName}" rel="${movie.id}" href="javascript:;">删除</a> /
                    <a href="/movie/edit?id=${movie.id}">修改</a> 
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
          <br> 
          <ul id="pagination" class="pagination pull-right"></ul>
        </div>
      </div>
    </section>
  </div>
<%@ include file="../include/footer.jsp"%>
</div>
 <%@ include file="../include/js.jsp"%>
<!-- DataTables -->
<script src="/static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- page -->
<script src="/static/dist/js/jquery.twbsPagination.min.js"></script>
<script>
    $(function(){
    	$(".delete").click(function(){
    		var id = $(this).attr("rel");
    		var filmName = $(this).attr("filmName");
    		/* 灵感来自于GitHub */
    		layer.prompt({title: '请输入要删除的电影名,并确认', formType: 3}, function(text, index){
    			if(text == filmName){
    				layer.close(index);
    				window.location.href="/movie/delete?id=" + id;
    				layer.msg('删除成功');
    			} else {
    				layer.close(index);
    				layer.msg('电影名输入错误,删除已取消!');
    			}
   			});
  		});
    	var keys = "${param.keys}";
    	key = encodeURIComponent(keys);
        $("#pagination").twbsPagination({
         totalPages:"${page.totalPage}",
         visiblePages:3,
         href:"/movie/list?p={{number}}&keys=" + key,
         first: "首页",
         prev: "上一页",
         next:"下一页",
         last:"末页"
       });  
    });
    </script>
</body>
</html>
