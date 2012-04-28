package ru.dzinushin.demos.spring.transactions.dao;

public interface AccountDAO {

	void transferMoney(int fromAccountId, int toAccountId, double transferSum);
}
