package com.kaishengit.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Movie;
import com.kaishengit.util.DbHelp;

public class MovieDao {

	public int save(Movie movie) {
		String sql = "insert into t_movie (movie_name, director_name, area, year, image_name, content, simplecontent, scan_num, reply_num) values (?,?,?,?,?,?,?,?,?)";
		return DbHelp.insert(sql, new ScalarHandler<Long>(),movie.getMovieName(), movie.getDirectorName(),movie.getArea(),movie.getYear(),movie.getImageName(),movie.getContent(),movie.getSimpleContent(), movie.getScanNum(),movie.getReplyNum()).intValue();
	}

	public List<Movie> findAll(Map<String,String> params) {
		String sql = "select * from t_movie ";
		
		List<Object> listParams = new ArrayList<>();
		if(StringUtils.isNotEmpty(params.get("keys"))) {
			sql += "where movie_name like ? ";
			String keys = params.get("keys");
			keys = "%" + keys + "%";
			listParams.add(keys);
		}
		
		sql += "limit ?,?";
		listParams.add(Integer.parseInt(params.get("start")));
		listParams.add(Integer.parseInt(params.get("pageSize")));
		
		return DbHelp.query(sql, new BeanListHandler<>(Movie.class, new BasicRowProcessor(new GenerousBeanProcessor())),listParams.toArray());
	}

	public Movie findMovieById(int id) {
		String sql = "select * from t_movie where id = ? ";
		return DbHelp.query(sql, new BeanHandler<>(Movie.class, new BasicRowProcessor(new GenerousBeanProcessor())),id);
	}

	public Movie findByMovieName(String movieName) {
		String sql = "select * from t_movie where movie_name = ?";
		return DbHelp.query(sql, new BeanHandler<>(Movie.class), movieName);
	}

	public int count(String keys) {
		List<String> params = new ArrayList<>();
		
		String sql = "select count(*) from t_movie ";
		if(StringUtils.isNotEmpty(keys)) {
			keys = "%" + keys + "%";
			params.add(keys);
			sql += "where movie_name like ?";
			
		}
		return DbHelp.query(sql, new ScalarHandler<Long>(),params.toArray()).intValue();
	}

	public void del(int movieId) {
		String sql = "delete from t_movie where id = ?";
		DbHelp.update(sql, movieId);
	}

	public void edit(Movie movie) {
		String sql = "update t_movie set movie_name = ?, director_name = ?, area = ?, year = ?, image_name = ?, content = ?, simplecontent = ?, scan_num = ?, reply_num = ?, remark = ? where id = ?";
		DbHelp.update(sql, movie.getMovieName(), movie.getDirectorName(), movie.getArea(), movie.getYear(), movie.getImageName(), movie.getContent(), movie.getSimpleContent(),movie.getScanNum(), movie.getReplyNum(), movie.getRemark(), movie.getId());
	}

}
