package com.rsys.orderMang.service;

import java.util.List;

import com.rsys.orderMang.dto.OrderDto;
import com.rsys.orderMang.entity.Orders;



public interface IOrderService {
	
	List<Orders> getAllOrders();
	
	List<OrderDto> getOrders();
	
	String addOrders(int customerId,Orders order);

	String updateOrders(int orderId,Orders order);
	
	String deleteOneOrder(int orderId);

}
