package com.lv.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import com.lv.entity.Movie;
import com.lv.entity.Type;
import com.lv.util.DbHelp;

public class TypeDao {

	public List<Type> queryAll() {
		String sql = "select id, type_name as text, create_time, remark from t_type";
		return DbHelp.query(sql,
				new BeanListHandler<Type>(Type.class, new BasicRowProcessor(new GenerousBeanProcessor())));
	}

	public void insertType(Type type) {
		String sql = "insert into t_type (type_name, create_time, remark) values (?,?,?)";
		DbHelp.update(sql, type.getText(), type.getCreateTime(), type.getRemark());
	}

	public Type findByName(String typeName) {
		String sql = "select * from t_type where type_name = ?";
		return DbHelp.query(sql, new BeanHandler<Type>(Type.class, new BasicRowProcessor(new GenerousBeanProcessor())),
				typeName);
	}

	public List<Type> queryByMovieId(Integer id) {
		String sql = "select t.id, t.type_name as text from t_type t, t_movie_type mt where t.id = mt.type_id and mt.movie_id = ?";
		return DbHelp.query(sql,
				new BeanListHandler<Type>(Type.class, new BasicRowProcessor(new GenerousBeanProcessor())), id);
	}

	public List<Movie> findMovieByTypeId(Integer id) {
		String sql = "SELECT * FROM t_movie m, t_movie_type mt where m.id = mt.movie_id and mt.type_id = ?";
		return DbHelp.query(sql,
				new BeanListHandler<Movie>(Movie.class, new BasicRowProcessor(new GenerousBeanProcessor())), id);
	}

	public void deleteTypeByTypeId(Integer id) {
		String sql = "delete from t_type where id = ?";
		DbHelp.update(sql, id);
	}

	public void update(Type type) {
		String sql = "update t_type set type_name=?, remark=? where id=?";
		DbHelp.update(sql, type.getText(), type.getRemark(), type.getId());
	}

	public int count(String keys) {
		String sql = "select count(*) from t_type ";
		List<Object> paramsList = new ArrayList<>();
		if(StringUtils.isNoneEmpty(keys)) {
			keys = "%" + keys + "%";
			sql += "where type_name like ?";
			paramsList.add(keys);
		}
		return DbHelp.query(sql, new ScalarHandler<Long>(), paramsList.toArray()).intValue();
	}

	public List<Type> queryMovieByParams(Map<String, String> params) {
		String sql = "select id, type_name as text, create_time, remark from t_type ";
		List<Object> paramsList = new ArrayList<>();
		if(StringUtils.isNotEmpty(params.get("keys"))) {
			sql += "where type_name like ? ";
			String keys = params.get("keys");
			keys  = "%" + keys + "%";
			paramsList.add(keys);
		}
		sql += "limit ?, ?";
		paramsList.add(Integer.parseInt(params.get("start")));
		paramsList.add(Integer.parseInt(params.get("pageSize")));
		return DbHelp.query(sql, new BeanListHandler<Type>(Type.class, new BasicRowProcessor(new GenerousBeanProcessor())), paramsList.toArray());
	}

	public Type queryTypeByTypeId(Integer id) {
		String sql = "select id, type_name as text, create_time, remark from t_type where id=?";
		return DbHelp.query(sql, new BeanHandler<>(Type.class, new BasicRowProcessor(new GenerousBeanProcessor())), id);
	}

	
	
	
	
	
	
	
	
	
	
	
}
