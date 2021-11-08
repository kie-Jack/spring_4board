package com.jcpdev.board.service;

import java.util.List;

import com.jcpdev.board.model.Customer;

public interface CustomerService {
	Customer login(Customer user);
	Customer selectOne(int idx);
	int insert(Customer customer);
	int update(Customer customer);
	int delete(int idx);
	List<Customer> selectAll();
}
