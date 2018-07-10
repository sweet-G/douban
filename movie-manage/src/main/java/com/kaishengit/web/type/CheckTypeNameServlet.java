package com.kaishengit.web.type;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.service.TypeService;
import com.kaishengit.web.BaseServlet;

@WebServlet("/check/typeName")
public class CheckTypeNameServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;

	TypeService typeService = new TypeService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String addTypeName = req.getParameter("addTypeName");
		
		addTypeName = new String(addTypeName.getBytes("ISO8859-1"),"UTF-8");
		
		boolean res = typeService.findTypeName(id,addTypeName);
		
		sendJson(res,resp);
	}
}
