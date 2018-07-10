<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>电影类型</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@ include file="../include/css.jsp" %>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

<%@ include file="../include/header.jsp" %>

<%-- <%@ include file="../include/sider.jsp" %>--%>
<jsp:include page="../include/sider.jsp">
	<jsp:param name="menu" value="type"/> 
</jsp:include>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
  
    <!-- Main content -->
    <section class="content">
      <!-- Default box -->
      <div class="box">
        <div class="box-header with-border">
          <form action="/type/listManage" class="form-inline pull-left" >
            <input type="text" class="form-control" name="keys" id="keys" value="${param.keys}" placeholder="关键字"/>
            <button class="btn btn-primary"><i class="fa fa-search"></i></button>
          </form>
          <a href="#" class="btn btn-success pull-right"  data-toggle="modal" data-target="#addModal">新增类型</a>
        </div>

        <div class="box-body">
          
          <table class="table" id="cust_table">
            <thead>
              <tr >
                <th>类型名称</th>
                <th>创建时间</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.items}" var="type">
            	<tr>
                <td>${type.text}</td>
                <td>${type.createTime}</td>
                <td>
                  <a href="javascript:;" rel="${type.id}" class="del">删除</a> 
                  <a href="javascript:;" class="update" typeName="${type.text}" rel="${type.id}">修改</a> 
                </td>
              </tr>
              
            </c:forEach>
             
            </tbody>
          </table>
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

  <div class="modal fade" id="addModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">新增类型</h4>
        </div>
        <div class="modal-body">
          <form action="/addType" method="post" class="form-horizontal" id="addForm">
              <div class="form-group">
                  <label class="col-sm-2 control-label">类型名称:</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="addTypeName" name="addTypeName" placeholder="请输入类型名称">
                 	 <input type="hidden" class="form-control" id="date" name="date">
                  </div>
                </div>

          </form>
        </div>
        <div class="modal-footer">
          <button class="btn btn-primary pull-left" id="addSave">保存</button>
          <button class="btn btn-default pull-left" data-dismiss="modal">取消</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div class="modal fade" id="updateModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">修改类型</h4>
        </div>
        <div class="modal-body">
          <form action="/type/edit" method="post" class="form-horizontal" id="editForm">
              <div class="form-group">
                  <label class="col-sm-2 control-label">类型名称:</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="editTypeName" name="editTypeName" placeholder="请输入类型名称">
                  </div>
                </div>

          </form>
        </div>
        <div class="modal-footer">
          <button class="btn btn-primary pull-left" id="editSave">保存</button>
          <button class="btn btn-default pull-left" data-dismiss="modal">取消</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->




</div>
<!-- ./wrapper -->

<%@ include file="../include/js.jsp" %>
<script src="/static/plugins/datatables/jquery.dataTables.min.js"></script>
<!-- page -->
<script src="/static/dist/js/jquery.twbsPagination.min.js"></script>

<script src="/static/dist/js/jquery.validate.min.js"></script>
<script>
    $(function(){
    	
       $(".update").click(function(){
    	   var typeName = $(this).attr("typeName");
    	   var typeId = $(this).attr("rel");
      	  $("#editTypeName").val(typeName);
      	  $("#editTypeName").attr("rel", typeId);
          $('#updateModal').modal({
            keyboard: false
          });
       });
       var saveEditeId;
       var saveEditName;
	   $("#editSave").click(function(){
		   saveEditName = $("#editTypeName").val();
		    saveEditeId = $("#editTypeName").attr("rel");
		   $("#editForm").submit();
	   })
    	
    		 $("#editForm").validate({
	    	  errorElement:"span",
	    	  errorClass:"text-danger",
	    	  rules:{
	    		  editTypeName:{
	    			  required:true
	    		  }  
	    	  },
	    	  messages:{
	    		  editTypeName:{
	    			  required:"请输入类型名称"
	    		  }
	    	  },
	    	  submitHandler:function(){
	    		  $.ajax({
	    			  url:"/type/edit",
	    			  type:"post",
	    			  data:{
	    				  "id" : saveEditeId,
	    				  "editTypeName" :saveEditName
	    			  },
	  				beforeSend:function(){
	  					$("#editSave").attr("disabled","disabled").text("保存中...");
	  				},
	  				success:function(res){
	  					if(res.state == "success") {
	  						window.location.href = "/type/listManage";
	  					} else{
	  						layer.msg("类型重复");
	  					}
	  				},
	  				error:function(){
	  					alert("系统繁忙");
	  				},
	  				complete:function(){
	  					$("#editSave").removeAttr("disabled").text("保存");
	  				}
	    		  })
	    	  }
	    	  
	       });
    	
		$("#addSave").click(function(){
			$("#addForm").submit();
		});
		$("#addForm").validate({
			errorElement:"span",
			errorClass:"text_danger",
			rules:{
				addTypeName:{
					required:true,
	    			remote:"/check/typeName"
				}
			},
			messages:{
				addTypeName:{
					required:"请输入类型名称",
	    			remote:"类型重复"
				}
			},
	       submitHandler:function(){
				$.ajax({
					url:"/addType",
					type:"post",
					data:$("#addForm").serialize(),
					beforeSend:function(){
						$("#addSave").attr("disabled","disabled").text("保存中...");
					},
					success:function(res){
						if(res.state == "success") {
							window.location.href = "/type/listManage";
						} 
					},
					error:function(){
						alert("系统繁忙");
					},
					complete:function(){
						$("#addSave").removeAttr("disabled").text("保存");
					}
				})
			}
	       
		});
      
		
    	var keys = "${param.keys}"
        $("#pagination").twbsPagination({
         totalPages:"${page.totalPage}",
         visiblePages:3,
         href:"/type/listManage?p={{number}}&keys=" + keys,
         first: "首页",
         prev: "上一页",
         next:"下一页",
         last:"末页"
       });  
    
			$(".del").click(function(){
				var id = $(this).attr("rel");
				 layer.confirm("确定要删除吗？",function(){
		    		   $.get("/delType",{"id":id}).done(function(data){
		    			   if(data.state == "success"){
		    				   window.location.href = "/type/listManager";
								layer.msg('删除成功');
		    			   }else{
		    				   layer.msg(data.message);
		    			   }
		    		   }).fail(function(){
		    			   layer.alert("系统繁忙");
		    		   })
		    	   })		
			});			
			
    });
    </script>
</body>
</html>
    