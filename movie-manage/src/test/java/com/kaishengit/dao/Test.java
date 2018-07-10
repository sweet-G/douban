package com.kaishengit.dao;

import org.apache.commons.codec.digest.DigestUtils;

import com.kaishengit.entity.Cofig;

public class Test {

	public static void main(String[] args) {
		String password = "000";
		String s = "$%^&$$*!&*)(&(54njh";
		String a = DigestUtils.md5Hex(password + s);
		System.out.println(a);
				
	}
}
