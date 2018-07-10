<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!-- Left side column. contains the sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
     
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        
          <li class="treeview <c:if test="${param.menu == 'movie' }">active</c:if>" >
            <a  href="/movie/list">
              <i class="fa fa-film "></i> <span>电影管理</span>
            </a>
            
          </li>
          <li class="treeview <c:if test="${param.menu =='type'}">active</c:if>">
            <a href="/type/listManage">
              <i class="fa fa-files-o"></i> <span>类型管理</span>
            </a>
           
          </li>
          <li class="treeview <c:if test="${param.menu =='commentary'}">active</c:if>">
            <a href="/commentary">
              <i class="fa fa-comments"></i> <span>评论管理</span>
            </a>
          </li>
          
        </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- =============================================== -->
    