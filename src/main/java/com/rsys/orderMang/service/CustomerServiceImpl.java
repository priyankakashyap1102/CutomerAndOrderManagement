package com.rsys.orderMang.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsys.orderMang.dto.CustomerDto;
import com.rsys.orderMang.entity.Customer;
import com.rsys.orderMang.entity.Orders;
import com.rsys.orderMang.repo.CustomerRepository;
import com.rsys.orderMang.repo.OrderRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private OrderRepository orderRepo;

	@Override
	public List<CustomerDto> getAllCustomer() {
		List<Customer> cutomerDetails= custRepo.findAll();
		List<CustomerDto> custmerDaoList=new ArrayList<>();
		
		for(Customer c:cutomerDetails)
		{
			CustomerDto customerDao=new CustomerDto();
			customerDao.setCustId(c.getCustomerId());
			customerDao.setCustName(c.getCustomerName());

			List<Integer> orders=custRepo.orderIds(c.getCustomerId());
			
			customerDao.setOrders(orders);
			custmerDaoList.add(customerDao);

		}
		
		return custmerDaoList;
	}

	@Override
	public Customer addNewCustomer(Customer cust) {
		Customer customer=new Customer();
		customer.setCustomerName(cust.getCustomerName());
		custRepo.save(cust);
		return custRepo.save(cust);
	}

	@Override
	public String updateCustomerDetails(int orderId) {
		String output="";
		
		//Customer customers=custRepo.getOne(customerId);
		orderRepo.deleteById(orderId);
		
		
		//custRepo.save(cust);
		output="Customer Updated";
		return output;
	}

	@Override
	public String deleteAllCustomers() {
		custRepo.deleteAll();
		return "Deleted!";
	}

}
