package com.lv.dao;

import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.lv.entity.Type;
import com.lv.util.DbHelp;

public class TypeDao {

	public List<Type> queryTypeByMovieId(Integer id) {
		String sql = "select t.id, t.type_name as text, t.create_time, t.remark from t_type t, t_movie_type mt where t.id = mt.type_id and movie_id = ?";
		return DbHelp.query(sql,
				new BeanListHandler<Type>(Type.class, 
						new BasicRowProcessor(new GenerousBeanProcessor())), id);
	}

	public List<Type> queryTypeListByGroup() {
		String sql = "SELECT t.id, t.type_name as text, count(mt.type_id) AS groups "
				+ "FROM t_type t, t_movie_type mt WHERE mt.type_id = t.id "
						+ "GROUP BY type_id ORDER BY groups DESC LIMIT 0, 6 ";
		return DbHelp.query(sql,
				new BeanListHandler<Type>(Type.class, 
						new BasicRowProcessor(new GenerousBeanProcessor())));
	}

}
