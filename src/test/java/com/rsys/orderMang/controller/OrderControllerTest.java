package com.rsys.orderMang.controller;

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
import com.rsys.orderMang.dto.OrderDto;
import com.rsys.orderMang.entity.Customer;
import com.rsys.orderMang.entity.OrderProduct;
import com.rsys.orderMang.entity.Orders;
import com.rsys.orderMang.service.IOrderService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(classes=OrderController.class)
@EnableWebMvc
public class OrderControllerTest {

	private MockMvc mvc;
	@Autowired
	private WebApplicationContext webApp;


	@MockBean
	private IOrderService iOrderService;


	Orders order;
	Orders order1=new Orders();
	Customer cust;
	OrderProduct orderProduct=new OrderProduct();
	List<OrderProduct> orderPro=new ArrayList<>();
	List<OrderDto> dtos=new ArrayList<>();
	OrderDto dto=new OrderDto();

	String output;

	@Before
	public void setUp()throws Exception
	{
		this.mvc=MockMvcBuilders.webAppContextSetup(webApp).build();
		order=new Orders();
		cust=new Customer();
		order.setOrderId(23);
		order.setNoOfInstallments(5);
		order.setOutstandingBal(200);
		order.setTotalPrice(200);
		order.setStatus("open");
		cust.setCustomerId(18);
		cust.setCustomerName("priyanka");
		order.setCustomer(cust);

		orderProduct.setProId(2);
		orderProduct.setId(24);
		orderProduct.setProName("Dettol");
		orderProduct.setQuantity(4);
		orderPro.add(orderProduct);

		dto.setCustomer(cust);
		dto.setNoOfInstallments(5);
		dto.setOrderId(1);
		dto.setOutstandingBal(200);
		dto.setStatus("open");
		dto.setTotalPrice(200);
		dtos.add(dto);

		order.setOrderPro(orderPro);
		output="Order details Added";


	}
	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	@Test
	public void testaddOrderDetails() throws Exception
	{
		String inputJson = mapToJson(order);
		Mockito.when(iOrderService.addOrders(Mockito.anyInt(), Mockito.any())).thenReturn(output);	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/poc/order/1/order")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputJson);
		MvcResult result =  mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		String expected="{\"code\":\"200\",\"message\":\"Following Data Found\",\"response\":\"Order details Added\"}";
		assertEquals(expected, result.getResponse().getContentAsString());
		assertNotNull(expected,result.getResponse().getContentAsString());

	}



	@Test
	public void testUpdateOrderDetails() throws Exception
	{
		String inputJson = mapToJson(order);
		Mockito.when(iOrderService.updateOrders(Mockito.anyInt(),Mockito.anyInt(),Mockito.any())).thenReturn(order);	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/poc/order/23/18")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputJson);
		MvcResult result =  mvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		String expected="{\"code\":\"200\",\"message\":\"Following Data Found\",\"response\":{\"orderId\":23,\"status\":\"open\",\"orderPro\":[{\"id\":24,\"proId\":2,\"proName\":\"Dettol\",\"quantity\":4}],\"noOfInstallments\":5,\"totalPrice\":200.0,\"outstandingBal\":200.0,\"customer_id\":18}}";
		assertEquals(expected, result.getResponse().getContentAsString());
		assertNotNull(expected,result.getResponse().getContentAsString());

	}

	@Test
	public void testGetOrderDetails() throws Exception
	{
		Mockito.when(iOrderService.getOrders()).thenReturn(dtos);	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/poc/order").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		String expected="{\"code\":\"200\",\"message\":\"Following Data Found\",\"response\":[{\"orderId\":1,\"status\":\"open\",\"product\":null,\"customer\":{\"customerId\":18,\"customerName\":\"priyanka\"},\"noOfInstallments\":5,\"totalPrice\":200.0,\"outstandingBal\":200.0}]}";
		assertEquals(expected, result.getResponse().getContentAsString());
		assertNotNull(expected,result.getResponse().getContentAsString());
	}

	@Test
	public void testDeleteOrderDetails() throws Exception
	{
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/poc/order/2")
				.accept(MediaType.APPLICATION_JSON);
		mvc.perform(requestBuilder).andExpect(status().isOk());
	}



}
