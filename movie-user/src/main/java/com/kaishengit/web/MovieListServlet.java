package com.kaishengit.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Movie;
import com.kaishengit.entity.Type;
import com.kaishengit.service.MovieService;
import com.kaishengit.util.Page;

@WebServlet("/user/index")
public class MovieListServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	MovieService movieService = new MovieService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String keys = req.getParameter("keys");
		String p = req.getParameter("p");
		String typeId = req.getParameter("typeId");
		
		int pageNo = 1;
		if(StringUtils.isNumeric(p)) {
			pageNo = Integer.parseInt(p);
		}
		
		//page对象，当前页的所有对象
		Page<Movie> page = movieService.findMovieTypeListByPage(pageNo, keys, typeId);
		//排行榜列表
		List<Movie> sortMovieList = movieService.findSortMovieList();
		//分类列表
		List<Type> typeList = movieService.findMovieTypeList(null);
		
		req.setAttribute("page", page);
		req.setAttribute("sortMovieList", sortMovieList);
		req.setAttribute("typeList", typeList);
		
		forward("index", req, resp);
		
		
	}
}
