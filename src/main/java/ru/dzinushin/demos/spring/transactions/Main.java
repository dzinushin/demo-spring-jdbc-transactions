package ru.dzinushin.demos.spring.transactions;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.dzinushin.demos.spring.transactions.service.AccountService;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String config = "appContext.xml";
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
		
		AccountService accountService = (AccountService) ctx.getBean("accountService");
		
		double transferSum = 10; // сумма для перевода между счетами
		accountService.transferMoney3(1, 2, transferSum);
	}

}
