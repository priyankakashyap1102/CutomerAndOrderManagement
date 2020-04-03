package com.rsys.orderMang.dto;

import java.util.List;


public class CustomerDto {


	int custId;
	String custName;
	List<Integer> orders;


	public List<Integer> getOrders() {
		return orders;
	}
	public void setOrders(List<Integer> orders) {
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
