package ru.dzinushin.demos.spring.transactions.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class AccountDAO extends JdbcDaoSupport {
	final static String GET_SUM_SQL = "select sum from account where id=?";
	final static String GET_SUM_FOR_UPDATE_SQL = "select sum from account where id=? for update";
	final static String UPDATE_SUM_SQL = "update account set sum=?, modified=now() where id=?";
	
	public double getSum(int accountId) {		
		Double sum = getJdbcTemplate().queryForObject(GET_SUM_SQL, Double.class, accountId);
		return sum;
	}
	
	public void updateSum(int accountId, double newSum) {
		getJdbcTemplate().update(UPDATE_SUM_SQL,newSum,accountId);		
	}
	
	public void transferMoney(int fromAccountId, int toAccountId, double transferSum) {
		Double sum1 = getJdbcTemplate().queryForObject(GET_SUM_FOR_UPDATE_SQL, Double.class, fromAccountId);
		if(sum1 - transferSum <0)
			throw new RuntimeException("can't transfer sum -- not ehough money on account");
		Double sum2 = getJdbcTemplate().queryForObject(GET_SUM_FOR_UPDATE_SQL, Double.class, toAccountId);
		updateSum( fromAccountId,sum1 - transferSum);
		updateSum( toAccountId,  sum2 + transferSum);
	}
}
