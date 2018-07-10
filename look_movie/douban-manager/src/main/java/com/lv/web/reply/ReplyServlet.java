package com.lv.web.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lv.entity.Reply;
import com.lv.service.ReplyService;
import com.lv.util.Config;
import com.lv.util.JsonResult;
import com.lv.util.Page;
import com.lv.web.BaseServlet;

@WebServlet("/reply/manage")
public class ReplyServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	ReplyService replyService = new ReplyService();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String p = req.getParameter("p");
		
		Page<Reply> page = replyService.findRepyByState(p, Config.REPLY_STATE_UNREVIEW);
		req.setAttribute("page", page);
		req.getRequestDispatcher("/WEB-INF/views/reply/reply.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int count = replyService.findReplyCountByState(Config.REPLY_STATE_UNREVIEW);
		JsonResult js = null;
		if(count > 0) {
			js = JsonResult.success(count);
		} else {
			js = JsonResult.error("暂无新评论");
		}
		sendJson(js, resp);
	}
	
	
}
