package com.rsys.orderMang.dto;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rsys.orderMang.entity.Customer;


@JsonSerialize
public class OrderDto {

	private int orderId;
	private String status;
	private List<ProductDto> product;
	private Customer customer;
	private int noOfInstallments;
	private float totalPrice;
	private float outstandingBal;



	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<ProductDto> getProduct() {
		return product;
	}
	public void setProduct(List<ProductDto> product) {
		this.product = product;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public int getNoOfInstallments() {
		return noOfInstallments;
	}
	public void setNoOfInstallments(int noOfInstallments) {
		this.noOfInstallments = noOfInstallments;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public float getOutstandingBal() {
		return outstandingBal;
	}
	public void setOutstandingBal(float outstandingBal) {
		this.outstandingBal = outstandingBal;
	}





}
