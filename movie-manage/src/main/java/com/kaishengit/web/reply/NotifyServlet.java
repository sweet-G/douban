package com.kaishengit.web.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Cofig;
import com.kaishengit.service.MovieService;
import com.kaishengit.web.BaseServlet;

@WebServlet("/notify")
public class NotifyServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;

	MovieService movieService = new MovieService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num = movieService.countReplyNumByState(Cofig.REPLY_STATE_UNVIEW);
		
		sendJson(num,resp);
	}
	
}
