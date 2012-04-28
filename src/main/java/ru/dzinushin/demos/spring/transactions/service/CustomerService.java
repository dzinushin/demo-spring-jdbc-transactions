package ru.dzinushin.demos.spring.transactions.service;

import org.springframework.transaction.annotation.Transactional;

import ru.dzinushin.demos.spring.transactions.domain.Customer;

@Transactional
public interface CustomerService {
	void add(Customer customer);
}
