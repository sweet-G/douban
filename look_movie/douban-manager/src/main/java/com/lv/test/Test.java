package com.lv.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String i = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		System.out.println(i);
		

//		String password = "123123" + Config.ADMIN_PASSWORD_SALT;
//		
//		password = DigestUtils.md2Hex(password);
//		System.out.println(password);
		// 3c9a6fa3827fe9c377d9ce6be0a18df4
		// d488822ea4d623a79e62cd0fe6894ac1
		
		
	}

}
