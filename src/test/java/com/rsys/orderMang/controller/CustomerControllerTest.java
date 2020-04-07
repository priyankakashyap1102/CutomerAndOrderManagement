package com.rsys.orderMang.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rsys.orderMang.dto.CustomerDto;
import com.rsys.orderMang.entity.Customer;
import com.rsys.orderMang.service.ICustomerService;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(classes=CustomerController.class)
@EnableWebMvc
public class CustomerControllerTest {
	
	private MockMvc mvc;
	@Autowired
	private WebApplicationContext webApp;
	
	@MockBean
	private ICustomerService customerService;
	
	Customer cust;
	CustomerDto customerDto=new CustomerDto();
	List<CustomerDto> dto=new ArrayList<>();
	List<Integer> oId=new ArrayList<>();
	String output;
	
	@Before
	public void setUp()throws Exception
	{
	
		this.mvc=MockMvcBuilders.webAppContextSetup(webApp).build();
		output="Customer Updated";
		cust=new Customer();
		oId.add(1);
		cust.setCustomerName("priyanka");
		customerDto.setCustId(1);
		customerDto.setCustName("priyanka");
		customerDto.setOrders(oId);
		dto.add(customerDto);
	}
	
	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}
	
	@Test
	public void testGetCustomerDetails()throws Exception
	{
		Mockito.when(customerService.getAllCustomer()).thenReturn(dto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/poc/customer").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		String expected="{\"code\":\"200\",\"message\":\"Following Data Found\",\"response\":[{\"custId\":1,\"custName\":\"priyanka\",\"orders\":[1]}]}";
		assertEquals(expected, result.getResponse().getContentAsString());
		assertNotNull(expected,result.getResponse().getContentAsString());
	}
	
	@Test
	public void testAddCustomerDetails() throws Exception
	{
		String inputJson = mapToJson(cust);
		Mockito.when(customerService.addNewCustomer(Mockito.any())).thenReturn(cust);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/poc/customer")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputJson);
		MvcResult result =  mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		String expected="{\"code\":\"200\",\"message\":\"Following Data Found\",\"response\":{\"customerId\":0,\"customerName\":\"priyanka\"}}";
		assertEquals(expected, result.getResponse().getContentAsString());
		assertNotNull(expected,result.getResponse().getContentAsString());
	}
	
	@Test
	public void testAddUpdateCustomer() throws Exception
	{
		Mockito.when(customerService.updateCustomerDetails(Mockito.anyInt())).thenReturn(output);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/poc/customer/1")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result =  mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		String expected="{\"code\":\"200\",\"message\":\"Following Data Found\",\"response\":\"Customer Updated\"}";
		assertEquals(expected, result.getResponse().getContentAsString());
		assertNotNull(expected,result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testDeleteAllCustomers()throws Exception
	{
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/poc/customer")
				.accept(MediaType.APPLICATION_JSON);
		mvc.perform(requestBuilder).andExpect(status().isOk());
	}
	

}
