<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>电影类型</title>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<%@ include file="../include/css.jsp"%>
</head>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
<%@ include file="../include/header.jsp"%>
<%@ include file="../include/left.jsp"%>
<div class="content-wrapper">
<section class="content">
<div class="box">
	<div class="box-header with-border">
		<form action="" class="form-inline pull-left">
			<input type="text" class="form-control" value="${keys}" name="keys" id="keys" placeholder="关键字" />
			<button class="btn btn-primary"><i class="fa fa-search"></i></button>
		</form>
		<a href="#" class="btn btn-success pull-right" data-toggle="modal" data-target="#addModal">新增类型</a>
	</div>

	<div class="box-body">
		<table class="table" id="cust_table">
			<thead>
			<c:if test="${empty page.TList}"><h3>找不到该类型</h3></c:if>
            <c:if test="${not empty page.TList}">
				<tr>
					<th>类型名称</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
			</c:if>
			</thead>
			<tbody>
				<c:forEach var="type" items="${page.TList}">
				  <tr>
				    <input type="hidden" name="typeid" value="${type.id}" />
				    <td>${type.text}</td>
				    <td>${type.createTime}</td>
				    <td>
				      <a rel="${type.id}" typeName="${type.text}" class="delete" href="javascript:;">删除</a> / 
				      <a rel="${type.id}" typeName="${type.text}" class="update" href="javascript:;">修改</a>
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

<!-- ↓↓↓↓新增类型↓↓↓↓ -->
<div class="modal fade" id="addModal">
<div class="modal-dialog">
<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">新增节点</h4>
	</div>
	
	<div class="modal-body">
		<form action="" class="form-horizontal" id="addForm">
			<div class="form-group">
				<label class="col-sm-2 control-label">节点名称:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="addNnodeName"
						name="typeName" placeholder="请输入节点名称">
				</div>
			</div>
		</form>
	</div>
	
	<div class="modal-footer">
		<button id="saveTypeBtn" class="btn btn-primary pull-left">保存</button>
		<button class="btn btn-default pull-left" data-dismiss="modal">取消</button>
	</div>
</div>
</div>
</div>
<!-- ↑↑↑↑新增类型↑↑↑↑ -->

<!-- ↓↓↓↓修改类型↓↓↓↓ -->
<div class="modal fade" id="updateModal">
<div class="modal-dialog">
<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">更新节点</h4>
	</div>
	
	<div class="modal-body">
		<form action="/type/edit" class="form-horizontal" id="editForm">
		  <div class="form-group">
			<label class="col-sm-2 control-label">节点名称:</label>
			  <div class="col-sm-10">
				<input type="text" name="typeName" class="form-control" id="updateNodeName" placeholder="请输入节点名称">
			</div>
		  </div>
		</form>
	</div>
	
	<div class="modal-footer">
		<button id="updateTypeBtn" class="btn btn-primary pull-left">保存</button>
		<button class="btn btn-default pull-left" data-dismiss="modal">取消</button>
	</div>
</div>
</div>
</div>
<!-- ↑↑↑↑修改类型↑↑↑↑ -->

</div>
<%@ include file="../include/js.jsp"%>

<script src="/static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/dist/js/jquery.twbsPagination.min.js"></script>

<!-- ↓↓↓↓类型↓↓↓↓ -->
<!-- ↑↑↑↑类型↑↑↑↑ -->
<script>
$(function() {
	
	/* ↓↓模糊查询↓↓↓↓↓↓ */
	var keys = $("#keys").val();
	keys = encodeURIComponent(keys);
	$("#pagination").twbsPagination({
		totalPages : "${page.totalPage}",
		visiblePages : 3,
		href : "/type/home?p={{number}}&keys=" + keys,
		first : "首页",
		prev : "上一页",
		next : "下一页",
		last : "末页"
	});
	/* ↑↑模糊查询↑↑↑↑↑↑ */
	
	/* ↓↓修改类型↓↓点解修改,弹出模态框,把数据带到模态框,提交表单,把数据带到提交路径↓↓↓↓ */
	$(".update").click(function() {
		var typeName = $(this).attr("typeName");
		var typeId = $(this).attr("rel");
		
		$("#updateNodeName").val(typeName);
		$("#updateNodeName").attr("rel", typeId);
		$('#updateModal').modal({
			keyboard : false
		});
	});
	var typeName;
	var typeId; 
	$("#updateTypeBtn").click(function(){
		typeName = $("#updateNodeName").val();
		typeId = $("#updateNodeName").attr("rel");
		$("#editForm").submit();
	});
	$("#editForm").validate({
		rules : {
			"typeName" : {
				required : true
			},
		},
		messages : {
			"typeName" : {
				required : "类型不能为空"
			},
		},
		submitHandler : function() {
			$.ajax({
				url : "/type/home",
				type : "POST",
				data : {
					"typeName" : typeName,
					 "typeId" :  typeId
				},
				beforeSend : function() {
					$("#updateTypeBtn").attr("disabled", "disabled").text("保存中...")
				},
				success : function(res) {
					if (res.state == "success") {
						window.location.href = "/type/home";
					} else {
						layer.msg(res.message)
					}
				},
				error : function() {
					layer.msg("系统繁忙,请稍后再试!")
				},
				complete : function() {
					$("#updateTypeBtn").removeAttr("disabled").text("保存")
				}
			});
		},
	});
	/* ↑↑修改类型↑↑点解修改,弹出模态框,把数据带到模态框,提交表单,把数据带到提交路径↑↑↑↑ */

	/* ↓↓新增类型↓↓当点击添加按钮时,验证表单,然后通过使用ajax提交方式,将数据传输到后台,
		如果成功,刷新页面,如果失败,通过layer方式提示错误信息↓↓↓↓ */
	$("#saveTypeBtn").click(function() {
		$("#addForm").submit();
	});
	$("#addForm").validate({
		errorElement : "span",
		errorClass : "text-danger",
		rules : {
			"typeName" : {
				required : true
			},
		},
		messages : {
			"typeName" : {
				required : "类型不能为空"
			},
		},
		submitHandler : function() {
			$.ajax({
				url : "/type/home",
				type : "POST",
				data : $("#addForm").serialize(),
				beforeSend : function() {
					$("#saveTypeBtn").attr("disabled", "disabled").text("保存中...")
				},
				success : function(res) {
					if (res.state == "success") {
						window.location.href = "/type/home";
					} else {
						layer.msg(res.message)
					}
				},
				error : function() {
					layer.msg("系统繁忙,请稍后再试!")
				},
				complete : function() {
					$("#saveTypeBtn").removeAttr("disabled").text("保存")
				}
			});
		},
	});
	/* ↑↑新增类型↑↑当点击添加按钮时,验证表单,然后通过使用ajax提交方式,将数据传输到后台,
		如果成功,刷新页面,如果失败,通过layer方式提示错误信息↑↑↑↑ */
	
	/* ↓↓删除类型↓↓使用ajax提交方式，如果成功，刷新页面，如果失败，通过layer方式提示错误信息↓↓↓↓ */
	$(".delete").click(function() {
		var id = $(this).attr("rel");
		var typeName = $(this).attr("typeName");
		/* 灵感来自于GitHub */
		layer.prompt({
			title : '请输入要删除的类型名称,并确认',
			formType : 3
		}, function(text, index) {
			if (text == typeName) {
				layer.close(index);
				$.ajax({
					url : "/type/delete",
					data : {
						"id" : id
					},
					success : function(data) {
						if (data.state == "success") {
							window.location.href = "/type/home";
							layer.msg('删除成功');
						} else {
							layer.msg(data.message);
						}
					},
					error : function() {
						layer.msg("系统繁忙");
					},
				});
			} else {
				layer.close(index);
				layer.msg('电影名输入错误,删除已取消!');
			}
		});
	});
	/* ↑↑删除类型↑↑使用ajax提交方式，如果成功，刷新页面，如果失败，通过layer方式提示错误信息↑↑↑↑ */
	
});
</script>
</body>
</html>
