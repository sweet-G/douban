package com.lv.web.type;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.lv.entity.Movie;
import com.lv.entity.Type;
import com.lv.service.MovieService;
import com.lv.service.TypeService;
import com.lv.web.BaseServlet;

@WebServlet("/type/list")
public class TypeListServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	MovieService movieService = new MovieService();
	TypeService typeService = new TypeService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");

		List<Type> typeList = typeService.queryList();
		
		if(StringUtils.isNotEmpty(id)) {
			Movie movie = movieService.findMovieById(id);
			
			for(Type type : typeList) {
				int typeId = type.getId();
				for(Type currType : movie.getTypeList()) {
					int currTypeId = currType.getId();
					if(typeId == currTypeId) {
						type.setSelected(true);
						break;
					}
				}
			}
		}

		Map<String, Object> results = new HashMap<>();
		results.put("typeList", typeList);
		sendJson(results, resp);
		
		/*sendJson(JsonResult.success(typeList), resp);*/
	}
	
}
