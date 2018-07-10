<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-default navbar-inverse navbar-fixed-top">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<a class="navbar-brand" href="#" style="margin-top:8px;"><i class="fa fa-film"></i></a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li <c:if test="${empty type.id}"> class="active"</c:if>
				 > <a href="/user/index"  style="margin-top:8px;">全部</a></li>
				<c:forEach items="${typeList}" var="type" varStatus="vs">
					<c:if test="${vs.count < 10 }">
						<li <c:if test="${param.typeId == type.id}"> class="active"</c:if>
						><a href="/user/index?typeId=${type.id}" style="margin-top:8px;">${type.text} </a></li>
					</c:if>
				</c:forEach>
			</ul>
			<form action="/user/index" class="navbar-form navbar-right">
				<div class="form-group" style="margin-top:8px;">
					<input type="text" name="keys" id="keys" value="${param.keys}" class="form-control" placeholder="Search">
				</div>
				<button class="btn btn-default" style="margin-top:8px;">
					<i class="fa fa-search"></i>
				</button>
			</form>
			 <ul class="nav navbar-nav">
			 <li class="dropdown user user-menu">
                      <a href="#" class="dropdown-toggle" >
                      <!-- 	<i class="fa fa-user"></i> -->
                        <img src="/static/dist/img/user7-128x128.jpg" class="user-image" alt="User Image" style="width:35px;margin-left:150px;padding:0px;">
                        <span class="hidden-xs">${sessionScope.user.userName}</span>
                      </a>
                    </li>
               </ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>
<!-- 导航栏结束 -->