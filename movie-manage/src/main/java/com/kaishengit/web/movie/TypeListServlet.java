package com.kaishengit.web.movie;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Type;
import com.kaishengit.service.MovieService;
import com.kaishengit.util.JsonResult;
import com.kaishengit.web.BaseServlet;

@WebServlet("/type/list")
public class TypeListServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	MovieService movieServie = new MovieService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String mid = req.getParameter("mid");
		
		try {
			List<Type> typeList = movieServie.findMovieTypeList(mid);
			Map<String, Object> maps = new HashMap<>();
			maps.put("state", "success");
			maps.put("results", typeList);
			sendJson(maps, resp);
		} catch (Exception e) {
			sendJson(JsonResult.error(e.getMessage()), resp);
		}
	}
	
}
