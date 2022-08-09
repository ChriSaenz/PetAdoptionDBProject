package com.sprinboot.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.backend.model.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

	@Query("select r from Receipt r where r.employee.id=?1")
	List<Receipt> findByEmployeeId(Long id);

	@Query("select r from Receipt r where r.customer.id=?1")
	List<Receipt> findByCustomerId(Long id);
	
	@Query("select r from Receipt r where r.request.id=?1")
	List<Receipt> findByRequestId(Long id);
	
	@Query("select r from Receipt r where r.cost>?1")
	List<Receipt> findByPriceGreaterThan(Double price);

	@Query("select r from Receipt r where r.cost<?1")
	List<Receipt> findByPriceLessThan(Double price);
	
	@Query("select r from Receipt r where r.cost=?1")
	List<Receipt> findByPriceEqualTo(Double price);


}
