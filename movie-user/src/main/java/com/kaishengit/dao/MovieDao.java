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

	public List<Movie> findByParams(Map<String, String> params) {
		String sql = "select m.* from t_movie m ";
		
		List<Object> lists = new ArrayList<>();
		if(StringUtils.isNotEmpty(params.get("typeId"))) {
			sql += ", t_type t , t_movie_type mt where mt.movie_id = m.id and mt.type_id = t.id and t.id = ? ";
			lists.add(params.get("typeId"));
		} else if(StringUtils.isNotEmpty(params.get("keys"))) {
			String keys = params.get("keys");
			keys = "%" + keys + "%";
			sql += "where m.movie_name like ? ";
			lists.add(keys);
		}
		
		sql += "limit ?,? ";
		lists.add(Integer.parseInt(params.get("start")));
		lists.add(Integer.parseInt(params.get("pageSize")));
		
		return DbHelp.query(sql, new BeanListHandler<>(Movie.class, new BasicRowProcessor(new GenerousBeanProcessor())), lists.toArray());
	}

	public Movie findMovieById(int id) {
		String sql = "select * from t_movie where id = ? ";
		return DbHelp.query(sql, new BeanHandler<>(Movie.class, new BasicRowProcessor(new GenerousBeanProcessor())),id);
	}

	public Movie findByMovieName(String movieName) {
		String sql = "select * from t_movie where movie_name = ?";
		return DbHelp.query(sql, new BeanHandler<>(Movie.class), movieName);
	}

	public int count(Map<String, String> params) {
		String sql = "select count(*) from t_movie m ";
		
		List<Object> lists = new ArrayList<>();
		
		if(StringUtils.isNotEmpty(params.get("typeId"))) {
			sql += ", t_type t , t_movie_type mt where mt.movie_id = m.id and mt.type_id = t.id and t.id = ?";
			lists.add(params.get("typeId"));
		} else if(StringUtils.isNotEmpty(params.get("keys"))) {
			String keys = params.get("keys");
			keys = "%" + keys + "%";
			sql += "where m.movie_name like ?";
			lists.add(keys);
		}
		// 组数-->List Arrays.asList(arrys)
		// List-->数组  lists.toArray()
		return DbHelp.query(sql, new ScalarHandler<Long>(), lists.toArray()).intValue();
	}


	public List<Movie> findSortMovie() {
		String sql = "select * from t_movie order by scan_num desc limit 0,5";
		return DbHelp.query(sql, new BeanListHandler<>(Movie.class, new BasicRowProcessor(new GenerousBeanProcessor())));
	}

	public void edit(Movie movie) {
		String sql = "update t_movie set movie_name = ?, director_name = ?, area = ?, year = ?, image_name = ?, content = ?, simplecontent = ?, last_time=?,scan_num = ?, reply_num = ?, remark = ? where id = ?";
		DbHelp.update(sql, movie.getMovieName(), movie.getDirectorName(), movie.getArea(), movie.getYear(), movie.getImageName(), movie.getContent(), movie.getSimpleContent(),movie.getLastTime(),movie.getScanNum(), movie.getReplyNum(), movie.getRemark(), movie.getId());
	}

}
