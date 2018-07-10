package com.lv.util;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class ConectionManager {

	private static BasicDataSource dataSource = new BasicDataSource();
	private static String DRIVER;
	private static String URL;
	private static String USERNAME;
	private static String PASSWORD;
	
	static {
		try {
			Properties prop = new Properties();
			prop.load(ConectionManager.class.getClassLoader().getResourceAsStream("Config.properties"));
			DRIVER = prop.getProperty("jdbc.driver");
			URL = prop.getProperty("jdbc.url");
			USERNAME = prop.getProperty("jdbc.username");
			PASSWORD = prop.getProperty("jdbc.password");
		} catch (IOException e) {
			e.printStackTrace();
		}
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		
		dataSource.setInitialSize(2);
		dataSource.setMaxIdle(5);
		dataSource.setMinIdle(1);
		dataSource.setMaxWaitMillis(5000);
	}
	
	public static DataSource getConection() {
		return dataSource;
	}
	
	
}
