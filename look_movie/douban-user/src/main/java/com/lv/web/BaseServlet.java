package com.lv.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lv.service.MovieService;
import com.lv.service.ReplyService;
import com.lv.service.UserService;

public class BaseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected MovieService movieService = new MovieService();
	protected ReplyService replyService = new ReplyService();
	protected UserService userService = new UserService();
	
	
	public void forWard(String path, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/" + path + ".jsp").forward(req, resp);
	}

	protected void sendJson(Object data,  HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		
		writer.print(new Gson().toJson(data));
		
		writer.flush();
		writer.close();
	}
	
}
