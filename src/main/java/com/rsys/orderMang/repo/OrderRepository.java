package com.rsys.orderMang.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rsys.orderMang.entity.Customer;
import com.rsys.orderMang.entity.Orders;


@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
	

}
