package com.kaishengit.web.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.service.MovieService;
import com.kaishengit.util.JsonResult;
import com.kaishengit.web.BaseServlet;

@WebServlet("/del")
public class MovieDelServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	MovieService movieService = new MovieService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		JsonResult result = null;
		
		try {
			movieService.del(id);
			result = JsonResult.success();
		} catch(Exception e) {
			result = JsonResult.error(e.getMessage());
		}
		sendJson(result,resp);
		
	}
}
