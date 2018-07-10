package com.lv.dao;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.lv.entity.Admin;
import com.lv.util.DbHelp;

public class AdminDao {

	public Admin findAdminByName(String adminName) {
		String sql = "select * from t_admin where admin_name = ?";
		return DbHelp.query(sql, new BeanHandler<Admin>(Admin.class, new BasicRowProcessor(new GenerousBeanProcessor())), adminName);
	}

}
