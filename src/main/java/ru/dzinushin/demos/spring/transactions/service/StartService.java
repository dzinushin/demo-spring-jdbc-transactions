package ru.dzinushin.demos.spring.transactions.service;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import ru.dzinushin.demos.spring.transactions.dao.Table1DAO;

public class StartService {
	
	private Table1DAO table1DAO;

	public Table1DAO getTable1DAO() {
		return table1DAO;
	}

	public void setTable1DAO(Table1DAO table1dao) {
		table1DAO = table1dao;
	}

	private DataSourceTransactionManager txManager;
	
	public DataSourceTransactionManager getTxManager() {
		return txManager;
	}

	public void setTxManager(DataSourceTransactionManager txManager) {
		this.txManager = txManager;
	}
	
	public void insertData()
	{		
		TransactionStatus tranStatus = txManager.getTransaction(new DefaultTransactionDefinition());

		try
		{
			table1DAO.insertRow(777);
			txManager.commit(tranStatus);
		}
		catch(Exception e)
		{
			txManager.rollback(tranStatus);
		}		
	}
	
	
	public void insertData2()
	{		
		TransactionTemplate tranTemplate = new TransactionTemplate(txManager);
		
		tranTemplate.execute( 
				new TransactionCallbackWithoutResult() {					
					@Override
					protected void doInTransactionWithoutResult(TransactionStatus status) {
						table1DAO.insertRow(888);
						//status.setRollbackOnly();
					}
				}
		);		
	 }	
	
}
