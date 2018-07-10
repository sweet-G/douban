package com.lv.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.lv.entity.Movie;
import com.lv.util.DaoEntity;
import com.lv.util.DaoUtils;
import com.lv.util.DbHelp;

public class MovieDao {

	public List<Movie> queryAllMovie() {
		String sql = "select * from t_movie";
		return DbHelp.query(sql, new BeanListHandler<Movie>(Movie.class, new BasicRowProcessor(new GenerousBeanProcessor())));
	}

	public int count(Map<String, String> params, String str) {
		
		String sql = "select count(*) from t_movie m ";
		String endSql = "where film_name like ?";
		
		DaoEntity daoEntity = DaoUtils.sqlUtil(endSql, params, str);
		sql = sql + daoEntity.getSql();
		return DbHelp.query(sql, new ScalarHandler<Long>(), daoEntity.getObj().toArray()).intValue();
	}

	public List<Movie> queryMovieByParams(Map<String, String> params, String str) {
		String sql = "select  m.id, m.film_name, m.film_director, m.area, m.year, m.img_path, m.content, m.simpo_content, m.create_time, m.scan_num, m.reply_num, m.last_reply_time, m.remark from t_movie m ";
		String endSql = " where film_name like ? ";
		DaoEntity daoEntity = DaoUtils.sqlUtil(endSql, params, str);
		String lastSql = " limit ?, ?";
		sql = sql + daoEntity.getSql() + lastSql;
		return DbHelp.query(sql, new BeanListHandler<Movie>(Movie.class, new BasicRowProcessor(new GenerousBeanProcessor())), daoEntity.getObj().toArray());
	}

	public List<Movie> queryMovieByScanNum() {
		String sql = "select * from t_movie order by scan_num desc limit 0,5";
		return DbHelp.query(sql, new BeanListHandler<Movie>(Movie.class, new BasicRowProcessor(new GenerousBeanProcessor())));
	}

	public Movie queryMovieByName(String filmName) {
		String sql = "select * from t_movie where film_name=?";
		return DbHelp.query(sql, new BeanHandler<Movie>(Movie.class, new BasicRowProcessor(new GenerousBeanProcessor())), filmName);
	}

	public Movie queryMovieByMovieId(String movieId) {
		String sql = "select * from t_movie where id=?";
		return DbHelp.query(sql, new BeanHandler<>(Movie.class, new BasicRowProcessor(new GenerousBeanProcessor())), Integer.valueOf(movieId));
	}
	
	
	
	
	
}
