package com.kaishengit.web.type;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Type;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.TypeService;
import com.kaishengit.util.JsonResult;
import com.kaishengit.web.BaseServlet;

@WebServlet("/delType")
public class TypeDelServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;

	TypeService typeService = new TypeService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		try {
			Type type = typeService.delType(id);
			sendJson(JsonResult.success(), resp);
		} catch(ServiceException e) {
			sendJson(JsonResult.error(e.getMessage()), resp);
		}
	}
}
