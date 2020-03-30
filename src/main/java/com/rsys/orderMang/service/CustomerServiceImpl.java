package com.rsys.orderMang.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsys.orderMang.dao.CustomerDao;
import com.rsys.orderMang.dto.CustomerDto;
import com.rsys.orderMang.entity.Customer;
import com.rsys.orderMang.entity.Orders;
import com.rsys.orderMang.repo.CustomerRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {
	
	@Autowired
	private CustomerRepository custRepo;

	@Override
	public List<CustomerDao> getAllCustomer() {
		List<Customer> cutomerDetails= custRepo.findAll();
		List<Integer> orders = new ArrayList<>();
		List<CustomerDto> cust=new ArrayList<>();
		List<CustomerDao> custmerDaoList=new ArrayList<>();
		
		CustomerDao customerDao=new CustomerDao();
		CustomerDto customerDto=new CustomerDto();
		for(Customer c:cutomerDetails)
		{
			System.out.println(c.getCustomerId());
		orders=custRepo.orderIds(c.getCustomerId());
		customerDto.setOrdersId(orders);
		customerDao.setCustId(c.getCustomerId());
		customerDao.setCustName(c.getCustomerName());
		cust.add(customerDto);
		customerDao.setOrders(cust);
			
		}
		custmerDaoList.add(customerDao);
		
		
		return custmerDaoList;
	}

	@Override
	public String addNewCustomer(Customer cust) {
		String output="";
		Customer customer=new Customer();
		customer.setCustomerName(cust.getCustomerName());
		custRepo.save(cust);
		output="Customer Added";
		return output;
	}

	@Override
	public String updateCustomerDetails(Customer cust) {
		String output="";
		Customer customer=new Customer();
		customer.setCustomerName(cust.getCustomerName());
		custRepo.save(cust);
		output="Customer Updated";
		return output;
	}

}
