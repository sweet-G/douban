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
import com.kaishengit.entity.MovieType;
import com.kaishengit.entity.Type;
import com.kaishengit.util.DbHelp;

public class TypeDao {

	public List<Type> findType(Map<String,String> params) {
		String sql = "select id, type_name as text, create_time from t_type ";
		List<Object> lists = new ArrayList<>();
		
		if(StringUtils.isNotEmpty(params.get("keys"))) {
			sql += "where type_name like ? ";
			String keys = params.get("keys");
			keys = "%" + keys + "%";
			lists.add(keys);
			
		}
		
		sql += "limit ?,?";
		lists.add(Integer.parseInt(params.get("start")));
		lists.add(Integer.parseInt(params.get("pageSize")));
		
		return DbHelp.query(sql, new BeanListHandler<>(Type.class, new BasicRowProcessor(new GenerousBeanProcessor())),lists.toArray());
	}

	public List<Type> findByMovieId(Integer id) {
		String sql = "select t.id, t.type_name as text, t.create_time, t.remark from t_type t inner JOIN t_movie_type mt on t.id = mt.type_id where mt.movie_id = ?";
		return DbHelp.query(sql, new BeanListHandler<>(Type.class, new BasicRowProcessor(new GenerousBeanProcessor())), id);
	}

	public List<Type> findMovieType() {
		String sql = "select id, type_name as text, create_time, remark from t_type";
		return DbHelp.query(sql, new BeanListHandler<>(Type.class, new BasicRowProcessor(new GenerousBeanProcessor())));
	}

	public int count(String keys) {
		List<String> lists = new ArrayList<>();
		
		String sql = "select count(*) from t_type ";
		if(StringUtils.isNotEmpty(keys)) {
			keys = "%" + keys + "%";
			lists.add(keys);
			sql += "where type_name like ?";
		}
		return DbHelp.query(sql, new ScalarHandler<Long>(),lists.toArray()).intValue();
	}

	public void del(Integer id) {
		String sql = "delete from t_type where id = ?";
		DbHelp.update(sql, id);
	}

	public List<MovieType> findTypeById(int id) {
		String sql = "select * from t_type t, t_movie_type mt where t.id = mt.type_id and t.id = ?";
		return DbHelp.query(sql, new BeanListHandler<MovieType>(MovieType.class, new BasicRowProcessor(new GenerousBeanProcessor())), id);
	}

	public void save(Type type) {
		String sql = "insert into t_type (type_name) values (?)";
		DbHelp.update(sql, type.getText());
	}

	public Type findTypeName(String addTypeName) {
		String sql = "select * from t_type where type_name = ?";
		return DbHelp.query(sql, new BeanHandler<>(Type.class), addTypeName);
	}

	public Type findById(int id) {
		String sql = "select id, type_name as text from t_type where id = ?";
		return DbHelp.query(sql, new BeanHandler<>(Type.class, new BasicRowProcessor(new GenerousBeanProcessor())), id);
	}

	public void editType(Type type) {
		String sql = "update t_type set type_name = ?, remark = ? where id = ?";
		DbHelp.update(sql,type.getText(), type.getRemark(),type.getId());
	}

	
	
}
