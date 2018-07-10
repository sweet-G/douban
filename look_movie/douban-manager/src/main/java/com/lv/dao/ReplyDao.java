package com.lv.dao;

import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.lv.entity.Reply;
import com.lv.util.DbHelp;

public class ReplyDao {

	public int count(Integer replyStateUnreview) {
		String sql = "select count(*) from t_reply where state=?";
		return DbHelp.query(sql, new ScalarHandler<Long>(), replyStateUnreview).intValue();
	}

	public List<Reply> queryRepyByState(List<Integer> paramsList) {
		String sql = "select r.*, m.film_name, u.pickname from t_reply r, t_movie m, t_user u where r.movie_id = m.id and r.user_id = u.id and r.state=? limit ?,?";
		return DbHelp.query(sql, new BeanListHandler<>(Reply.class, new BasicRowProcessor(new GenerousBeanProcessor())), paramsList.toArray());
	}

	public Reply queryReplyByReplyId(Integer id) {
		String sql = "select * from t_reply where id=?";
		return DbHelp.query(sql, new BeanHandler<>(Reply.class, new BasicRowProcessor(new GenerousBeanProcessor())), id);
	}

	public void updateReply(Reply reply) {
		String sql = "update t_reply set state=? where id=?";
		DbHelp.update(sql, reply.getState(), reply.getId());
	}


}
