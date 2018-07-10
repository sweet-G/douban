package com.kaishengit.service;

import org.apache.commons.codec.digest.DigestUtils;

import com.kaishengit.dao.AdminDao;
import com.kaishengit.entity.Admin;
import com.kaishengit.entity.Cofig;
import com.kaishengit.exception.ServiceException;

public class AdminService {

AdminDao adminDao = new AdminDao();
	
	public Admin login(String adminName, String password) {
		
		Admin admin = adminDao.findByName(adminName);
		//加密
		password = DigestUtils.md5Hex(password + Cofig.ADMIN_PASSWORD_SALT);
		
		if(admin != null && password.equals(admin.getPassword())) {
			return admin;
		} else {
			throw new ServiceException("用户名或者密码错误");
		}
	}

}
