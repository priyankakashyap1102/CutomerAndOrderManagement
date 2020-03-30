package com.rsys.orderMang.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsys.orderMang.entity.Customer;
import com.rsys.orderMang.entity.Orders;
import com.rsys.orderMang.repo.CustomerRepository;
import com.rsys.orderMang.repo.OrderRepository;


@Service
public class OrdersServiceImpl implements IOrderService {
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	CustomerRepository customerRepo;

	@Override
	public List<Orders> getAllOrders() {
		return orderRepo.findAll();
	}

	@Override
	public String addOrders(int customerId,Orders order) {
		String output="";
		Orders orders=new Orders();
		Set<Orders> order1=new HashSet<>();
		Customer customer=new Customer();
		orders.setStatus("false");
		
		 Optional<Customer> byId = customerRepo.findById(customerId);
		 if (!byId.isPresent()) {
	            throw new NullPointerException();
	        }
		 
		 Customer cust=byId.get();
		 orders.setCustomer(cust);
		 orders.setProducts(order.getProducts());
			Orders o=orderRepo.save(orders);
			order1.add(o);
			customer.setOredrs(order1);
			output="Order details Added";
	/*	//Customer cus=order.getCustomer();
		Customer customer=orderRepo.findByCustomer();
		if(customer==null)
		{
			output="Customer is not present";
		}
		else
		{
		orders.setCustomer(customer);
		orders.setProducts(order.getProducts());
		orderRepo.save(order);
		output="Order details Added";
		}*/
		
		return output;
	}

	@Override
	public String updateOrders(Orders product) {
		return null;
	}

	@Override
	public String deleteAllOrders() {
		return null;
	}

	@Override
	public String deleteOneOrder(int proId) {
		return null;
	}

}
