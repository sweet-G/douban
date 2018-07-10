package com.kaishengit.web.type;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.TypeService;
import com.kaishengit.util.JsonResult;
import com.kaishengit.web.BaseServlet;

@WebServlet("/addType")
public class TypeAddServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	TypeService typeService = new TypeService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String addTypeName = req.getParameter("addTypeName");
		
		try {
			typeService.addType(addTypeName);
			
			JsonResult result = JsonResult.success();
			sendJson(result,resp);
		}catch(ServiceException e) {
			JsonResult result = JsonResult.error(e.getMessage());
			sendJson(result,resp);
		}
	}
}
