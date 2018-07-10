package com.lv.service;

import org.apache.commons.codec.digest.DigestUtils;

import com.lv.dao.AdminDao;
import com.lv.entity.Admin;
import com.lv.exception.ServiceException;
import com.lv.util.Config;

public class AdminService {
	AdminDao adminDao = new AdminDao();

	public Admin login(String adminName, String password) throws ServiceException {
		password = password + Config.ADMIN_PASSWORD_SALT;
		password = DigestUtils.md2Hex(password);
		Admin admin = adminDao.findAdminByName(adminName);
		if(admin != null && password.equals(admin.getPassword())) {
			return admin;
		} else {
			throw new ServiceException("账号或密码错误!");
		}
	}

}
