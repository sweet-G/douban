package com.lv.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.lv.dao.TypeDao;
import com.lv.entity.Movie;
import com.lv.entity.Type;
import com.lv.exception.ServiceException;
import com.lv.util.Page;

public class TypeService {
	TypeDao typeDao = new TypeDao();
	
	public List<Type> queryList() {
		List<Type> types = typeDao.queryAll();
		return types;
	}
	
	public Object saveType(String typeId, String typeName, String createTime) throws ServiceException {
		
		if(StringUtils.isEmpty(typeName)) {
			throw new ServiceException("类型不能为空!");
		}
		if(typeName.length() > 6) {
			throw new ServiceException("请精简对该类型的描述!");
		}
		Type type = typeDao.findByName(typeName);
		if(StringUtils.isNotEmpty(typeId)) {
			if(type != null && type.getId() != Integer.valueOf(typeId)) {
				throw new ServiceException("这个类型已经被其他小伙伴存在库中啦!");
			}
			Type types = typeDao.queryTypeByTypeId(Integer.valueOf(typeId));
			types.setText(typeName);
			typeDao.update(types);
		} else if(StringUtils.isEmpty(typeId) && type == null) {
			type = new Type();
			type.setText(typeName);
			type.setCreateTime(createTime);
			typeDao.insertType(type);
		}else {
			throw new ServiceException("这个类型已经被其他小伙伴存在库中啦!");
		}
		return null;
	}

	public Type deleteTypeByTypeId(String id) throws ServiceException {
		
		// 1.验证传入的id是不是空字符串
		if(StringUtils.isEmpty(id)) {
			throw new ServiceException("参数异常!");
		}
		// 2.验证传入的id是不是纯数字
		if(!StringUtils.isNumeric(id)) {
			throw new ServiceException("参数异常!");
		}
		// 3.根据传入的类型的id，查找电影————>3.1
		List<Movie> movieList = typeDao.findMovieByTypeId(Integer.valueOf(id));
		// 3.1判断是否能根据传入的类型id，找到电影，如果找到↓,不能删; 找不到————>3.2
		if(movieList.size() != 0) {
			throw new ServiceException("该类型下有电影,不能进行删除操作!");
		}
		Type type = typeDao.queryTypeByTypeId(Integer.valueOf(id));
		// 3.2根据id删除类型
		typeDao.deleteTypeByTypeId(Integer.valueOf(id));
		return type;
	}

	public Page<Type> queryTypeList(String p, String keys) {
		p = StringUtils.isEmpty(p) ? "1" : p;
		p = StringUtils.isNumeric(p) ? p : "1";
		int count = typeDao.count(keys);
		
		Page<Type> page = new Page<>(Integer.valueOf(p), count);
		
		Map<String, String> params = new HashMap<>();
		params.put("start", page.getStart().toString());
		params.put("pageSize", page.getPageSize().toString());
		params.put("keys", keys);
		
		List<Type> typeList = typeDao.queryMovieByParams(params);
		
		page.setTList(typeList);
		return page;
	}

	
	
	
}
