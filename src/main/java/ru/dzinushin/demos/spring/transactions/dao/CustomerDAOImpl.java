package ru.dzinushin.demos.spring.transactions.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import ru.dzinushin.demos.spring.transactions.domain.Customer;

public class CustomerDAOImpl extends JdbcDaoSupport implements CustomerDAO {
	static final String INSERT_CUSTOMER_SQL="insert into customer(id,name) values(?,?)";
	
	@Override
	public void add(Customer customer) {
		getJdbcTemplate().update(INSERT_CUSTOMER_SQL,customer.getId(),customer.getName());
	}

}
