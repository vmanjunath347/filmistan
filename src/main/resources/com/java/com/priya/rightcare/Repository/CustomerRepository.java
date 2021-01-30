package com.priya.rightcare.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.priya.rightcare.DTO.CustomerOrderResponse;

import com.priya.rightcare.Entity.Customer;
import com.priya.rightcare.Entity.PaymentInfo;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("SELECT new com.priya.rightcare.DTO.CustomerOrderResponse (c.customerId,p.productName,p.productPrice,p.quantity) From Customer c JOIN c.products p")
	public List<CustomerOrderResponse> getOrderDetails();

	public Customer findById(int categoryId);

	public Customer findByfirstName(String firstName);

	public PaymentInfo save(PaymentInfo paymentInfo);

}
