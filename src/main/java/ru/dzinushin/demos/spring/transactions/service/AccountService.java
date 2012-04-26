package ru.dzinushin.demos.spring.transactions.service;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import ru.dzinushin.demos.spring.transactions.dao.AccountDAO;

public class AccountService {
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
	
	public void transferMoney1(final int fromAccountId, final int toAccountId, final double transferSum) {		
		TransactionTemplate tranTemplate = new TransactionTemplate(txManager);
		
		Object res = tranTemplate.execute( 
				new TransactionCallbackWithoutResult() {					
					@Override
					protected void doInTransactionWithoutResult(TransactionStatus status) {
//						double newSum = accountDAO.getSum(fromAccountId) - transferSum;
//						if(newSum < 0)
//							throw new RuntimeException();
//						accountDAO.updateSum(fromAccountId, newSum);
//						newSum = accountDAO.getSum(toAccountId) + transferSum;
//						accountDAO.updateSum(toAccountId, newSum);
						accountDAO.transferMoney(fromAccountId, toAccountId, transferSum);
						//status.setRollbackOnly();
					}
				}
		);		
	}
	
	public void transferMoney2(final int fromAccountId, final int toAccountId, final double transferSum) {		
		TransactionStatus tranStatus = txManager.getTransaction(new DefaultTransactionDefinition());
	
		try
		{
//			double newSum = accountDAO.getSum(fromAccountId) - transferSum;
//			if(newSum < 0)
//				throw new RuntimeException();
//			accountDAO.updateSum(fromAccountId, newSum);
//			newSum = accountDAO.getSum(toAccountId) + transferSum;
//			accountDAO.updateSum(toAccountId, newSum);
			
			accountDAO.transferMoney(fromAccountId, toAccountId, transferSum);

			txManager.commit(tranStatus);
		}
		catch(RuntimeException e)
		{
			txManager.rollback(tranStatus);
			throw e;
		}
	}

}
