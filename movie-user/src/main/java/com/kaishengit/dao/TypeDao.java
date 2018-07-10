package com.kaishengit.dao;

import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.kaishengit.entity.Type;
import com.kaishengit.util.DbHelp;

public class TypeDao {

	public List<Type> findMovieType() {
		String sql = "select id, type_name as text from t_type";
		return DbHelp.query(sql, new BeanListHandler<>(Type.class));
	}

	public List<Type> findByMovieId(Integer id) {
		String sql = "select t.id, t.type_name as text, t.create_time, t.remark from t_type t inner JOIN t_movie_type mt on t.id = mt.type_id where mt.movie_id = ?";
		return DbHelp.query(sql, new BeanListHandler<>(Type.class, new BasicRowProcessor(new GenerousBeanProcessor())), id);
	}

	
	
}
