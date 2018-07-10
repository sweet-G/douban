package com.kaishengit.web.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Cofig;
import com.kaishengit.entity.Reply;
import com.kaishengit.service.MovieService;
import com.kaishengit.util.Page;
import com.kaishengit.web.BaseServlet;

@WebServlet("/commentary")
public class CommentaryServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;

	MovieService movieService = new MovieService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String p = req.getParameter("p");
		
		int pageNo = 1;
		if(StringUtils.isNumeric(p)) {
			pageNo = Integer.parseInt(p);
		}
		
		Page<Reply> page = movieService.findReplyByStatePage(pageNo,Cofig.REPLY_STATE_UNVIEW);
		
		req.setAttribute("page", page);
		forward("movie/commentary", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String replyId = req.getParameter("replyId");
		String state = req.getParameter("state");
		
		movieService.reviewCommentary(replyId,state);
	}
	
}
