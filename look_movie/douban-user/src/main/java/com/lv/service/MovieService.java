package com.lv.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.lv.dao.MovieDao;
import com.lv.dao.TypeDao;
import com.lv.entity.Movie;
import com.lv.entity.Page;
import com.lv.entity.Type;
import com.lv.exception.ServiceException;
import com.lv.util.Config;

public class MovieService {
	MovieDao movieDao = new MovieDao();
	TypeDao typeDao = new TypeDao();

	public Page<Movie> findMovieList(String p, String keys, String typeName) throws IOException {
		p = StringUtils.isEmpty(p) ? "1" : p;
		p = StringUtils.isNumeric(p) ? p : "1";
		
		Map<String, String> params = new HashMap<>();
		
		params.put("keys", keys);
		params.put("typeName", typeName);
		int count = movieDao.count(params, "这个参数无意义");
		
		Page<Movie> page = new Page<>(Integer.valueOf(p), count);
		Map<String, String> param = new HashMap<>();
		param.put("keys", keys);
		param.put("typeName", typeName);
		param.put("start", page.getStart().toString());
		param.put("pageSize", page.getPageSize().toString());
		
		List<Movie> movieList = movieDao.queryMovieByParams(param, null);
		
		for(Movie movie : movieList) {
			List<Type> typeList = typeDao.queryTypeByMovieId(movie.getId());
			movie.setImgPath(Config.get("download.prex") + movie.getImgPath());
			movie.setTypeList(typeList);
		}
		page.settList(movieList);
		
		return page;
	}

	public List<Movie> findMovieByScanNum() {
		List<Movie> movieList =  movieDao.queryMovieByScanNum();
		/*for(Movie movie : movieList) {
			if(movie.getFilmName().length() > 5) {
				movie.setFilmName(movie.getFilmName().substring(0, 5) + "...");
			}
		}*/
		return movieList;
	}

	public List<Type> findTypeByGroup() {
		return typeDao.queryTypeListByGroup();
	}

	public Movie movieDetail(String filmName) throws Exception {
		if(StringUtils.isEmpty(filmName)) {
			throw new ServiceException("参数异常!");
		}
		
		Movie movie = movieDao.queryMovieByName(filmName);
		
		if(movie == null) {
			throw new ServiceException("参数异常!");
		}
		
		List<Type> typeList = typeDao.queryTypeByMovieId(movie.getId());
		movie.setImgPath(Config.get("download.prex") + movie.getImgPath());
		movie.setTypeList(typeList);
		
		
		return movie;
	}

}
