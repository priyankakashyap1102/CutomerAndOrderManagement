package com.rsys.orderMang.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rsys.orderMang.dao.CustomerDao;
import com.rsys.orderMang.entity.Customer;
import com.rsys.orderMang.entity.Orders;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	
	@Query(value ="Select o.order_id from orders o where o.customer_id =:id",nativeQuery = true)
	List<Integer> orderIds(@Param("id") int id);

}
