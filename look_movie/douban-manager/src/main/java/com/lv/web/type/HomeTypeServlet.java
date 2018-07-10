package com.lv.web.type;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.lv.util.Page;
import com.lv.web.BaseServlet;

@WebServlet("/type/home")
public class HomeTypeServlet extends BaseServlet{
	private static Logger logger = LoggerFactory.getLogger(HomeTypeServlet.class);
	private static final long serialVersionUID = 1L;
	TypeService typeService = new TypeService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String p = req.getParameter("p");
		String keys = req.getParameter("keys");
		
		Page<Type> page =  typeService.queryTypeList(p, keys);
		req.setAttribute("page", page);
		req.setAttribute("keys", keys);
		forWard("/movie/type", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String typeName = req.getParameter("typeName");
		String typeId = req.getParameter("typeId");
		String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		try {
			typeService.saveType(typeId, typeName, createTime);
			if(logger.isDebugEnabled()) {
				if(typeId == null) {
					logger.info("{}添加了类型: {}", getAdmin(req).getAdminName() , typeName);
				} else {
					logger.info("{}修改了类型: {}", getAdmin(req).getAdminName() , typeName);
				}
			}
			
			sendJson(JsonResult.success(), resp);
		} catch (ServiceException e) {
			sendJson(JsonResult.error(e.getMessage()), resp);
		}
	}
	
}
