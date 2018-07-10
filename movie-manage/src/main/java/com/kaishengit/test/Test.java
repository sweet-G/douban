
package com.kaishengit.test;

import org.apache.commons.codec.digest.DigestUtils;

public class Test {

	public static void main(String[] args) {
		
		String password = "000000";
		String salt = "$%^&$$*!&*)(&(54njh";
		String code = DigestUtils.md5Hex(password + salt);
		
		System.out.println(code);
	}
	
}
