package com.kaishengit.dao;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kaishengit.entity.Admin;
import com.kaishengit.util.DbHelp;

public class AdminDao {
	
	Logger logger = LoggerFactory.getLogger(AdminDao.class);


	public Admin findByName(String adminName) {
		String sql = "select * from t_admin where admin_name = ?";
		return DbHelp.query(sql, new BeanHandler<Admin>(Admin.class, new BasicRowProcessor(new GenerousBeanProcessor())), adminName);
	}

}
