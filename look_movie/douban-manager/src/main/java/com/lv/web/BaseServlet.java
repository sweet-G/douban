package com.lv.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lv.entity.Admin;

public class BaseServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void forWard(String path, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/" + path + ".jsp").forward(req, resp);
	}
	
	protected void sendJson(Object obj, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json;charset=UTF-8");
		
		PrintWriter writer = resp.getWriter();
		writer.print(new Gson().toJson(obj));
		
		writer.flush();
		writer.close();
	}
	
	protected Admin getAdmin(HttpServletRequest req) {
		return (Admin) req.getSession().getAttribute("admin");
	}
	
	
}
