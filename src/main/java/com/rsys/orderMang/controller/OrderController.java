package com.rsys.orderMang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	
	

}
