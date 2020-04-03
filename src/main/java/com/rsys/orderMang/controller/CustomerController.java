package com.rsys.orderMang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rsys.orderMang.dto.CustomerDto;
import com.rsys.orderMang.entity.Customer;
import com.rsys.orderMang.entity.Product;
import com.rsys.orderMang.repo.CustomerRepository;
import com.rsys.orderMang.response.ResponseData;
import com.rsys.orderMang.service.ICustomerService;

@RestController("CustomerController")
@RequestMapping("/poc/customer")
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	String msg="Following Data Found";
	
	@PostMapping
	public ResponseData addCustomer(@RequestBody Customer customer)
	{
		
		Customer customers=customerService.addNewCustomer(customer);
		if(customer==null)
		{
			throw new NullPointerException();
		}
		
		return new ResponseData("200",msg,customers);
		
	}
	
	@GetMapping
	public ResponseData getCustomerDetails()
	{
		
		List<CustomerDto> output=customerService.getAllCustomer();
		
		if(output==null)
		{
			throw new NullPointerException();
		}
		
		return new ResponseData("200",msg,output);
		
	}
	
	@PutMapping(value = "/{orderId}")
	public ResponseData updateCustomers(@PathVariable(value = "orderId") int orderId)
	{
		String output=customerService.updateCustomerDetails(orderId);
		if(output==null)
		{
			throw new NullPointerException();
		}
		
		return new ResponseData("200",msg,output);
		
		
	}
	
	@DeleteMapping
	public ResponseData deleteAllCustomers()
	{
		String output=customerService.deleteAllCustomers();
		if(output==null)
		{
			throw new NullPointerException();
		}
		
		return new ResponseData("200",msg,output);
		
		
	}
	
	
	

}
