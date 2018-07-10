<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default navbar-inverse navbar-fixed-top">
<div class="container">
	<div class="navbar-header">
		<a class="navbar-brand" href="#"><i class="fa fa-film"></i></a>
	</div>

	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li class="
			<c:if test="${empty param.type}">
				active
			</c:if>
			"><a href="/user/homepage">全部</a></li>
			<c:forEach items="${typeList}" var="type" varStatus="vs">
			<li class="
			<c:if test="${type.text == param.type}">
				active
			</c:if>
			">
				<a href="/user/homepage?type=${type.text}">${type.text}</a>
			</li>
			</c:forEach>
		</ul>
		
		<form class="navbar-form navbar-right">
			<div class="form-group">
				<input type="text" name="keys" value="${keys}" class="form-control" placeholder="Search">
			</div>
			<button id="keysBtn" class="btn btn-default"><i class="fa fa-search"></i></button>
		</form>
		
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown user user-menu ">
				<a href="#" class="dropdown-toggle">
					<img src="/static/dist/img/user1-128x128.jpg" class="user-image" alt="User Image">
					<span class="hidden-xs">${sessionScope.user.pickName}</span>
				</a>
			</li>
			<li class="dropdown user user-menu">
				<a href="http://192.168.1.102:8080" class="dropdown-toggle">
					 <span class="hidden-xs">
						<i class="fa fa-film"></i>
					</span>
				</a>
			</li>
			<li class="dropdown user user-menu navbar-right">
				<a href="/logout" class="dropdown-toggle">
					<span class="hidden-xs">
						<i class="fa fa-sign-in"></i>
					</span>
				</a>
			</li>
		</ul>
			
		</div>
	</div>
</div>
</nav>
<!-- 导航栏结束 -->
    