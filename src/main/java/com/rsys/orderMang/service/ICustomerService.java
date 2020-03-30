package com.rsys.orderMang.service;

import java.util.List;

import com.rsys.orderMang.dao.CustomerDao;
import com.rsys.orderMang.entity.Customer;


public interface ICustomerService {
	
	List<CustomerDao> getAllCustomer();
	String addNewCustomer(Customer cust);

	String updateCustomerDetails(Customer cust);
	
	

}
