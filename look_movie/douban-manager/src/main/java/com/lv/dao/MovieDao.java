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
import com.lv.util.DbHelp;

public class MovieDao {

	public int save(Movie movie) {
		String sql = "insert into t_movie (film_name, film_director, area, year, img_path, content, simpo_content, scan_num, reply_num) values (?,?,?,?,?,?,?,?,?)";
		return DbHelp.insert(sql, new ScalarHandler<Long>(), movie.getFilmName(), movie.getFilmDirector(), movie.getArea(), movie.getYear(), movie.getImgPath(), movie.getContent(), movie.getSimpoContent(), movie.getScanNum(), movie.getReplyNum()).intValue();
	}

	public Movie queryMovieByName(String movieName) {
		String sql = "select * from t_movie where film_name = ?";
		return DbHelp.query(sql, new BeanHandler<Movie>(Movie.class, new BasicRowProcessor(new GenerousBeanProcessor())), movieName);
	}

	public List<Movie> queryAllMovie(Integer start, Integer pageSize) {
		String sql = "select * from t_movie limit ?, ?";
		return DbHelp.query(sql, new BeanListHandler<Movie>(Movie.class, new BasicRowProcessor(new GenerousBeanProcessor())), start, pageSize);
	}

	/*public int total() {
		String sql = "select count(*) from t_movie";
		return DbHelp.query(sql, new ScalarHandler<Long>()).intValue();
	}*/

	public void deleteMovie(Integer id) {
		String sql = "delete from t_movie where id = ?";
		DbHelp.update(sql, id);
	}

	public Movie queryMovieById(Integer id) {
		String sql = "select * from t_movie where id = ?";
		return DbHelp.query(sql, new BeanHandler<Movie>(Movie.class, new BasicRowProcessor(new GenerousBeanProcessor())),  id);
	}

	public int count(String keys) {
		String sql = "select count(*) from t_movie ";
		List<Object> paramsList = new ArrayList<>();
		if(StringUtils.isNotEmpty(keys)) {
			keys = "%" + keys + "%";
			sql += "where film_name like ?";
			paramsList.add(keys);
		}
		return DbHelp.query(sql, new ScalarHandler<Long>(), paramsList.toArray()).intValue();
	}

	public List<Movie> queryMovieByParams(Map<String, String> params) {
		String sql = "select * from t_movie ";
		List<Object> paramsList = new ArrayList<>();
		if(StringUtils.isNotEmpty(params.get("keys"))) {
			sql += "where film_name like ? ";
			String keys = params.get("keys");
			keys  = "%" + keys + "%";
			paramsList.add(keys);
		}
		sql += "limit ?, ?";
		paramsList.add(Integer.parseInt(params.get("start")));
		paramsList.add(Integer.parseInt(params.get("pageSize")));
		return DbHelp.query(sql, new BeanListHandler<Movie>(Movie.class, new BasicRowProcessor(new GenerousBeanProcessor())), paramsList.toArray());
	}

	public void update(Movie movie) {
		String sql = "update t_movie set film_name=?, film_director=?, area=?, year=?, img_path=?, content=?, simpo_content=?, create_time=?, scan_num=?, reply_num=?, last_reply_time=?, remark=? where id=?";
		DbHelp.update(sql, movie.getFilmName(), movie.getFilmDirector(), movie.getArea(), movie.getYear(), movie.getImgPath(), movie.getContent(), movie.getSimpoContent(), movie.getCreateTime(), movie.getScanNum(), movie.getReplyNum(), movie.getLastReplyTime(), movie.getRemark(), movie.getId());
	}

}
