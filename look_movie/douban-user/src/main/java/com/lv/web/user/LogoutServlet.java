package com.lv.web.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lv.entity.User;
import com.lv.web.BaseServlet;

@WebServlet("/logout")
public class LogoutServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(LogoutServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User)req.getSession().getAttribute("user");
		logger.info("用户: [{}--{}] 退出了系统", user.getUserName(), user.getPickName());
		
		if(user != null) {
			req.getSession().removeAttribute("user");
		}
		resp.sendRedirect("/login");
	}
	
	
}
