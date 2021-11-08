package com.jcpdev.board.dao;

import java.util.List;

import com.jcpdev.board.model.Customer;

public interface CustomerMapper {

	List<Customer> selectAll();
	Customer selectOne(int idx);
	int insert(Customer customer);
	int update(Customer customer);
	int delete(int idx);
	Customer login(Customer customer);
}
