package com.kaishengit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.dao.MovieDao;
import com.kaishengit.dao.MovieTypeDao;
import com.kaishengit.dao.TypeDao;
import com.kaishengit.entity.Movie;
import com.kaishengit.entity.MovieType;
import com.kaishengit.entity.Type;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Page;

public class TypeService {

	TypeDao typeDao = new TypeDao();
	MovieDao movieDao = new MovieDao();
	MovieTypeDao movieTypeDao = new MovieTypeDao();
	
	Movie movie = new Movie();
	Type type = new Type();
	

	public Page<Type> findTypeListByPage(int pageNo, String keys) {
		int count = typeDao.count(keys);
		Page<Type> page = new Page<>(pageNo, count);
		
		Map<String,String> params = new HashMap<>();
		params.put("keys", keys);
		params.put("start", page.getStart().toString());
		params.put("pageSize", page.getPageSize().toString());
		
		List<Type> typeList = typeDao.findType(params);
		
		page.setItems(typeList);
		
		return page;
	}

	public Type delType(String id) {
		
		if(StringUtils.isNumeric(id)) {
			throw new ServiceException("参数异常");
		}
		
		if(StringUtils.isEmpty(id)) {
			throw new ServiceException("参数异常");
		}
		
		List<MovieType> movieTypeList = typeDao.findTypeById(Integer.parseInt(id));
		
		if(movieTypeList.size() != 0) {
			throw new ServiceException("已有电影拥有该类型，不能删除");
		}
		
		typeDao.del(Integer.parseInt(id));
		return type;
	}

	public void addType(String addTypeName) {

		type.setText(addTypeName);
		
		typeDao.save(type);
	}

	public boolean findTypeName(String id, String addTypeName) {

		Type type = typeDao.findTypeName(addTypeName);
		
		if(type == null) {
			return true;
		}
		
		if(StringUtils.isNotEmpty(id)) {
			return false;
		}
		
		if(type.getId() == Integer.parseInt(id)) {
			return true;
		} else {
			return false;
		}
		
	}

	public Type findTypeById(String id) {
		if(StringUtils.isNumeric(id)) {
			Type type = typeDao.findById(Integer.parseInt(id));
			return type;
		} else {
			throw new ServiceException("参数异常");
		}
	}

	public void edit(String id, String text) {
		Type type = typeDao.findTypeName(text);
		
		if(StringUtils.isNotEmpty(id)) {
			if(StringUtils.isNumeric(id)) {
				if(type != null && type.getId() == Integer.parseInt(id)) {
					throw new ServiceException("类型重复");
				}
			}
		}
		type = typeDao.findById(Integer.parseInt(id));
		
		type.setText(text);
		typeDao.editType(type);
		
	}

	

}
