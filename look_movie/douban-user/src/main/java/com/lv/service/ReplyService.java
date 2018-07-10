package com.lv.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.lv.dao.MovieDao;
import com.lv.dao.ReplyDao;
import com.lv.entity.Movie;
import com.lv.entity.Reply;
import com.lv.exception.ServiceException;
import com.lv.util.Config;

public class ReplyService {
	private ReplyDao replyDao = new ReplyDao();
	private MovieDao movieDao = new MovieDao();
	
	public void addReply(String movieId, String replyContent, Integer userId, Integer state) throws ServiceException {
		if(StringUtils.isEmpty(movieId)) {
			throw new ServiceException("参数异常!");
		}
		
		if(!StringUtils.isNumeric(movieId)) {
			throw new ServiceException("参数异常!");
		}
		
		Movie movie = movieDao.queryMovieByMovieId(movieId);
		
		if(movie == null) {
			throw new ServiceException("参数异常!");
		}
		
		Reply reply = new Reply();
		
		reply.setContent(replyContent);
		reply.setState(state);
		reply.setUserId(userId);
		reply.setMovieId(Integer.valueOf(movieId));
		
		replyDao.saveReply(reply);
	}

	public List<Reply> findReplyByMovieId(Integer id) {
		return replyDao.queryReplyByMovieId(id, Config.REPLY_STATE_SUCCESS);
	}
	
	
	
	
	

}
