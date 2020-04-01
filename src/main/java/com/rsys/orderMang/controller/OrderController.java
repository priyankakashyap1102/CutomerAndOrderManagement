package com.rsys.orderMang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rsys.orderMang.dto.OrderDto;
import com.rsys.orderMang.entity.Orders;
import com.rsys.orderMang.repo.OrderRepository;
import com.rsys.orderMang.response.ResponseData;
import com.rsys.orderMang.service.IOrderService;

@RestController("OrderController")
@RequestMapping("/poc/order")
public class OrderController {
	
	@Autowired
    private IOrderService iOrderService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	String msg="Following Data Found";
	
	
	@PostMapping(value = "/{customerId}/order")
	public ResponseData addOrderDetails(@PathVariable(value = "customerId") int customerId,@RequestBody Orders order)
	{
		
		String output=iOrderService.addOrders(customerId,order);
		if(output==null)
		{
			throw new NullPointerException();
		}
		
		return new ResponseData("200",msg,output);
		
	}
	
	@GetMapping
	public ResponseData getOrders()
	{
		List<OrderDto> orderDto=iOrderService.getOrders();
		if(orderDto==null)
		{
			throw new NullPointerException();
		}
		
		return new ResponseData("200",msg,orderDto);
	}
	
	@PutMapping(value="/{orderId}")
	public ResponseData updateOrderDetails(@PathVariable(value = "orderId") int orderId,@RequestBody Orders order)
	{
		String output=iOrderService.updateOrders(orderId, order);
		if(output==null)
		{
			throw new NullPointerException();
		}
		
		return new ResponseData("200",msg,output);
		
	}
	
	@DeleteMapping(value="/{orderId}")
	public ResponseData deleteOrderDetails(@PathVariable(value = "orderId") int orderId)
	{
		String output=iOrderService.deleteOneOrder(orderId);
		if(output==null)
		{
			throw new NullPointerException();
		}
		
		return new ResponseData("200",msg,output);
		
	}

}
