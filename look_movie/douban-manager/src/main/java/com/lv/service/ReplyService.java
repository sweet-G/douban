package com.lv.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.lv.dao.MovieDao;
import com.lv.dao.ReplyDao;
import com.lv.entity.Movie;
import com.lv.entity.Reply;
import com.lv.exception.ServiceException;
import com.lv.util.Config;
import com.lv.util.Page;

public class ReplyService {
	ReplyDao replyDao = new ReplyDao();
	
	public Page<Reply> findRepyByState(String p, Integer replyStateUnreview) {
		
		if(StringUtils.isEmpty(p)) {
			p = "1";
		}
		
		if(!StringUtils.isNumeric(p)) {
			p = "1";
		}
		
		Page<Reply> page = new Page<>(Integer.valueOf(p), replyDao.count(replyStateUnreview));
		
		if(replyStateUnreview > 2 || replyStateUnreview < 0) {
			throw new ServiceException("参数异常!");
		}
		List<Integer> paramsList = new ArrayList<>();
		paramsList.add(replyStateUnreview);
		paramsList.add(page.getStart());
		paramsList.add(page.getPageSize());
		
		List<Reply> replyList = replyDao.queryRepyByState(paramsList);
		page.setTList(replyList);
		return page;
	}

	public int findReplyCountByState(Integer replyStateUnreview) {
		int count = replyDao.count(replyStateUnreview);
		return count;
	}

	public void editReplyState(String replyId, String state) throws ServiceException {
		if(StringUtils.isEmpty(replyId)) {
			throw new ServiceException("参数异常!");
		}
		if(!StringUtils.isNumeric(replyId)) {
			throw new ServiceException("参数异常!");
		}
		Reply reply = replyDao.queryReplyByReplyId(Integer.valueOf(replyId));
		
		reply.setState(Integer.valueOf(state));
		replyDao.updateReply(reply);
		
		if(Integer.valueOf(state) == Config.REPLY_STATE_SUCCESS) {
			
			MovieDao movieDao = new MovieDao();
			Movie movie = movieDao.queryMovieById(reply.getMovieid());
			movie.setReplyNum(movie.getReplyNum() + 1);
			movie.setLastReplyTime(reply.getCreateTime());
			movieDao.update(movie);
			
		}
	}

}
