package com.rsys.orderMang.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {

	
	@Id
	@Column(name = "pro_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int proId;
	
	
	@Column(name = "pro_name",unique=true)
	private String proName;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "price")
	private float price;
	
/*	@ManyToMany(mappedBy="products")
	private List<Orders> orders;*/
	
	
	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	
	
	
	
	
	
	
}
