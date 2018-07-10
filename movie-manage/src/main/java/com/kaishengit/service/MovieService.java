package com.kaishengit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

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
import com.kaishengit.util.EhcacheUtil;
import com.kaishengit.util.Page;

public class MovieService {

	MovieType movieType = new MovieType();
	MovieDao movieDao = new MovieDao();
	MovieTypeDao movieTypeDao = new MovieTypeDao();
	TypeDao typeDao = new TypeDao();
	ReplyDao replyDao = new ReplyDao();
	
	Movie movie = new Movie();
	
	public void addFilm(String movieName, String directorName, String area, String year, String imageName, String content,
			String[] types) {

		movie.setMovieName(movieName);
		movie.setDirectorName(directorName);
		movie.setArea(area);
		movie.setYear(year);
		movie.setImageName(imageName);
		movie.setContent(content);
		movie.setScanNum(Cofig.DEFULT_SCAN_NUM);
		movie.setReplyNum(Cofig.DEFULT_REPLY_NUM);
		
		// 通过jsoup将content中的第一个p标签作为简介存储到remark中
		Document doc = Jsoup.parseBodyFragment(content);
		String simpleContent = doc.select("p").first().toString();
		//将html中的标签进行过滤
		simpleContent = Jsoup.clean(simpleContent,Whitelist.none());
		if(simpleContent.length() > 100) {
			simpleContent = simpleContent.substring(0, 80) + "...";
		}
		movie.setSimpleContent(simpleContent);
		
		int id = movieDao.save(movie);
		
		for(String type : types) {
			int typeId = Integer.parseInt(type);
			
			MovieType movieType = new MovieType();
			movieType.setMovieId(id);
			movieType.setTypeId(typeId);
			
			movieTypeDao.save(movieType);
		}
	}

	public Page<Movie> findAllFilmByPage(int pageNo,String keys) {
		int count = movieDao.count(keys);
		Page<Movie> page = new Page<>(pageNo,count);
		
		Map<String,String> params = new HashMap<>();
		params.put("start", page.getStart().toString());
		params.put("pageSize", page.getPageSize().toString());
		params.put("keys", keys);
	
		
		List<Movie> movieList = movieDao.findAll(params);
		
            for(Movie movie : movieList) {
  			List<Type> typeList = typeDao.findByMovieId(movie.getId());
			movie.setTypeList(typeList);
 		}
    		page.setItems(movieList);
		
		return page;
	}

	public void del(String id) {
		if(StringUtils.isNumeric(id)) {
			int movieId = Integer.parseInt(id);
			
			movieDao.del(movieId);
			movieTypeDao.delMovieType(movieId);
		}else {
			throw new ServiceException("参数异常");
		}
		
	}

	public Movie findById(String id) {
		
		if(StringUtils.isNumeric(id)) {
			Movie movie = (Movie)EhcacheUtil.get(Cofig.EHCACHE_MOVIE_NAME, id);
			if(movie == null) {
				movie = movieDao.findMovieById(Integer.parseInt(id));
				EhcacheUtil.set(Cofig.EHCACHE_MOVIE_NAME, id, movie);
			}
			movie.setImageName(Cofig.get("download.prex") + movie.getImageName());
			return movie;
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

	public boolean findMovieName(String id, String movieName) {
		Movie movie = movieDao.findByMovieName(movieName);
		//如果movie为null，则为新增，返回true
		if(movie == null) {
			return true;
		}
		
		//如果movie不为null，并且id为空，则为新增是与其他电影名称重复；，返回false
		if(StringUtils.isEmpty(id)) {
			return false;
		}
		
		//如果movie不为null,并且id不为空，则为当前电影名称与自己的重复。返回true
		if(movie.getId() == Integer.parseInt(id)) {
			return true;
		} else {
			return false;
		}
		
	}

	public void editFilm(String id, String movieName, String directorName, String area, String year, String imageName,
			String content, String[] types) {
		Movie movie = movieDao.findMovieById(Integer.parseInt(id));
		
		if(movie != null) {
			movie.setMovieName(movieName);
			movie.setDirectorName(directorName);
			movie.setArea(area);
			movie.setYear(year);
			
			if(StringUtils.isNotEmpty(imageName)) {
				movie.setImageName(imageName);
			}
			
			movie.setContent(content);
			
			// 通过jsoup将content中的第一个p标签作为简介存储到remark中
			Document doc = Jsoup.parseBodyFragment(content);
			String simpleContent = doc.select("p").first().toString();
			//将html中的标签进行过滤
			simpleContent = Jsoup.clean(simpleContent,Whitelist.none());
			if(simpleContent.length() > 100) {
				simpleContent = simpleContent.substring(0, 80) + "...";
			}
			
			movie.setSimpleContent(simpleContent);
			
			movieDao.edit(movie);
			//删除原有的movieId
			movieTypeDao.delMovieType(movie.getId());
			
			for(String type : types) {
				int typeId = Integer.parseInt(type);
				movieType.setMovieId(Integer.parseInt(id));
				movieType.setTypeId(typeId);
				movieTypeDao.save(movieType);
			}
			
		} else {
			throw new ServiceException("该资源不存在");
		}
	}

	public int countReplyNumByState(Integer state) {
		return replyDao.countByState(state);
	}

	public Page<Reply> findReplyByStatePage(int pageNo,Integer state) {
		int count = replyDao.countByState(state);
		
		Page<Reply> page = new Page<>(pageNo,count);
		
		Map<String ,String> params = new HashMap<>();
		params.put("start", page.getStart().toString());
		params.put("pageSize", page.getPageSize().toString());
		params.put("state", state.toString());
		
		List<Reply> replyList = replyDao.findByState(params);
		
		page.setItems(replyList);
		return page;
	}

	public void reviewCommentary(String replyId, String state) {
		replyDao.review(Integer.parseInt(replyId),Integer.parseInt(state));
	}

	

}
