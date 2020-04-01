package com.rsys.orderMang.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	@JoinTable(name ="OrderPro",joinColumns= {@JoinColumn(name="order_Id")},inverseJoinColumns= {@JoinColumn(name="pro_Id")})
	private List<Product> products;
	
	/*@OneToMany(cascade = CascadeType.ALL )
	@JoinColumn(name="order_id")
	private List<OrderProduct> orderPro=new ArrayList<OrderProduct>();*/
	

	@ManyToOne(/*fetch = FetchType.LAZY*/)
	@JoinColumn(name="customer_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
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


	@JsonIgnore
	public Customer getCustomer() {
		return customer;
	}

	@JsonIgnore
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


	public int getCustomer_id()
	{
		return customer.getCustomerId();
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	
	
	












}
