package com.lv.util;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbHelp {

	private static QueryRunner runner = new QueryRunner(ConnectionManager.getConnection());
	private static Logger logger = LoggerFactory.getLogger(DbHelp.class);
	
	
	public static void update(String sql, Object... params) {
		try {
			runner.update(sql, params);
			logger.debug("执行SQL : {}", sql);
		} catch (SQLException e) {
			logger.debug("执行SQL : {} 异常", sql);
		}
	}
	
	public static <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) {
		T t = null;
		try {
			t = runner.query(sql, rsh, params);
			logger.debug("执行SQL : {}", sql);
		} catch (SQLException e) {
			logger.debug("执行SQL : {} 异常", sql);
		}
		return t;
	}
	
	public static <T> T insert(String sql, ResultSetHandler<T> rsh, Object... params) {
		T t = null;
		try {
			t = runner.insert(sql, rsh, params);
			logger.debug("执行SQL : {}", sql);
		} catch (SQLException e) {
			logger.debug("执行SQL : {} 异常", sql);
		}
		return t;
	}
	
}
