package com.rsys.orderMang.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsys.orderMang.entity.Product;
import com.rsys.orderMang.repo.ProductRepository;


@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	ProductRepository proRepo;

	@Override
	public List<Product> getAllProduct() {
		return proRepo.findAll();
	}

	@Override
	public String addItemToProduct(Product productObj) {

		String output="";
		Product product=new Product();
		product.setPrice(productObj.getPrice());
		product.setProName(productObj.getProName());
		product.setQuantity(productObj.getQuantity());
		proRepo.save(product);
		output="Product Added Sucessfully";

		return output;
	}

	@Override
	@Transactional
	public String updateProduct(Product product) {
		Product product2=proRepo.getOne(product.getProId());
		String output="";
		if(product.getQuantity()==0)
		{
			//deleteOneProduct(product.getProId());
			output="Not Available";
		}
		else
		{
			product2.setQuantity(product.getQuantity());
			proRepo.save(product2);
			output="Update Successfully!";

		}
		return output;
		}

		@Override
		public String deleteAllProduct() {

			proRepo.deleteAll();
			return "Deleted Successfully";
		}

		@Override
		public String deleteOneProduct(int proId) {

			proRepo.deleteById(proId);
			return "Deleted Successfully";
		}

	}
