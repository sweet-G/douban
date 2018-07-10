package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.UserService;
import com.kaishengit.util.JsonResult;

@WebServlet("/regsiter")
public class RegsiterServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;

	UserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		forward("regsiter", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String newPassword = req.getParameter("newPassword");
		String tel = req.getParameter("tel");
		String email = req.getParameter("eamil");
		String kaptcha = req.getParameter("kaptcha");
		
		try {
			String kaptchaExpected = (String)req.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

			userService.addRegsiter(userName,password,newPassword,tel,email,kaptcha,kaptchaExpected);
			
			JsonResult result = JsonResult.success();
			sendJson(result,resp);
		} catch(ServiceException e) {
			JsonResult result = JsonResult.error(e.getMessage());
			sendJson(result,resp);
		}
	}
}
