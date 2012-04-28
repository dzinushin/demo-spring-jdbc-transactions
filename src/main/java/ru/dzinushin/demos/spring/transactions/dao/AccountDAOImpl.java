package ru.dzinushin.demos.spring.transactions.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import ru.dzinushin.demos.spring.transactions.domain.Account;

public class AccountDAOImpl extends JdbcDaoSupport  implements AccountDAO {
	final static String GET_SUM_SQL = "select sum from account where id=?";
	final static String GET_SUM_FOR_UPDATE_SQL = "select sum from account where id=? for update";
	final static String UPDATE_SUM_SQL = "update account set sum=?, modified=now() where id=?";
	
	
	public Account findAccount(int accountId) {
		return null;
	}
	
	public double getSum(int accountId) {		
		Double sum = getJdbcTemplate().queryForObject(GET_SUM_SQL, Double.class, accountId);
		return sum;
	}
	
	public void updateSum(int accountId, double newSum) {
		getJdbcTemplate().update(UPDATE_SUM_SQL,newSum,accountId);		
	}
	
	@Override
	public void transferMoney(int fromAccountId, int toAccountId, double transferSum) {
		Double sum1 = getJdbcTemplate().queryForObject(GET_SUM_FOR_UPDATE_SQL, Double.class, fromAccountId);
		if(sum1 - transferSum <0)
			throw new RuntimeException("can't transfer sum -- not ehough money on account");
		Double sum2 = getJdbcTemplate().queryForObject(GET_SUM_FOR_UPDATE_SQL, Double.class, toAccountId);
		updateSum( fromAccountId,sum1 - transferSum);
		updateSum( toAccountId,  sum2 + transferSum);
	}
	
/*
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
			e.printStackTrace();
		}
		
	}	
 */
}
