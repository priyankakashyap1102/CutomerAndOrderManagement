package com.rsys.orderMang.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.rsys.orderMang.ExceptionHandler.OrderIsEmptyException;

import com.rsys.orderMang.dto.OrderDto;
import com.rsys.orderMang.dto.ProductDto;
import com.rsys.orderMang.entity.Customer;
import com.rsys.orderMang.entity.OrderProduct;
import com.rsys.orderMang.entity.Orders;
import com.rsys.orderMang.entity.Product;
import com.rsys.orderMang.repo.CustomerRepository;
import com.rsys.orderMang.repo.OrderProductRepository;
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

	@Autowired
	OrderProductRepository orderProRepo;

	@Override
	public List<Orders> getAllOrders() {
		return orderRepo.findAll();
	}

	@Override
	public List<OrderDto> getOrders() {

		List<Orders> allOrders = orderRepo.findAll();
		List<OrderDto> dtos = new ArrayList<>();

		for (Orders o : allOrders) {
			OrderDto orderDto = new OrderDto();
			orderDto.setOrderId(o.getOrderId());
			orderDto.setNoOfInstallments(o.getNoOfInstallments());
			orderDto.setOutstandingBal(o.getOutstandingBal());
			orderDto.setStatus(o.getStatus());
			orderDto.setTotalPrice(o.getTotalPrice());
			orderDto.setCustomer(o.getCustomer());

			ProductDto productDto = new ProductDto();
			List<ProductDto> pDto = new ArrayList<>();
			List<OrderProduct> op = orderProRepo.getByOderid(o.getOrderId());
			for (OrderProduct i : op) {
				productDto.setpId(i.getProId());
				productDto.setQuantity(i.getQuantity());
				productDto.setpName(i.getProName());
				pDto.add(productDto);
			}

			orderDto.setProduct(pDto);
			dtos.add(orderDto);
		}
		return dtos;
	}

	@Override
	public String addOrders(int customerId, Orders order) {
		String output = "";
		float price = 0;
		int quantity = 0, quan = 0, tquan = 0;
		int sum = 0;
		float tPrice = 0;
		Orders orders = new Orders();
		Optional<Customer> byId = customerRepo.findById(customerId);
		if (!byId.isPresent()) {
			throw new OrderIsEmptyException("Please Provide valid customerId");
		}
		List<OrderProduct> pIds = order.getOrderPro();
		for (OrderProduct p1 : pIds) {
			quantity = p1.getQuantity();
			sum = sum + quantity;
		}
		if (sum > 5) {
			output = "Only 5 qunatity can be added!";
		} else {
			int flag = 0;
			for (OrderProduct p : pIds) {
				quantity = p.getQuantity();
				Product prt = proRepo.getOne(p.getProId());
				price = prt.getPrice();
				quan = prt.getQuantity();
				tPrice = tPrice + (price * quantity);
				if (quan == 0 || (quan < quantity)) {
					output = "Not Available!";
					flag = 1;
					break;
				} else {
					tquan = quan - quantity;
					prt.setQuantity(tquan);
					proService.updateProduct(prt);
				}
			}
			if (flag == 0) {
				Customer cust = byId.get();
				orders.setCustomer(cust);
				orders.setTotalPrice(tPrice);
				orders.setStatus("open");
				if(order.getNoOfInstallments()>5)
				throw new OrderIsEmptyException("Only five installments Allowed");
				else
				orders.setNoOfInstallments(order.getNoOfInstallments());
				orders.setOutstandingBal(tPrice);
				//orderPro.add(orderProduct);
				orders.setOrderPro(order.getOrderPro());
				orderRepo.save(orders);
				output = "Order details Added";
			}

		}

		return output;
	}

	@Override
	@Transactional
	public Orders updateOrders(int orderId, int customerId, Orders orders) {
		

		Optional<Orders> oId = orderRepo.findById(orderId);
		if(!oId.isPresent())
		{
			throw new OrderIsEmptyException("Please Provide valid orderId");
		}
		Orders order = oId.get();
		List<OrderProduct> prevProduct = order.getOrderPro();
		List<OrderProduct> newProduct = orders.getOrderPro();

		Optional<Customer> byId = customerRepo.findById(customerId);
		
		if (!byId.isPresent()) {
			throw new OrderIsEmptyException("Please Provide valid  customerId");
		}
		
		int newQuantity = 0, oldQuantity = 0, tquan = 0, quan = 0;
		float price = 0, tprice = 0;
		for (OrderProduct prevOrderPro : prevProduct) {

			for (OrderProduct newOrderPro : newProduct) {

				if (prevOrderPro.getId() == newOrderPro.getId()) {
					newQuantity = newOrderPro.getQuantity();
					oldQuantity = prevOrderPro.getQuantity();
					Product prep = proRepo.getOne(prevOrderPro.getProId());
					quan = prep.getQuantity();
					price = prep.getPrice();
					tprice = tprice +(newQuantity * price);

					if (newQuantity == 0) {
						//orderProRepo.deleteById(newOrderPro.getId());
						
						break;
					} else {
						if (newQuantity < oldQuantity) {
							tquan = quan + (oldQuantity - newQuantity);
							prep.setQuantity(tquan);
							proService.updateProduct(prep);
						} else {
							tquan = quan - (newQuantity - oldQuantity);
							prep.setQuantity(tquan);
							proService.updateProduct(prep);
						}

						prevOrderPro.setQuantity(newOrderPro.getQuantity());
						orderProRepo.save(prevOrderPro);

					}

				}
			}

		}
		Customer cust = byId.get();
		order.setCustomer(cust);
		order.setTotalPrice(tprice);
		
		if(orders.getStatus().equalsIgnoreCase("close"))
		{
			order.setNoOfInstallments(0);
			order.setOutstandingBal(0);
			order.setStatus(orders.getStatus());
			
		}
		else
		{
		order.setNoOfInstallments(5);
		order.setOutstandingBal(tprice);
		order.setStatus("open");
		}
		return orderRepo.save(order);
	}

	@Override
	public String deleteOneOrder(int orderId) {
		
		Orders order = orderRepo.getOne(orderId);
		if(order==null)
		{
			throw new OrderIsEmptyException("Please Provide valid  orderId");			
		}
		orderRepo.deleteById(orderId);
		return "Deleted Successfully";
	}

}
