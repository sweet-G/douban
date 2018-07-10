package com.kaishengit.web.movie;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Admin;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.AdminService;
import com.kaishengit.util.JsonResult;
import com.kaishengit.web.BaseServlet;

@WebServlet("/login")
public class LoginServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	AdminService adminService = new AdminService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if("adminName".equals(cookie.getName())) {
					req.setAttribute("adminName", cookie.getValue());
					break;
				}
			}
			
		}
		
		forward("login", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String adminName = req.getParameter("adminName");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");
		
		try {
			Admin admin = adminService.login(adminName, password);
			
			// 将admin对象放入session
			
			HttpSession session = req.getSession();
			session.setAttribute("admin", admin);
			
			if(StringUtils.isNotEmpty(remember)) {
				Cookie cookie = new Cookie("adminName", adminName);
				cookie.setDomain("192.168.1.130");
				cookie.setPath("/");
				cookie.setMaxAge(60 * 60 * 24 * 7);
				cookie.setHttpOnly(true);
				
				resp.addCookie(cookie);
			} else {
				Cookie[] cookies = req.getCookies();
				
				if(cookies != null) {
					for(Cookie cookie : cookies) {
						if("adminName".equals(cookie.getName())) {
							cookie.setDomain("192.168.1.130");
							cookie.setPath("/");
							cookie.setMaxAge(0);
							
							resp.addCookie(cookie);
							break;
						}
					}
				}
				
			}
			
			JsonResult result = JsonResult.success(admin);
			sendJson(result, resp);
			
		} catch(ServiceException e) {
			JsonResult result = JsonResult.error(e.getMessage());
			sendJson(result, resp);
		}
		
	}
	
}
