package ru.dzinushin.demos.spring.transactions.service;

import org.springframework.transaction.annotation.Transactional;

import ru.dzinushin.demos.spring.transactions.domain.Account;

public interface AccountService {
	
	void add(Account account);

	void transferMoney1(final int fromAccountId, final int toAccountId, final double transferSum);
	void transferMoney2(final int fromAccountId, final int toAccountId, final double transferSum);
	
	@Transactional
	void transferMoney3(final int fromAccountId, final int toAccountId, final double transferSum);
}
