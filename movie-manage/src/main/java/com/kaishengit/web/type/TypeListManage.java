package com.kaishengit.web.type;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Type;
import com.kaishengit.service.TypeService;
import com.kaishengit.util.Page;
import com.kaishengit.web.BaseServlet;

@WebServlet("/type/listManage")
public class TypeListManage extends BaseServlet{

	private static final long serialVersionUID = 1L;

	TypeService typeService = new TypeService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String keys = req.getParameter("keys");
		String p = req.getParameter("p");
		
		int pageNo = 1;
		if(StringUtils.isNumeric(p)) {
			pageNo = Integer.parseInt(p);
		}
		
		Page<Type> page = typeService.findTypeListByPage(pageNo,keys);
		req.setAttribute("page", page);
		forward("type/listManage", req, resp);
	}
}
