package com.lv.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lv.entity.Admin;

@WebServlet("/logout")
public class LogoutServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(LogoutServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Admin admin = (Admin)req.getSession().getAttribute("admin");
		if(admin != null) {
			logger.info("{} 退出了系统", admin.getAdminName());
			req.getSession().removeAttribute("admin");
		}
		resp.sendRedirect("/login");
	}
	
	
}
