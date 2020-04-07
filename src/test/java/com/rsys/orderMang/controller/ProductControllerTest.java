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
import com.rsys.orderMang.entity.Product;
import com.rsys.orderMang.repo.ProductRepository;
import com.rsys.orderMang.service.IProductService;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(classes=ProductController.class)
@EnableWebMvc
public class ProductControllerTest {
	
	private MockMvc mvc;
	@Autowired
	private WebApplicationContext webApp;
	
	@MockBean
	private IProductService productService;
	
	@MockBean
	private ProductRepository productRepository;
	
	Product product;
	Product product1;
	
	List<Product> pro=new ArrayList<>();
	String output;
	
	@Before
	public void setUp()throws Exception
	{
	
		this.mvc=MockMvcBuilders.webAppContextSetup(webApp).build();
		output="Update Successfully!";
		product=new Product();
		product1=new Product();
		product.setProName("Shampoo");
		product.setPrice(240);
		product.setQuantity(10);
		
		product1.setProName("Shampoo");
		product1.setProId(1);
		product1.setPrice(240);
		product1.setQuantity(10);
		pro.add(product1);
	}
	
	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}
	
	@Test
	public void testAddProductDetails()throws Exception
	{
		String inputJson = mapToJson(product);
		Mockito.when(productService.addItemToProduct(Mockito.any())).thenReturn(product);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/poc/product").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputJson);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		String expected="{\"code\":\"200\",\"message\":\"Following Data Found\",\"response\":{\"proId\":0,\"proName\":\"Shampoo\",\"quantity\":10,\"price\":240.0}}";
		assertEquals(expected, result.getResponse().getContentAsString());
		assertNotNull(expected,result.getResponse().getContentAsString());
	}
	
	@Test
	public void testUpdateProductDetails()throws Exception
	{
		String inputJson = mapToJson(product1);
		Mockito.when(productService.updateProduct(Mockito.any())).thenReturn(output);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/poc/product/update").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputJson);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		String expected="";
		assertEquals(expected, result.getResponse().getContentAsString());
		assertNotNull(expected,result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testGetProductDetails()throws Exception
	{
		Mockito.when(productService.getAllProduct()).thenReturn(pro);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/poc/product").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		String expected="{\"code\":\"200\",\"message\":\"Following Data Found\",\"response\":[{\"proId\":1,\"proName\":\"Shampoo\",\"quantity\":10,\"price\":240.0}]}";
		assertEquals(expected, result.getResponse().getContentAsString());
		assertNotNull(expected,result.getResponse().getContentAsString());
	}
	
	
	@Test
	public void testDeleteAllProducts()throws Exception
	{
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/poc/product")
				.accept(MediaType.APPLICATION_JSON);
		mvc.perform(requestBuilder).andExpect(status().isOk());
	}
	
	

}
