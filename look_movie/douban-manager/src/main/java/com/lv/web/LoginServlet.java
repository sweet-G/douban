package com.lv.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lv.entity.Admin;
import com.lv.exception.ServiceException;
import com.lv.service.AdminService;
import com.lv.util.JsonResult;

@WebServlet("/login")
public class LoginServlet extends BaseServlet {
	private Logger logger = LoggerFactory.getLogger(LoginServlet.class);
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Cookie[] cookies = req.getCookies();
		String adminName = null;
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if("adminName".equals(cookie.getName())) {
					adminName = cookie.getValue();
					break;
				}
			}
		}
		req.setAttribute("adminName", adminName);
		forWard("Login", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AdminService adminService = new AdminService();
		String adminName = req.getParameter("adminName");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");
		
		
		try {
			Admin admin = adminService.login(adminName, password);
			logger.info("{}登录了管理系统", adminName);
			
			HttpSession session = req.getSession();
			session.setAttribute("admin", admin);
			
			if(remember != null) {
				Cookie cookie = new Cookie("adminName", adminName);
				cookie.setDomain("localhost");
				cookie.setPath("/");
				cookie.setMaxAge(60*60*24*7);
				cookie.setHttpOnly(true);
				
				resp.addCookie(cookie);
			} else {
				Cookie[] cookies = req.getCookies();
				if(cookies != null) {
					for(Cookie cookie : cookies) {
						if("adminName".equals(cookie.getName())) {
							cookie.setDomain("localhost");
							cookie.setPath("/");
							cookie.setMaxAge(0);
							resp.addCookie(cookie);
							break;
						}
					}
				}
			}
			JsonResult res = JsonResult.success(admin);
			sendJson(res, resp);
		} catch (ServiceException e) {
			JsonResult res = JsonResult.error(e.getMessage());
			sendJson(res, resp);
		}
		
	}
	
}
