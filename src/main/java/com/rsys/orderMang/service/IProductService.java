package com.rsys.orderMang.service;

import java.util.List;

import com.rsys.orderMang.entity.Product;


public interface IProductService {
	
	List<Product> getAllProduct();


	Product addItemToProduct(Product productObj);

	String updateProduct(Product product);

	String deleteAllProduct();
	
	String deleteOneProduct(int proId);

}
