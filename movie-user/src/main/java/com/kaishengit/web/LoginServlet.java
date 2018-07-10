package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.UserService;
import com.kaishengit.util.JsonResult;

@WebServlet("/login")
public class LoginServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	UserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if("userName".equals(cookie.getName())) {
					req.setAttribute("userName", cookie.getValue());
					break;
				}
			}
		}
		
		forward("login", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");

		try {
			User user = userService.login(userName, password);
			
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			
			if(StringUtils.isNotEmpty(remember)) {
				Cookie cookie = new Cookie("userName",userName); 
				
				cookie.setDomain("192.168.1.130");
				cookie.setPath("/");
				cookie.setMaxAge(60*60*24);
				cookie.setHttpOnly(true);
				
				resp.addCookie(cookie);
			} else {
				Cookie[] cookies = req.getCookies();
				if(cookies != null) {
					for(Cookie cookie : cookies) {
						if("userName".equals(cookie.getName())) {
							cookie.setPath("/");
							cookie.setMaxAge(0);
							
							resp.addCookie(cookie);
							break;
						}
					}
				}
				
			}
			
			JsonResult result = JsonResult.success(user);			
			sendJson(result,resp);
		} catch(ServiceException e) {
			JsonResult result = JsonResult.error(e.getMessage());
			sendJson(result, resp);
		}
		
	}
	
}
