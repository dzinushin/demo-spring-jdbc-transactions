package ru.dzinushin.demos.spring.transactions;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.dzinushin.demos.spring.transactions.service.StartService;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String config = "appContext.xml";
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext(config);

		StartService srv = (StartService) ctx.getBean("StartService");
		
		//srv.testApacheDBCP();
		srv.insertData();
		srv.insertData2();
	}

}
