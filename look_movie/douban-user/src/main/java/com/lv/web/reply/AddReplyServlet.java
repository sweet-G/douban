package com.lv.web.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lv.entity.User;
import com.lv.exception.ServiceException;
import com.lv.util.Config;
import com.lv.util.JsonResult;
import com.lv.web.BaseServlet;

@WebServlet("/user/addreply")
public class AddReplyServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(AddReplyServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String movieId = req.getParameter("movieid");
		String replyContent = req.getParameter("reply");
		User user = (User) req.getSession().getAttribute("user");
		
		Integer userId = user.getId();
		Integer state = Config.REPLY_STATE_UNREVIEW;
		
		try {
			replyService.addReply(movieId, replyContent, userId, state);
			logger.info("用户: [{}--{}] 评论了电影", user.getUserName(), user.getPickName());
			
			sendJson(JsonResult.success(), resp);
		} catch (ServiceException e) {
			sendJson(JsonResult.error(e.getMessage()), resp);
		}
		
	}
	
}
