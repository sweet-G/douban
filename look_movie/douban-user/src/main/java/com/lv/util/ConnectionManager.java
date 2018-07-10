package com.lv.util;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionManager {
	private static BasicDataSource dataSource = new BasicDataSource();
	private static String DRIVER;
	private static String URL;
	private static String USERNAME;
	private static String PASSWORD;
	
	static {
		try {
			DRIVER = Config.get("jdbc.driver");
			URL = Config.get("jdbc.url");
			USERNAME = Config.get("jdbc.username");
			PASSWORD = Config.get("jdbc.password");
			
			dataSource.setInitialSize(2);
			dataSource.setMaxIdle(3);
			dataSource.setMinIdle(1);
			dataSource.setMaxWaitMillis(5000);
			
			dataSource.setUrl(URL);
			dataSource.setDriverClassName(DRIVER);
			dataSource.setUsername(USERNAME);
			dataSource.setPassword(PASSWORD);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static DataSource getConnection() {
		return dataSource;
	}
	
	
}
