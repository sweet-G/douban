package com.kaishengit.util;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kaishengit.exception.DataAccessException;

public class DbHelp {
	
	private static QueryRunner runner = new QueryRunner(ConnectionManager.getDataSource());
	private static Logger logger = LoggerFactory.getLogger(DbHelp.class);
	
	public static <T> T insert(String sql, ResultSetHandler<T> rsh, Object... params) throws DataAccessException{
		T t = null;
		try {
			t = runner.insert(sql, rsh, params);
			logger.debug("SQL:{}" , sql);
		} catch (SQLException e) {
			logger.error("执行{}异常", sql);
			throw new DataAccessException("执行" + sql + "异常"); 
		}
		return t;
	}
	
	
	public static void update(String sql, Object... params) throws DataAccessException{
		
		try {
			runner.update(sql, params);
			logger.debug("SQL:{}" , sql);
		} catch (SQLException e) {
			logger.error("执行{}异常", sql);
			throw new DataAccessException("执行" + sql + "异常"); 
		}
		
	}
	
	public static <T> T query(String sql, ResultSetHandler<T> rsh, Object... params)  throws DataAccessException{
		T t = null;
		try {
			t = runner.query(sql, rsh, params);
			logger.debug("SQL:{}" , sql);
		} catch (SQLException e) {
			logger.error("执行{}异常", e.getMessage());
			throw new DataAccessException("执行" + sql + "异常"); 
		}
		return t;
		
	}
	
	
}
