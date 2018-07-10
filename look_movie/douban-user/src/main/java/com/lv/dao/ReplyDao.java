package com.lv.dao;

import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.lv.entity.Reply;
import com.lv.util.DbHelp;

public class ReplyDao {

	public void saveReply(Reply reply) {
		String sql = "insert into t_reply (content, state, remark, user_id, movie_id) values (?,?,?,?,?)";
		DbHelp.update(sql, reply.getContent(), reply.getState(), reply.getRemark(), reply.getUserId(), reply.getMovieId());
	}

	public List<Reply> queryReplyByMovieId(Integer id, Integer state) {
		String sql = "select r.*, u.pickname from t_reply r, t_user u where r.user_id = u.id and movie_id=? and r.state=?";
		return DbHelp.query(sql, new BeanListHandler<>(Reply.class, new BasicRowProcessor(new GenerousBeanProcessor())), id, state);
	}

	

}
