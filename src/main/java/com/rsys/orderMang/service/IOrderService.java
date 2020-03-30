package com.rsys.orderMang.service;

import java.util.List;

import com.rsys.orderMang.entity.Orders;



public interface IOrderService {
	
	List<Orders> getAllOrders();
	String addOrders(int customerId,Orders order);

	String updateOrders(Orders order);

	String deleteAllOrders();
	
	String deleteOneOrder(int orderId);

}
