package com.jcpdev.board.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.jcpdev.board.dao.CustomerMapper;
import com.jcpdev.board.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	private final CustomerMapper dao;
	
	public CustomerServiceImpl(CustomerMapper dao) {   //생성자 자동주입 @Autowired생략
		this.dao =dao;
	}
	
	
	@Override
	public Customer login(Customer user) {
		return dao.login(user);
	}
	
	
	@Override
	public Customer selectOne(int idx) {
		// TODO Auto-generated method stub
		return dao.selectOne(idx);
	}

	@Override
	public int insert(Customer customer) {
		if(customer.getAddr().equals("기타"))
			customer.setAddr(customer.getAddr_etc());
		
		return dao.insert(customer);
	}

	@Override
	public int update(Customer customer) {
		if(customer.getAddr().equals("기타"))
			customer.setAddr(customer.getAddr_etc());
		
		return dao.update(customer);
	}

	@Override
	public int delete(int idx) {
		// TODO Auto-generated method stub
		return dao.delete(idx);
	}

	@Override
	public List<Customer> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
}