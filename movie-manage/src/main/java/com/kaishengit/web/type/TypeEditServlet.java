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
@WebServlet("/type/edit")
public class TypeEditServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;

	TypeService typeService = new TypeService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		
		Type type = typeService.findTypeById(id);
		
		req.setAttribute("type", type);
		forward("type/listManage", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String editTypeName = req.getParameter("editTypeName");
		try {
			typeService.edit(id,editTypeName);
			
			JsonResult result = JsonResult.success();
			sendJson(result,resp);
		} catch(ServiceException e) {
			JsonResult result = JsonResult.error(e.getMessage());
			sendJson(result,resp);
		}
	}
	
}
