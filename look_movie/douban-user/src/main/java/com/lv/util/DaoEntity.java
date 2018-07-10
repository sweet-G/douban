package com.lv.util;

import java.util.List;

public class DaoEntity {

	private String sql;
	private List<Object> obj;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<Object> getObj() {
		return obj;
	}

	public void setObj(List<Object> obj) {
		this.obj = obj;
	}

}
