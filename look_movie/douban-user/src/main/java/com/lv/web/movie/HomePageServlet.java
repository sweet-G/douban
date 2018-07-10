package com.lv.web.movie;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lv.entity.Movie;
import com.lv.entity.Page;
import com.lv.entity.Type;
import com.lv.web.BaseServlet;

@WebServlet("/user/homepage")
public class HomePageServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String p = req.getParameter("p");
		String keys = req.getParameter("keys");
		String typeName = req.getParameter("type");
		
		Page<Movie> page = movieService.findMovieList(p, keys, typeName);
		List<Movie> movieList = movieService.findMovieByScanNum();
		List<Type>	typeList = movieService.findTypeByGroup();
		
		req.setAttribute("typeList", typeList);
		req.setAttribute("movieList", movieList);
		req.setAttribute("keys", keys);
		req.setAttribute("page", page);
		
		forWard("homepage", req, resp);
	}
	
}
