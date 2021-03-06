package com.lv.web.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lv.web.BaseServlet;

@WebServlet("/checkusername")
public class CheckUserNameServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("username");
		String pickName = req.getParameter("pickname");
		
		boolean isExist = userService.checkUserName(userName, pickName);
		
		sendJson(isExist, resp);
		
	}
	
}
