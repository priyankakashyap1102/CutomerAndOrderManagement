package com.rsys.orderMang.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsys.orderMang.dto.OrderDto;
import com.rsys.orderMang.dto.ProductDto;
import com.rsys.orderMang.entity.Customer;
import com.rsys.orderMang.entity.Orders;
import com.rsys.orderMang.entity.Product;
import com.rsys.orderMang.repo.CustomerRepository;
import com.rsys.orderMang.repo.OrderRepository;
import com.rsys.orderMang.repo.ProductRepository;


@Service
public class OrdersServiceImpl implements IOrderService {

	@Autowired
	OrderRepository orderRepo;

	@Autowired
	CustomerRepository customerRepo;


	@Autowired
	IProductService proService;
	
	@Autowired
	ProductRepository proRepo;

	@Override
	public List<Orders> getAllOrders() {
		return orderRepo.findAll();
	}
	
	
	@Override
	public List<OrderDto> getOrders() {
     
		List<Orders> allOrders=orderRepo.findAll();
		Optional<Orders> ids=null;
		List<Integer> products=new ArrayList<>();
		OrderDto orderDto=new OrderDto();
		List<OrderDto> dtos=new ArrayList<>();
		for(Orders o:allOrders)
		{
			ids=orderRepo.findById(o.getOrderId());
			orderDto.setOrderId(o.getOrderId());
			orderDto.setNoOfInstallments(o.getNoOfInstallments());
			orderDto.setOutstandingBal(o.getOutstandingBal());
			orderDto.setStatus(o.getStatus());
			orderDto.setTotalPrice(o.getTotalPrice());
			//orderDto.setCustomer(o.getCustomer());
			
		}
		Orders order=ids.get();
		products=orderRepo.findProductId(order.getOrderId());
		
		
		List<Product> productDetails=new ArrayList<>();
		for(Integer p:products)
		{
		
		productDetails=proRepo.getAllByproId(p);
		}
		
		ProductDto productDto=new ProductDto();
		List<ProductDto> pDto=new ArrayList<>();
		for(Product p1:productDetails)
		{
			productDto.setpId(p1.getProId());
			productDto.setQuantity(p1.getQuantity());
			productDto.setpName(p1.getProName());
			
		}
		pDto.add(productDto);
		orderDto.setProduct(pDto);
		dtos.add(orderDto);
				
		return dtos;
	}

	@Override
	@Transactional
	public String addOrders(int customerId,Orders order) {
		String output="";
		float price=0;
		int quantity=0,quan=0,tquan=0;
		int sum=0;
		float tPrice=0;
		Orders orders=new Orders();
		Optional<Customer> byId = customerRepo.findById(customerId);
		if (!byId.isPresent()) {
			throw new NullPointerException();
		}
		List<Product> pIds=order.getProducts();

		for(Product p1:pIds)
		{
			quantity=p1.getQuantity();
			sum=sum+quantity;
		}
		if(sum>5)
		{
			output="Only 5 qunatity can be added!";
		}
		else
		{
			int flag =0;
			for(Product p:pIds)
			{
				quantity=p.getQuantity();
				price=orderRepo.getPrice(p.getProId());
				quan=orderRepo.getTotalQuantity(p.getProId());
				tPrice=tPrice+(price*quantity);
				if(quan==0 || (quan<quantity))
				{
					output="Not Available!";
					flag=1;
					break;
				}
				else
				{

					tquan=quan-quantity;
					p.setQuantity(tquan);
					proService.updateProduct(p);
				}

			}
			if(flag==0)
			{
				Customer cust=byId.get();
				orders.setCustomer(cust);
				orders.setProducts(order.getProducts());
				orders.setTotalPrice(tPrice);
				orders.setStatus("open");
				orders.setNoOfInstallments(order.getNoOfInstallments());
				orders.setOutstandingBal(tPrice);
				orderRepo.save(orders);
				output="Order details Added";
			}

		}

		return output;
	}

	@Override
	public String updateOrders(Orders product) {
		return null;
	}

	@Override
	public String deleteAllOrders() {
		return null;
	}

	@Override
	public String deleteOneOrder(int proId) {
		return null;
	}

	

}
