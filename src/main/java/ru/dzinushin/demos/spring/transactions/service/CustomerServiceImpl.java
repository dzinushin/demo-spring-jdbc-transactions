package ru.dzinushin.demos.spring.transactions.service;

import org.springframework.transaction.PlatformTransactionManager;

import ru.dzinushin.demos.spring.transactions.dao.CustomerDAO;
import ru.dzinushin.demos.spring.transactions.domain.Customer;

public class CustomerServiceImpl implements CustomerService {

	private PlatformTransactionManager txManager;
	private CustomerDAO customerDAO;
			
	public PlatformTransactionManager getTxManager() {
		return txManager;
	}

	public void setTxManager(PlatformTransactionManager txManager) {
		this.txManager = txManager;
	}

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}


	@Override
	public void add(Customer customer) {
		customerDAO.add(customer);
	}

}
