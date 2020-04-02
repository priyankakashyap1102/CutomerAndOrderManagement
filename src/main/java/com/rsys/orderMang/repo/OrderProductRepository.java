package com.rsys.orderMang.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rsys.orderMang.entity.OrderProduct;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {
	
	@Query(value="Select * from order_product o where o.order_id=:orderId", nativeQuery=true)
	List<OrderProduct> getByOderid(@Param("orderId")  int orderId);

}
