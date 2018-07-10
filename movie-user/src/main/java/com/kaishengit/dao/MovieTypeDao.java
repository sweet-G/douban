package com.kaishengit.dao;

import com.kaishengit.entity.MovieType;
import com.kaishengit.util.DbHelp;

public class MovieTypeDao {

	public void save(MovieType movieType) {
		String sql = "insert into t_movie_type (movie_id,type_id) values (?,?)";
		DbHelp.update(sql,movieType.getMovieId(),movieType.getTypeId());
	}

	public void delMovieType(int movieId) {
		String sql = "delete from t_movie_type where movie_id = ?";
		DbHelp.update(sql, movieId);
	}

}
