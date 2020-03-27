package com.rsys.orderMang.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class Orders {

	@Id
	@Column(name = "order_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;


	@Column(name = "status")
	private String status;

	@ManyToMany(targetEntity =Product.class,cascade= {CascadeType.ALL})
	@JoinTable(name ="OrderPro",joinColumns= {@JoinColumn(name="Order_Id")},inverseJoinColumns= {@JoinColumn(name="Product_Id")})
	private List<Product> products;

	@ManyToOne
	@JoinColumn(name="order_id", nullable=false,insertable=false,updatable=false)
	private Customer customer;

	@Column(name = "noOfInstallments")
	private int noOfInstallments;

	@Column(name = "totalPrice")
	private float totalPrice;

	@Column(name = "outstandingBal")
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

	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
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
