package com.kaishengit.service;

import org.apache.commons.codec.digest.DigestUtils;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.Cofig;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;

public class UserService {

	UserDao userDao = new UserDao();
	User user = new User();
	
	public User login(String userName, String password) {
		
		User user = userDao.findByName(userName);
		//加密
		password = DigestUtils.md5Hex(password + Cofig.ADMIN_PASSWORD_SALT);
		
		if(user != null && password.equals(user.getPassword())) {
			return user;
		} else {
			throw new ServiceException("用户名或者密码错误");
		}
	}

	public void addRegsiter(String userName, String password, String newPassword, String tel,String eamil, String kaptche, String kaptchaExpected) {
		user.setUserName(userName);
		user.setPassword(DigestUtils.md5Hex(password + Cofig.ADMIN_PASSWORD_SALT));
		user.setNewPassword(DigestUtils.md5Hex(newPassword + Cofig.ADMIN_PASSWORD_SALT));
		user.setTel(tel);
		user.setEamil(eamil);
		user.setKaptche(kaptche);
		user.setKaptchaExpected(kaptchaExpected);
		
		
		if(user.getPassword().equals(user.getNewPassword())) {
			if(user.getTel() != null && user.getTel().length() <= 3) {
				if(user.getEamil().matches("\\w+@\\w+(\\.\\w+){1,2}")) {
					if(user.getKaptche().equals(user.getKaptchaExpected())) {
						userDao.save(user);
					}else {
						throw new ServiceException("验证码不正确，请重新输入");
					}
				} else {
					throw new ServiceException("邮箱格式不正确，请重新输入");
				}
			}else {
				throw new ServiceException("电话格式为三位，请重新输入");
			}
		} else {
			throw new ServiceException("两次密码不一致，请重新输入");
		}
	}

	public boolean findUserName(String userName) {
		User user = userDao.findByName(userName);
		
		if(user == null) {
			return true;
		}
		
		return false;
	}

}
