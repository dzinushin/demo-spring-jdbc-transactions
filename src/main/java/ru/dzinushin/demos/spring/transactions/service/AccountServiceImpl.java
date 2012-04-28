package ru.dzinushin.demos.spring.transactions.service;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import ru.dzinushin.demos.spring.transactions.dao.AccountDAO;
import ru.dzinushin.demos.spring.transactions.domain.Account;

public class AccountServiceImpl implements AccountService {
	private DataSourceTransactionManager txManager;
	private AccountDAO accountDAO;
	
	public DataSourceTransactionManager getTxManager() {
		return txManager;
	}
	public void setTxManager(DataSourceTransactionManager txManager) {
		this.txManager = txManager;
	}
	public AccountDAO getAccountDAO() {
		return accountDAO;
	}
	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
	
	@Override
	public void add(Account account)
	{
		
	}
	
	@Override
	public void transferMoney1(final int fromAccountId, final int toAccountId, final double transferSum) {		
		TransactionTemplate tranTemplate = new TransactionTemplate(txManager);
		
		Object res = tranTemplate.execute( 
				new TransactionCallbackWithoutResult() {					
					@Override
					protected void doInTransactionWithoutResult(TransactionStatus status) {
						accountDAO.transferMoney(fromAccountId, toAccountId, transferSum);
						//status.setRollbackOnly();
					}
				}
		);		
	}
	
	@Override
	public void transferMoney2(final int fromAccountId, final int toAccountId, final double transferSum) {		
		TransactionStatus tranStatus = txManager.getTransaction(new DefaultTransactionDefinition());
	
		try
		{
			accountDAO.transferMoney(fromAccountId, toAccountId, transferSum);
			txManager.commit(tranStatus);
		}
		catch(RuntimeException e)
		{
			txManager.rollback(tranStatus);
			throw e;
		}
	}

	
	@Override
	public void transferMoney3(final int fromAccountId, final int toAccountId, final double transferSum) {
		accountDAO.transferMoney(fromAccountId, toAccountId, transferSum);
	}
}
