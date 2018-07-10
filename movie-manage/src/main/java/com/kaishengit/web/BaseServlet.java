package com.kaishengit.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class BaseServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void forward(String path,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/" + path + ".jsp").forward(req, resp);
	}
	
	protected void sendJson(Object result, HttpServletResponse resp) throws IOException {
		PrintWriter writer = resp.getWriter();
		writer.print(new Gson().toJson(result));
		resp.setContentType("application/json;charset=UTF-8");
		
		writer.flush();
		writer.close();
	}
	
}
