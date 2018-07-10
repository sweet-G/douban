package com.lv.web.movie;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lv.entity.Movie;
import com.lv.service.MovieService;
import com.lv.web.BaseServlet;

@WebServlet("/movie/delete")
public class DeleteMovieServlet extends BaseServlet{
	private static Logger logger = LoggerFactory.getLogger(DeleteMovieServlet.class);
	private static final long serialVersionUID = 1L;
	MovieService movieService = new MovieService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		try {
			Movie movie = movieService.deleteMovie(id);
			logger.info("{}删除了电影: {}", getAdmin(req).getAdminName(), movie.getFilmName());
			resp.sendRedirect("/movie/list");
		} catch (Exception e) {
			resp.sendError(404, e.getMessage());
		}
	}
	
}
