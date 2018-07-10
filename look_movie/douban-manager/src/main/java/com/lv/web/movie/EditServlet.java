package com.lv.web.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lv.entity.Movie;
import com.lv.exception.ServiceException;
import com.lv.service.MovieService;
import com.lv.service.TypeService;
import com.lv.util.Config;
import com.lv.util.JsonResult;
import com.lv.web.BaseServlet;

@WebServlet("/movie/edit")
public class EditServlet extends BaseServlet {
	private static Logger logger = LoggerFactory.getLogger(EditServlet.class);
	private static final long serialVersionUID = 1L;
	MovieService movieService = new MovieService();
	TypeService typeService = new TypeService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Movie movie = movieService.findMovieById(id);
		
		req.setAttribute("oldImgPath", movie.getImgPath());
		movie.setImgPath(Config.get("download.prex") + movie.getImgPath());
		req.setAttribute("movie", movie);
		forWard("/movie/edit", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String filmName = req.getParameter("filmName");
		String director = req.getParameter("director");
		String area = req.getParameter("area");
		String year = req.getParameter("year");
		String[] types = req.getParameterValues("type");
		String imgPath = req.getParameter("imageName");
		String content = req.getParameter("content");
		
		MovieService movieService = new MovieService();
		try {
			movieService.editMovie(id, filmName, director, area, year, imgPath, content, types);
			logger.info("{} 修改了电影: {}", getAdmin(req).getAdminName(), filmName);
			sendJson(JsonResult.success(), resp);
		} catch (ServiceException e) {
			sendJson(JsonResult.error(e.getMessage()), resp);
		}
		
		
	}
	
}
