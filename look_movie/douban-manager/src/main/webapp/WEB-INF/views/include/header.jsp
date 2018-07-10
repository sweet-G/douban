<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header class="main-header">
	<!-- Logo -->
	<a href="#" class="logo">
		<span class="logo-mini">电影</span>
		<span class="logo-lg">电影推荐</span>
	</a>
	<nav class="navbar navbar-static-top">
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button"> </a>

		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
			
				<li class="dropdown user user-menu">
					<a href="#" class="dropdown-toggle">
						<img src="/static/dist/img/user1-128x128.jpg" class="user-image" alt="User Image">
						<span class="hidden-xs">${sessionScope.admin.adminName}</span>
					</a>
				</li>

				<!-- Notifications: style can be found in dropdown.less -->
				<li class="dropdown notifications-menu">
					<a href="/reply/manage" class="dropdown-toggle">
						<i class="fa fa-bell-o"></i> 
						<span class="label label-warning" id="unreadcount"></span>
					</a>
				</li>

				<li class="dropdown user user-menu">
					<a href="http://192.168.1.102/login" class="dropdown-toggle">
						 <span class="hidden-xs">
							<i class="fa fa-film"></i>
						</span>
					</a>
				</li>

				<li class="dropdown user user-menu">
					<a href="/logout" class="dropdown-toggle">
						<span class="hidden-xs">
							<i class="fa fa-sign-in"></i>
						</span>
					</a>
				</li>
				
			</ul>
		</div>
	</nav>
</header>

<!-- =============================================== -->
