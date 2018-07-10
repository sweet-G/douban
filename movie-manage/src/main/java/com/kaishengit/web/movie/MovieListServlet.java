package com.kaishengit.web.movie;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Movie;
import com.kaishengit.service.MovieService;
import com.kaishengit.util.Page;
import com.kaishengit.web.BaseServlet;

@WebServlet("/movie/list")
public class MovieListServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	MovieService movieService = new MovieService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String keys = req.getParameter("keys");
		String p = req.getParameter("p");
		
		int pageNo = 1;
		
		 if(StringUtils.isNumeric(p)) {
			 pageNo = Integer.parseInt(p);
		 }
		 
		Page<Movie> page = movieService.findAllFilmByPage(pageNo,keys);
		
		req.setAttribute("page", page);
		
		forward("movie/list", req, resp);
	}
	
}
