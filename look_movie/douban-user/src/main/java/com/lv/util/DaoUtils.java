package com.lv.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class DaoUtils {

	public static DaoEntity sqlUtil(String endSql, Map<String, String> params, String str) {
		List<Object> paramsList = new ArrayList<>();
		String keys = params.get("keys");
		String typeName = params.get("typeName");
		String start = params.get("start");
		String pageSize = params.get("pageSize");
		
		if(StringUtils.isNotEmpty(keys)) {
			keys = "%" + keys + "%";
			paramsList.add(keys);
		} else if(StringUtils.isNotEmpty(typeName)) {
			paramsList.add(typeName);
			if(str == null) {
				endSql = " , t_type t, t_movie_type mt where m.id = mt.movie_id and mt.type_id = t.id and t.type_name=?";
			} else {
				// TODO 根据类型名字查一共有多少条电影
				endSql = " , t_movie_type mt, t_type t where m.id = mt.movie_id and t.id = mt.type_id and t.type_name=?";
			}
		} else {
			endSql = "";
		}
		if(StringUtils.isNotEmpty(start)) {
			paramsList.add(Integer.valueOf(start));
		}
		if(StringUtils.isNotEmpty(pageSize)) {
			paramsList.add(Integer.valueOf(pageSize));
		}
		
		DaoEntity daoEntity = new DaoEntity();
		daoEntity.setSql(endSql);
		daoEntity.setObj(paramsList);
		return daoEntity;
	}

}
