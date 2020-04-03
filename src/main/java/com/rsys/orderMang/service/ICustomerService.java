package com.rsys.orderMang.service;

import java.util.List;

import com.rsys.orderMang.dto.CustomerDto;
import com.rsys.orderMang.entity.Customer;


public interface ICustomerService {
	
	List<CustomerDto> getAllCustomer();
	Customer addNewCustomer(Customer cust);

	String updateCustomerDetails(int orderId);
	
	String deleteAllCustomers();
	
	
	
	

}
