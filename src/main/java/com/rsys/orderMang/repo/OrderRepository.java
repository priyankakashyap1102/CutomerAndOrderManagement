package com.rsys.orderMang.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.rsys.orderMang.entity.Orders;
import com.rsys.orderMang.entity.Product;


@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
	
	
	@Query(value="Select p.price from product p where p.pro_id=:pId", nativeQuery=true)
	float getPrice(@Param("pId") int pId);
	
	@Query(value="Select p.quantity from product p where p.pro_id=:pId", nativeQuery=true)
	int getTotalQuantity(@Param("pId") int pId);
	
	@Query(value="Select o.pro_id from order_pro o where o.order_id=:orderId", nativeQuery=true)
	List<Integer> findProductId(@Param("orderId") int orderId);
	
	

}
