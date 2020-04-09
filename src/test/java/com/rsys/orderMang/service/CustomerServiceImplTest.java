package com.rsys.orderMang.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.rsys.orderMang.dto.CustomerDto;
import com.rsys.orderMang.entity.Customer;
import com.rsys.orderMang.repo.CustomerRepository;
import com.rsys.orderMang.repo.OrderRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(classes=CustomerServiceImpl.class)
public class CustomerServiceImplTest {
	
	private MockMvc mvc;
	@MockBean
	private CustomerRepository custRepo;
	
	@MockBean
	private OrderRepository orderRepo;
	
	@Autowired
	CustomerServiceImpl serviceImpl;
	
	List<Customer> cutomerDetails= new ArrayList<>();
	List<CustomerDto> custmerDaoList=new ArrayList<>();
	List<Integer> ids=new ArrayList<>();
	Customer cust=new Customer();
	CustomerDto customerDto=new CustomerDto();
	
	@Test
	public void testGetCustomerDetails()throws Exception
	{
		cust.setCustomerId(1);
		cust.setCustomerName("priyanka");
		cutomerDetails.add(cust);
		ids.add(1);
		
		customerDto.setCustId(1);
		customerDto.setCustName("priyanka");
		customerDto.setOrders(ids);
		
		custmerDaoList.add(customerDto);
		
		Mockito.when(custRepo.findAll()).thenReturn(cutomerDetails);
		Mockito.when(custRepo.orderIds(Mockito.anyInt())).thenReturn(ids);
		List<CustomerDto> expected=serviceImpl.getAllCustomer();
		assertEquals(expected.size(),custmerDaoList.size());
		assertNotNull(custmerDaoList);
		
		
	}
	
	@Test
	public void testAddCustomer()throws Exception
	{
		
		cust.setCustomerName("priyanka");
		Mockito.when(custRepo.save(Mockito.any())).thenReturn(cust);
		Customer expected =serviceImpl.addNewCustomer(cust);
		Assertions.assertEquals(expected, cust);
	}
	
	

}
