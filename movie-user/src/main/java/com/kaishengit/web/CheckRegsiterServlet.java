package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.service.UserService;

@WebServlet("/check/reName")
public class CheckRegsiterServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;

	UserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("userName");
		boolean res = userService.findUserName(userName);
		
		sendJson(res,resp);
	}
}
