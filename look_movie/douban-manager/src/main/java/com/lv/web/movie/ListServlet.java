package com.lv.web.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lv.entity.Movie;
import com.lv.service.MovieService;
import com.lv.util.Page;
import com.lv.web.BaseServlet;

@WebServlet("/movie/list")
public class ListServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	MovieService movieService = new MovieService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String keys = req.getParameter("keys");
		String p = req.getParameter("p");
		
		Page<Movie> page = movieService.findMovieByPage(p, keys);
		
		req.setAttribute("page", page);
		req.setAttribute("keys", keys);
		forWard("/movie/list", req, resp);
	}
	
}
