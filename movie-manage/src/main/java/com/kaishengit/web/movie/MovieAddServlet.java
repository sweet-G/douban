package com.kaishengit.web.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.MovieService;
import com.kaishengit.util.JsonResult;
import com.kaishengit.web.BaseServlet;

@WebServlet("/movie/add")
public class MovieAddServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	MovieService movieService = new MovieService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		forward("movie/add", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String movieName = req.getParameter("movieName");
		String directorName = req.getParameter("directorName");
		String area = req.getParameter("area");
		String year = req.getParameter("year");
		String imageName = req.getParameter("imageName");
		String content = req.getParameter("content");
		String[] types = req.getParameterValues("type");
		 
		try {
			
			movieService.addFilm(movieName, directorName, area, year, imageName, content, types);
			
			JsonResult result = JsonResult.success();
			sendJson(result,resp);
		} catch(ServiceException e) {
			JsonResult result = JsonResult.error(e.getMessage());
			sendJson(result,resp);
		}
		
	}

}
