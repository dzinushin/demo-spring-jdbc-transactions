package ru.dzinushin.demos.spring.transactions.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class Table1DAO {
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void insertRow(int val) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "insert into table1(val) values("+val+")";
		jdbcTemplate.update(sql);
	}
	
	public void testApacheDBCP()
	{
		System.out.println("dataSource="+ dataSource);
		
		BasicDataSource ds = (BasicDataSource) dataSource;
		
		boolean defRO = ds.getDefaultReadOnly();
		boolean autoCommit = ds.getDefaultAutoCommit();
		int maxActive = ds.getMaxActive();
		int maxIdle = ds.getMaxIdle();
		
		System.out.println("defRO="+defRO+" autoCommit=" + autoCommit);
		System.out.println("maxActive="+maxActive+ " maxIdle="+maxIdle);
		
		int numActive = ds.getNumActive();
		int numIdle = ds.getNumIdle();
		System.out.println("numActive="+numActive+" numIdle="+numIdle);
		
		Connection conn = null;
		try {
			conn = ds.getConnection();
			
			numActive = ds.getNumActive();
			numIdle = ds.getNumIdle();
			System.out.println("numActive="+numActive+" numIdle="+numIdle);
			
			Statement st = conn.createStatement();
			st.executeUpdate("insert into table1(val) values(11)");
			conn.commit();
			st.close();
			
			conn.close();

			numActive = ds.getNumActive();
			numIdle = ds.getNumIdle();
			System.out.println("numActive="+numActive+" numIdle="+numIdle);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
