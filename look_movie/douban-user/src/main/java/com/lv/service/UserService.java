package com.lv.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.lv.dao.UserDao;
import com.lv.entity.User;
import com.lv.exception.ServiceException;
import com.lv.util.Config;

public class UserService {
	private UserDao userDao = new UserDao();

	public User login(String userName, String password) throws ServiceException {
		password += Config.ADMIN_PASSWORD_SALT;
		password = DigestUtils.md2Hex(password);
		
		User user = userDao.findUserByUserName(userName);
		
		if(user != null && password.equals(user.getPassword())) {
			return user;
		} else {
			throw new ServiceException("账号或密码错误!");
		}
	}

	public boolean checkUserName(String userName, String pickName) {
		User user = null;
		if(StringUtils.isEmpty(userName) && StringUtils.isNotEmpty(pickName)) {
			user = userDao.findUserByPickName(pickName);
		}
		if(StringUtils.isEmpty(pickName) && StringUtils.isNotEmpty(userName)) {
			user = userDao.findUserByUserName(userName);
		}
		
		if(user == null) {
			return true;
		}
		
		return false;
	}

	public User signin(User user) {
		String password = user.getPassword();
		password += Config.ADMIN_PASSWORD_SALT;
		password = DigestUtils.md2Hex(password);
		user.setPassword(password);
		int userId = userDao.save(user);
		
		return userDao.queryUserById(userId);
	}

}
