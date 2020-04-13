package com.rsys.orderMang.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.rsys.orderMang.ExceptionHandler.OrderIsEmptyException;
import com.rsys.orderMang.dto.OrderDto;
import com.rsys.orderMang.entity.OrderProduct;
import com.rsys.orderMang.entity.Orders;
import com.rsys.orderMang.repo.OrderProductRepository;
import com.rsys.orderMang.response.ResponseData;
import com.rsys.orderMang.service.IOrderService;

@RestController("OrderController")
@RequestMapping("/poc/order")
@EnableWebMvc
public class OrderController {

	@Autowired
	private IOrderService iOrderService;
	
	@Autowired
	OrderProductRepository orderProductRepository;

	String msg="Following Data Found";


	@PostMapping(value = "/customerId/{customerId}")
	public ResponseData addOrderDetails(@PathVariable(value = "customerId") int customerId,@RequestBody Orders order)
	{

		String output=iOrderService.addOrders(customerId,order);
		if(output==null)
		{
			throw new OrderIsEmptyException("Order Details cannot be empty");
		}

		return new ResponseData("200",msg,output);
	}

	@GetMapping
	public ResponseData getOrders()
	{
		List<OrderDto> orderDto=iOrderService.getOrders();
		if(orderDto==null)
		{
			throw new OrderIsEmptyException("Not placed any order");
		}

		return new ResponseData("200",msg,orderDto);
	}

	@PutMapping(value="/orderId/customerId/{orderId}/{customerId}")
	public ResponseData updateOrderDetails(@PathVariable(value = "orderId") int orderId, @PathVariable(value = "customerId") int customerId,@RequestBody Orders order)
	{
		
		Orders output=iOrderService.updateOrders(orderId,customerId, order);
		if(output==null)
		{
			throw new OrderIsEmptyException("Order Details cannot be empty");
		}

		return new ResponseData("200",msg,output);

	}

	@DeleteMapping(value="/{orderId}")
	public ResponseData deleteOrderDetails(@PathVariable(value = "orderId") int orderId)
	{
		String output=iOrderService.deleteOneOrder(orderId);		
		return new ResponseData("200",msg,output);

	}

}
