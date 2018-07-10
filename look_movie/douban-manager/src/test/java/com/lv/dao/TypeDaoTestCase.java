package com.lv.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.lv.entity.Type;


public class TypeDaoTestCase {

	TypeDao typeDao;
	
	@Before
	public void before() {
		typeDao = new TypeDao();
	}
	
	
	@Test
	public void testQueryAll() {
		List<Type> typeList = typeDao.queryAll();
		Assert.assertEquals(typeList.size(), 14);
	}
	
	@Test
	public void testQueryTypeByTypeId() {
		Type type = typeDao.queryTypeByTypeId(3);
		System.out.println(type);
	}
	
	@After
	public void after() {
		System.out.println("success...");
	}
	
	
}
