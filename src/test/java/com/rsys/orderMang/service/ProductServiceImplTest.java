package com.rsys.orderMang.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.rsys.orderMang.entity.Product;
import com.rsys.orderMang.repo.ProductRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(classes=ProductServiceImpl.class)
public class ProductServiceImplTest {
	
	@MockBean
	ProductRepository proRepo;
	
	@Autowired
	ProductServiceImpl impl;

	
	Product pro=new Product();
	List<Product> proList=new ArrayList<>();
	
	
	@Test
	public void testAddItemInProduct()throws Exception
	{
		pro.setPrice(100);
		pro.setProName("Shampoo");
		pro.setQuantity(10);
		
		Mockito.when(proRepo.save(Mockito.any())).thenReturn(pro);
		Product expected=impl.addItemToProduct(pro);
		assertEquals(expected,pro);
	}
	
	@Test
	public void testGetAllProductDetails()throws Exception
	{
		pro.setPrice(100);
		pro.setProName("Shampoo");
		pro.setQuantity(10);
		proList.add(pro);
		
		Mockito.when(proRepo.findAll()).thenReturn(proList);
		List<Product> expected=impl.getAllProduct();
		assertEquals(expected,proList);
	}
	
	@Test
	public void testUpdateProductDetails()throws Exception
	{
		pro.setPrice(100);
		pro.setProName("Shampoo");
		pro.setQuantity(10);
		pro.setProId(1);
		
		Mockito.when(proRepo.getOne(Mockito.anyInt())).thenReturn(pro);
		Mockito.when(proRepo.save(Mockito.any())).thenReturn(pro);
		String expected=impl.updateProduct(pro);
		assertEquals(expected,"Update Successfully!");
	}
	
	@Test
	public void testUpdateProductwithQuantityZero()throws Exception
	{
		pro.setPrice(100);
		pro.setProName("Shampoo");
		pro.setQuantity(0);
		pro.setProId(1);
		
		Mockito.when(proRepo.getOne(Mockito.anyInt())).thenReturn(pro);
		Mockito.when(proRepo.save(Mockito.any())).thenReturn(pro);
		String expected=impl.updateProduct(pro);
		assertEquals(expected,"Not Available");
	}
	
	@Test
	public void testDeleteAll()throws Exception
	{
		String expected=impl.deleteAllProduct();
		assertEquals(expected,"Deleted Successfully");
	}
	
	@Test
	public void testDeleteOne()throws Exception
	{
		Optional<Product> byId=Optional.of(pro);
		Mockito.when(proRepo.findById(Mockito.anyInt())).thenReturn(byId);
		String expected=impl.deleteOneProduct(1);
		assertEquals(expected,"Deleted Successfully");
	}
	

}
