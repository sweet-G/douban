package com.kaishengit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.dao.MovieDao;
import com.kaishengit.dao.MovieTypeDao;
import com.kaishengit.dao.ReplyDao;
import com.kaishengit.dao.TypeDao;
import com.kaishengit.entity.Cofig;
import com.kaishengit.entity.Movie;
import com.kaishengit.entity.MovieType;
import com.kaishengit.entity.Reply;
import com.kaishengit.entity.Type;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Page;

public class MovieService {

	MovieType movieType = new MovieType();
	MovieDao movieDao = new MovieDao();
	MovieTypeDao movieTypeDao = new MovieTypeDao();
	TypeDao typeDao = new TypeDao();
	ReplyDao replyDao = new ReplyDao();
	
	Movie movie = new Movie();
	Reply reply = new Reply();

	public Movie findById(String id) {
		
		if(StringUtils.isNumeric(id)) {
			Movie movie = movieDao.findMovieById(Integer.parseInt(id));
			
			if(movie != null) {
				movie.setScanNum(movie.getScanNum() + 1);
				movieDao.edit(movie);
				
				movie.setImageName(Cofig.get("download.prex") + movie.getImageName());
				
				List<Type> typeList = typeDao.findByMovieId(movie.getId());
				movie.setTypeList(typeList);
				
				return movie;
			} else {
				throw new ServiceException("参数异常");

			}
		} else {
			throw new ServiceException("参数异常");
		}
	}

	public List<Type> findMovieTypeList(String mid) {
		//如果mid不为空则为修改
		if(StringUtils.isNotEmpty(mid)) {
			List<Type> typeList = typeDao.findMovieType();
			if(StringUtils.isNumeric(mid)) {
				List<Type> types = typeDao.findByMovieId(Integer.parseInt(mid));
				for(Type type : typeList) {
					for(Type chooseType : types) {
						 if(type.getId() == chooseType.getId()) {
							 type.setSelected(true);
							 break;
						 }
					}
				}
				return typeList;
			} else {
				throw new ServiceException("参数异常");
			}
		} else {
			//如果 mid为空则为新增
			return typeDao.findMovieType();
		}
	}

	public Page<Movie> findMovieTypeListByPage(int pageNo, String keys, String typeId) {
		
		if(StringUtils.isNotEmpty(typeId) && !StringUtils.isNumeric(typeId)) {
			throw new ServiceException("参数异常");
		}
		
		Map<String, String> params = new HashMap<>();
		params.put("keys", keys);
		params.put("typeId", typeId);
		
		int count = movieDao.count(params);
		Page<Movie> page = new Page<>(pageNo,count);
		
		params.put("start", String.valueOf(page.getStart()));
		params.put("pageSize", String.valueOf(page.getPageSize()));
		
		List<Movie> movieList = movieDao.findByParams(params);
		
		for(Movie movie : movieList) {
			List<Type> typeList = typeDao.findByMovieId(movie.getId());
			movie.setTypeList(typeList);
			
			movie.setImageName(Cofig.get("download.prex") + movie.getImageName());
			
		}
		
		page.setItems(movieList);
		return page;
	}

	public List<Movie> findSortMovieList() {
		return movieDao.findSortMovie();
	}

	public List<Reply> findReplyByMovieIdAndState(String id, Integer state) {
		return replyDao.findByMovieIdAndState(Integer.parseInt(id), state);
	}

	public void addReply(String content, String movieId, int userId) {
		reply.setContent(content);
		reply.setMovieId(Integer.parseInt(movieId));
		reply.setUserId(userId);
		reply.setState(Cofig.REPLY_STATE_UNVIEW);
		
		int id = replyDao.add(reply);
		
		Reply reply = replyDao.findCreateTime(id);
		//修改对应的评论次数
		Movie movie = movieDao.findMovieById(Integer.parseInt(movieId));
		if(movie != null) {
			movie.setReplyNum(movie.getReplyNum() + 1);
			movie.setLastTime(reply.getCreateTime().toString());
			movieDao.edit(movie);
		} else {
			throw new ServiceException("该电影不存在或已被删除");
		}
		
	}

	

}
