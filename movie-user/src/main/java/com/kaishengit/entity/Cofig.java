package com.kaishengit.entity;

import java.io.IOException;
import java.util.Properties;

public class Cofig {

	public static final String ADMIN_PASSWORD_SALT = "$%^&$$*!&*)(&(54njh";

	public static final Integer DEFULT_SCAN_NUM = 0;
	public static final Integer DEFULT_REPLY_NUM = 0;
	
	public static final Integer REPLY_STATE_UNVIEW = 0; //未审核
	public static final Integer REPLY_STATE_SUCCESS = 1; //审核通过
	public static final Integer REPLY_STATE_UPASS = 2; //审核不通过


	private static Properties prop = new Properties();
	
	static {
		try {
			prop.load(Cofig.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String get(String key) {
		return prop.getProperty(key);
	}
}
