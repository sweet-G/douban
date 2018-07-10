package com.lv.web.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lv.service.MovieService;
import com.lv.web.BaseServlet;

@WebServlet("/movie/checkmoviename")
public class CheckMovieNameServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	MovieService movieService = new MovieService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String movieName = req.getParameter("filmName");
		String id = req.getParameter("id");
		
		boolean isExist = movieService.findMovieByName(movieName, id);
		sendJson(isExist, resp);
	}
	
}
