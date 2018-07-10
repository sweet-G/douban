package com.kaishengit.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Cofig;
import com.kaishengit.entity.Movie;
import com.kaishengit.entity.Reply;
import com.kaishengit.entity.Type;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.MovieService;

@WebServlet("/user/detail")
public class DetailServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	MovieService movieService = new MovieService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		System.out.println(id);
		try {
			
			Movie movie = movieService.findById(id);
			List<Type> typeList = movieService.findMovieTypeList(null);
			List<Reply> replyList = movieService.findReplyByMovieIdAndState(id,Cofig.REPLY_STATE_SUCCESS);

			req.setAttribute("movie", movie);
			req.setAttribute("typeList", typeList);
			req.setAttribute("replyList", replyList);
			
			forward("detail", req, resp);
		}catch(ServiceException e) {
			resp.sendError(404,e.getMessage());
		}
		
	}
}
