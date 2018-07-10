package com.lv.web.movie;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lv.entity.Movie;
import com.lv.entity.Reply;
import com.lv.entity.Type;
import com.lv.web.BaseServlet;

@WebServlet("/user/detail")
public class DetailServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String filmName = req.getParameter("filmname");
		try {
			Movie movie = movieService.movieDetail(filmName);
			List<Type> typeList = movieService.findTypeByGroup();
			List<Reply> replyList = replyService.findReplyByMovieId(movie.getId());
			
			req.setAttribute("replyList", replyList);
			req.setAttribute("typeList", typeList);
			req.setAttribute("movie", movie);
		} catch (Exception e) {
			String message = e.getMessage();
			resp.sendError(404, message);
		}
		
		forWard("detail", req, resp);
	}
	
}
