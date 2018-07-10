package com.lv.web.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lv.exception.ServiceException;
import com.lv.service.ReplyService;
import com.lv.util.JsonResult;
import com.lv.web.BaseServlet;

@WebServlet("/reply/edit")
public class ReplyEditServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private ReplyService replyService = new ReplyService();
	private static Logger logger = LoggerFactory.getLogger(ReplyEditServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String replyId = req.getParameter("replyid");
		String state = req.getParameter("state");
		try {
			replyService.editReplyState(replyId, state);
			if(logger.isDebugEnabled()) {
				logger.info("{}修改了评论状态", getAdmin(req).getAdminName());
			}
			sendJson(JsonResult.success(), resp);
		} catch (ServiceException e) {
			sendJson(JsonResult.error(e.getMessage()), resp);
		}
		
	}
	
}
