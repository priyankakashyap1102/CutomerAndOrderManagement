package com.rsys.orderMang.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.rsys.orderMang.entity.Product;
import com.rsys.orderMang.repo.ProductRepository;
import com.rsys.orderMang.response.ResponseData;
import com.rsys.orderMang.service.IProductService;


@RestController("ProductController")
@RequestMapping("/poc/product")
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ProductRepository productRepository;
	
	String msg = "Following Data Found";
	
	@PostMapping
	public ResponseData addProduct(@RequestBody Product product)
	{
		
		Product pro=productService.addItemToProduct(product);
		if(pro==null)
		{
			throw new NullPointerException();
		}
		
		return new ResponseData("200",msg,pro);
		
	}
	
	@GetMapping
	public ResponseData getProduct()
	{
		List<Product> product=productService.getAllProduct();
		if(product==null)
		{
			throw new NullPointerException();
		}
		
		return new ResponseData("200",msg,product);
		
	}
	
	@PutMapping("/update")
	public ResponseData updateProduct(@RequestBody Product product)
	{
		Optional<Product> pro=productRepository.findById(product.getProId());
		
		if(!pro.isPresent())
			throw new NullPointerException();
		
		String output=productService.updateProduct(product);		
		return new ResponseData("200",msg,output);
		
	}
	
	@DeleteMapping
	public String deleteAll() {
		productService.deleteAllProduct();
		return "Deleted All Item From Cart";

	}
	
}
