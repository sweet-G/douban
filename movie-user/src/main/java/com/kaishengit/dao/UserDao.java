package com.kaishengit.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;

import com.kaishengit.entity.User;
import com.kaishengit.util.DbHelp;

public class UserDao {

	public User findByName(String userName) {
		String sql = "select * from t_user where username = ?";
		return DbHelp.query(sql, new BeanHandler<User>(User.class), userName);
	}

	public void save(User user) {
		String sql = "insert into t_user (username, password, tel,email) values (?,?,?,?)";
		DbHelp.update(sql, user.getUserName(),user.getPassword(),user.getTel(),user.getEamil());
	}

}
