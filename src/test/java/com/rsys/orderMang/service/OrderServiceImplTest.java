package com.rsys.orderMang.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.rsys.orderMang.dto.OrderDto;
import com.rsys.orderMang.entity.Customer;
import com.rsys.orderMang.entity.OrderProduct;
import com.rsys.orderMang.entity.Orders;
import com.rsys.orderMang.entity.Product;
import com.rsys.orderMang.repo.CustomerRepository;
import com.rsys.orderMang.repo.OrderProductRepository;
import com.rsys.orderMang.repo.OrderRepository;
import com.rsys.orderMang.repo.ProductRepository;


@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(classes=OrdersServiceImpl.class)
@EnableWebMvc
public class OrderServiceImplTest {

	@MockBean
	OrderRepository orderRepo;

	@MockBean
	CustomerRepository customerRepo;

	@MockBean
	IProductService proService;

	@MockBean
	ProductRepository proRepo;

	@MockBean
	OrderProductRepository orderProRepo;

	@Autowired
	OrdersServiceImpl orderService;


	OrderProduct orderProduct=new OrderProduct();
	List<OrderProduct> orderPro=new ArrayList<>();
	List<OrderDto> dtos=new ArrayList<>();
	List<Orders> list=new ArrayList<>();
	OrderDto dto=new OrderDto();

	String output;
	Orders order=new Orders();
	Customer cust=new Customer();
	Product pro=new Product();


	@Test
	public void testAddOrderDetails()throws Exception
	{
		

		pro.setQuantity(4);
		order.setNoOfInstallments(5);

		cust.setCustomerId(1);

		orderProduct.setProId(2);

		orderProduct.setProName("Dettol");
		orderProduct.setQuantity(4);
		orderPro.add(orderProduct);


		order.setOrderPro(orderPro);
		output="Order details Added";

		Optional<Customer> value=Optional.of(cust);
		Mockito.when(orderRepo.save(Mockito.any())).thenReturn(order);
		Mockito.when(proRepo.getOne(Mockito.anyInt())).thenReturn(pro);
		Mockito.when(customerRepo.findById(Mockito.anyInt())).thenReturn(value);
		String response=orderService.addOrders(1, order);
		assertEquals(output, response);
	}

	@Test
	public void testAddOrderDetailsWithQuantityZero()throws Exception
	{

	

		pro.setQuantity(0);
		order.setNoOfInstallments(5);

		cust.setCustomerId(1);

		orderProduct.setProId(2);

		orderProduct.setProName("Dettol");
		orderProduct.setQuantity(4);
		orderPro.add(orderProduct);


		order.setOrderPro(orderPro);
		output="Not Available!";

		Optional<Customer> value=Optional.of(cust);
		Mockito.when(orderRepo.save(Mockito.any())).thenReturn(order);
		Mockito.when(proRepo.getOne(Mockito.anyInt())).thenReturn(pro);
		Mockito.when(customerRepo.findById(Mockito.anyInt())).thenReturn(value);
		String response=orderService.addOrders(1, order);
		assertEquals(output, response);

	}

	@Test
	public void testAddOrderDetailsWithQauntityGreaterthnaFive()throws Exception
	{
		

		pro.setQuantity(10);
		order.setNoOfInstallments(5);

		cust.setCustomerId(1);

		orderProduct.setProId(2);

		orderProduct.setProName("Dettol");
		orderProduct.setQuantity(9);
		orderPro.add(orderProduct);


		order.setOrderPro(orderPro);
		output="Only 5 qunatity can be added!";

		Optional<Customer> value=Optional.of(cust);
		Mockito.when(orderRepo.save(Mockito.any())).thenReturn(order);
		Mockito.when(proRepo.getOne(Mockito.anyInt())).thenReturn(pro);
		Mockito.when(customerRepo.findById(Mockito.anyInt())).thenReturn(value);
		String response=orderService.addOrders(1, order);
		assertEquals(output, response);

	}

	@Test
	public void testUpdateOrderDetails()throws Exception
	{
		

		pro.setQuantity(10);
		order.setNoOfInstallments(5);
		order.setStatus("open");

		cust.setCustomerId(1);

		orderProduct.setProId(2);

		orderProduct.setProName("Dettol");
		orderProduct.setQuantity(9);
		orderPro.add(orderProduct);


		order.setOrderPro(orderPro);

		Optional<Orders> oIds=Optional.of(order);
		Optional<Customer> value=Optional.of(cust);

		Mockito.when(customerRepo.findById(Mockito.anyInt())).thenReturn(value);
		Mockito.when(orderRepo.findById(Mockito.anyInt())).thenReturn(oIds);
		Mockito.when(orderRepo.save(Mockito.any())).thenReturn(order);
		Mockito.when(proRepo.getOne(Mockito.anyInt())).thenReturn(pro);

		Orders o=orderService.updateOrders(1, 1, order);

		assertEquals(o, order);	
	}

	@Test
	public void testGetOrderDetails()throws Exception
	{
		
		order.setOrderId(1);
		order.setNoOfInstallments(5);
		order.setOutstandingBal(100);
		order.setStatus("Open");
		order.setTotalPrice(100);

		orderProduct.setProId(2);

		orderProduct.setProName("Dettol");
		orderProduct.setQuantity(9);
		orderPro.add(orderProduct);
		
		order.setOrderPro(orderPro);
		cust.setCustomerId(1);
		list.add(order);
		
		dto.setCustomer(cust);
		dto.setOrderId(1);
		
		dtos.add(dto);
		
		Mockito.when(orderRepo.findAll()).thenReturn(list);
		Mockito.when(orderProRepo.getByOderid(Mockito.anyInt())).thenReturn(orderPro);
		
		List<OrderDto> dtosExpec=orderService.getOrders();
		
		assertEquals(dtosExpec.size(),dtos.size());
		assertNotNull(dtosExpec);
		
	}
	
	@Test
	public void testDeleteOrders()throws Exception
	{
		String output="Deleted Successfully";
		order.setOrderId(1);
		order.setNoOfInstallments(5);
		order.setOutstandingBal(100);
		order.setStatus("Open");
		order.setTotalPrice(100);
		Mockito.when(orderRepo.getOne(Mockito.anyInt())).thenReturn(order);
		String exp=orderService.deleteOneOrder(1);
		assertEquals(exp, output);
		
	}
	
	
}
