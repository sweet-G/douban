package com.kaishengit.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.kaishengit.entity.Reply;
import com.kaishengit.util.DbHelp;

public class ReplyDao {

	public int countByState(Integer state) {
		String sql = "select count(*) from t_reply where state = ?";
		return DbHelp.query(sql, new ScalarHandler<Long>(), state).intValue();
	}

	public List<Reply> findByState(Map<String,String> params) {
		String sql = "select r.*,m.movie_name,u.username from t_reply r, t_movie m, t_user u where r.movie_id = m.id and r.user_id = u.id and state = ? ";
		List<Object> listParams = new ArrayList<>();
		
		sql += "limit ?,?";
		listParams.add(Integer.parseInt(params.get("state")));
		listParams.add(Integer.parseInt(params.get("start")));
		listParams.add(Integer.parseInt(params.get("pageSize")));
		
		return DbHelp.query(sql, new BeanListHandler<>(Reply.class, new BasicRowProcessor(new GenerousBeanProcessor())), listParams.toArray());
	}

	public void review(int replyId, int state) {
		String sql = "update t_reply set state = ? where id = ?";
		DbHelp.update(sql, state,replyId);
	}


}
