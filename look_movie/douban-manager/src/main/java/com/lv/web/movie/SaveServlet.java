package com.lv.web.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lv.service.MovieService;
import com.lv.util.JsonResult;
import com.lv.web.BaseServlet;

@WebServlet("/movie/save")
public class SaveServlet extends BaseServlet{
	private static Logger logger = LoggerFactory.getLogger(SaveServlet.class);
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String filmName = req.getParameter("filmName");
		String director = req.getParameter("director");
		String area = req.getParameter("area");
		String year = req.getParameter("year");
		String[] types = req.getParameterValues("type");
		String imgPath = req.getParameter("imageName");
		String content = req.getParameter("content");
		
		MovieService movieService = new MovieService();
		movieService.addFilm(filmName, director, area, year, imgPath, content, types);
		logger.info("{} 新增了电影: {}", getAdmin(req).getAdminName(), filmName);
		JsonResult res = JsonResult.success();
		sendJson(res, resp);
		
	}
	
	
}
