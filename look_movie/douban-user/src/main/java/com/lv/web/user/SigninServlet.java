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
import com.lv.util.JsonResult;
import com.lv.web.BaseServlet;

@WebServlet("/signin")
public class SigninServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(SigninServlet.class);
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		forWard("signin", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("username");
		String pickName = req.getParameter("pickname");
		String password = req.getParameter("password");
		String tel = req.getParameter("tel");
		String email = req.getParameter("email");
		String kaptcha = req.getParameter("kaptcha");
		
		String kaptchaExpected = (String) req.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		
		HttpSession session = null;
		User user = null;
		if(kaptcha.equals(kaptchaExpected)) {
			user = new User();
			user.setUserName(userName);
			user.setPickName(pickName);
			user.setPassword(password);
			user.setTel(tel);
			user.setEmail(email);
		
			user = userService.signin(user);
			logger.info("[{}] 新注册了账户——pickName : {} , tel : {}", userName, pickName, tel);
			
			session = req.getSession();
			session.setAttribute("user", user);
			
			Cookie cookie = new Cookie("userName", userName);
			cookie.setDomain("192.168.1.102");
			cookie.setPath("/");
			cookie.setMaxAge(60*60*24*7);
			cookie.setHttpOnly(true);
			resp.addCookie(cookie);
			sendJson(JsonResult.success(), resp);
		} else {
			sendJson(JsonResult.error("验证码输入错误!"), resp);
		}
		
		
		
	}
	
	
	
}
