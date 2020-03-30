package com.rsys.orderMang.dao;

import java.util.List;

import com.rsys.orderMang.dto.CustomerDto;
import com.rsys.orderMang.entity.Orders;

public class CustomerDao {
	
	
	int custId;
	String custName;
	List<CustomerDto> orders;
	
	public List<CustomerDto> getOrders() {
		return orders;
	}
	public void setOrders(List<CustomerDto> orders) {
		this.orders = orders;
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	
	

}
