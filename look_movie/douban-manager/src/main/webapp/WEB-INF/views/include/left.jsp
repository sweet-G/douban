<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<aside class="main-sidebar">
	<section class="sidebar">
		<ul class="sidebar-menu">
			<li class="treeview <c:if test="${param.menu == 'movie'}">active</c:if>">
				<a href="/movie/list?menu=movie">
					<i class="fa fa-film "></i> <span>电影管理</span>
				</a>
			</li>
			<li class="treeview <c:if test="${param.menu == 'type'}">active</c:if>">
				<a href="/type/home?menu=type">
					<i class="fa fa-files-o"></i> <span>类型管理</span>
				</a>
				</li>
			<li class="treeview <c:if test="${param.menu == 'reply'}">active</c:if>">
				<a href="/reply/manage?menu=reply">
					<i class="fa fa-comments"></i> <span>评论管理</span>
				</a>
			</li>
		</ul>
	</section>
</aside>
