package com.lv.util;

import java.io.IOException;
import java.util.Properties;

public class Config {

	public static final String ADMIN_PASSWORD_SALT = "!%^#()()*!#";
	public static final Integer REPLY_STATE_UNREVIEW = 0; // 未审核
	public static final Integer REPLY_STATE_SUCCESS = 1; // 通过
	public static final Integer REPLY_STATE_UNPASS = 2; // 未通过
	
	public static String get(String path) throws IOException {
		Properties prop = new Properties();
		prop.load(Config.class.getClassLoader().getResourceAsStream("Config.properties"));
		String paths = prop.getProperty(path);
		return paths;
	}
	
	
}
