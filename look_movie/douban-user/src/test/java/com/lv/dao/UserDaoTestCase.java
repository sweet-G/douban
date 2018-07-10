package com.lv.dao;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDaoTestCase {

	Logger logger = LoggerFactory.getLogger(UserDaoTestCase.class);
	
	@Test
	public void testLogger() {
		logger.trace("trace");
		logger.debug("debug");
		logger.info("info");
		logger.error("error");
		logger.warn("warn");
	}
	
	
}
