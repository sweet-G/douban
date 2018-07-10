package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.User;
import com.kaishengit.service.MovieService;

@WebServlet("/user/reply")
public class ReplyServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	MovieService movieService = new MovieService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String content = req.getParameter("content");
		String movieId = req.getParameter("movieId");
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		int userId = user.getId();
		 
		movieService.addReply(content,movieId,userId); 
	
		resp.sendRedirect("/user/detail?id=" + movieId);
	}
}
