package com.kaishengit.web.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.service.MovieService;
import com.kaishengit.web.BaseServlet;

@WebServlet("/check/movieName")
public class CheckMovieNameServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	MovieService movieService = new MovieService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取当前电影的id
		String id = req.getParameter("id");
		String movieName = req.getParameter("movieName");
		movieName = new String(movieName.getBytes("ISO8859-1"),"UTF-8");
		boolean res = movieService.findMovieName(id, movieName);
		
		sendJson(res,resp);
	}
}
