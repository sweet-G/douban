package com.lv.web.type;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lv.entity.Type;
import com.lv.exception.ServiceException;
import com.lv.service.TypeService;
import com.lv.util.JsonResult;
import com.lv.web.BaseServlet;

@WebServlet("/type/delete")
public class DeleteTypeServlet extends BaseServlet{
	private static Logger logger = LoggerFactory.getLogger(DeleteTypeServlet.class);
	private static final long serialVersionUID = 1L;
	TypeService typeService = new TypeService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		try {
			Type type = typeService.deleteTypeByTypeId(id);
			logger.info("{}删除了类型: {}", getAdmin(req).getAdminName(), type.getText());
			sendJson(JsonResult.success(), resp);
		} catch (ServiceException e) {
			sendJson(JsonResult.error(e.getMessage()), resp);
		}
	}
	
	
	
}
