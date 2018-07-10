package com.lv.util;

import java.io.IOException;
import java.util.Properties;

public class Config {
	
	public static final String ADMIN_PASSWORD_SALT = "!%^#()()*!#";
	
	public static final String JSON_STATE_SUCCESS = "success";
	public static final String JSON_STATE_ERROR = "error";

	public static final Integer REPLY_STATE_UNREVIEW = 0; // 未审核
	public static final Integer REPLY_STATE_SUCCESS = 1; // 通过
	public static final Integer REPLY_STATE_UNPASS = 2; // 未通过
	
	
	public static String get(String data) throws IOException {
		Properties prop = new Properties();
		prop.load(Config.class.getClassLoader().getResourceAsStream("Config.properties"));
		return prop.getProperty(data);
	}
	
}
