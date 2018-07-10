package com.kaishengit.dao;

import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.kaishengit.entity.Reply;
import com.kaishengit.util.DbHelp;

public class ReplyDao {

	public List<Reply> findByMovieIdAndState(int movieId, Integer state) {
		String sql = "select r.*,u.username from t_reply r, t_user u where r.user_id = u.id and r.movie_id = ? and state = ?";
		return DbHelp.query(sql, new BeanListHandler<>(Reply.class, new BasicRowProcessor(new GenerousBeanProcessor())), movieId, state);
	}
	public int add(Reply reply) {
		String sql = "insert into t_reply (content, state, user_id, movie_id) values (?,?,?,?)";
		return DbHelp.insert(sql, new ScalarHandler<Long>(), reply.getContent(),reply.getState(),reply.getUserId(),reply.getMovieId()).intValue();
	}
	public Reply findCreateTime(int id) {
		String sql = "select * from t_reply where id = ?";
		return DbHelp.query(sql, new BeanHandler<>(Reply.class, new BasicRowProcessor(new GenerousBeanProcessor())), id);
	}


	
}
