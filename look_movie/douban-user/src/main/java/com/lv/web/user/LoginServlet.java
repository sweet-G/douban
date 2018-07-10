package com.lv.web.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lv.entity.User;
import com.lv.exception.ServiceException;
import com.lv.util.JsonResult;
import com.lv.web.BaseServlet;

@WebServlet("/login")
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(SigninServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null) {
			resp.sendRedirect("/user/homepage");
		} else {
			
		
		
		
		String userName = null;
		String password = null;
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if("userName".equals(cookie.getName())) {
					userName = cookie.getValue();
				}
				if("password".equals(cookie.getName())) {
					password = cookie.getValue();
				}
				
			}
		}
		req.setAttribute("password", password);
		req.setAttribute("userName", userName);
		forWard("login", req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");
		
		try {
			User user = userService.login(userName, password);
			logger.info("用户: [{}--{}] 登录了系统", userName, user.getPickName());
			
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			
			Cookie rememberNameCookie = new Cookie("userName", user.getUserName());
			rememberNameCookie.setDomain("192.168.1.102");
			rememberNameCookie.setPath("/");
			rememberNameCookie.setMaxAge(60*60*24*7);
			rememberNameCookie.setHttpOnly(true);
			resp.addCookie(rememberNameCookie);
			
			if(remember != null) {
				Cookie cookie = new Cookie("password", password);
				cookie.setDomain("192.168.1.102");
				cookie.setPath("/");
				cookie.setMaxAge(60*60*24*7);
				cookie.setHttpOnly(true);
				
				resp.addCookie(cookie);
						
			} else {
				Cookie[] cookies = req.getCookies();
				if(cookies != null) {
					for(Cookie cookie : cookies) {
						if("password".equals(cookie.getName())) {
							cookie.setDomain("192.168.1.102");
							cookie.setPath("/");
							cookie.setMaxAge(0);
							resp.addCookie(cookie);
							break;
						}
					}
				}
			}
			sendJson(JsonResult.success(), resp);
		} catch (ServiceException e) {
			sendJson(JsonResult.error(e.getMessage()), resp);
		}
		
		
	}
	
	
}
