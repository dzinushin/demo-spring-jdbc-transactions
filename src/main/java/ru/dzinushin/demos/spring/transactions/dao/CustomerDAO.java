package ru.dzinushin.demos.spring.transactions.dao;

import ru.dzinushin.demos.spring.transactions.domain.Customer;

public interface CustomerDAO {
	void add(Customer customer);
}
