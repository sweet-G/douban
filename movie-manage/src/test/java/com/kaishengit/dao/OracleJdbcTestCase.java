package com.kaishengit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

public class OracleJdbcTestCase {

	@Test
	public void testJdbc() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:ORCL", "SCOTT", "apple");
		String sql = "select * from emp where empno = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1,7788);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			System.out.println(rs.getString("ename"));
		}
		
		rs.close();
		ps.close();
		conn.close();
	}
}
