package com.lv.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.lv.entity.User;
import com.lv.util.DbHelp;

public class UserDao {

	public User findUserByUserName(String userName) {
		String sql = "Select * from t_user where username=?";
		return DbHelp.query(sql, new BeanHandler<User>(User.class), userName);
	}

	public User findUserByPickName(String pickName) {
		String sql = "Select * from t_user where pickname=?";
		return DbHelp.query(sql, new BeanHandler<User>(User.class), pickName);
	}

	public int save(User user) {
		String sql = "insert into t_user (username, password, tel, email, pickname, remark) values (?,?,?,?,?,?)";
		return DbHelp.insert(sql, new ScalarHandler<Long>(), user.getUserName(), user.getPassword(), user.getTel(), user.getEmail(), user.getPickName(), user.getRemark()).intValue();
	}

	public User queryUserById(int userId) {
		String sql = "Select * from t_user where id=?";
		return DbHelp.query(sql, new BeanHandler<User>(User.class), userId);
	}

}
