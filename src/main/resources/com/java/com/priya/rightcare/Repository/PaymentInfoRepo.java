package com.priya.rightcare.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.priya.rightcare.Entity.Customer;
import com.priya.rightcare.Entity.PaymentInfo;

@Repository
public interface PaymentInfoRepo extends JpaRepository<PaymentInfo, Integer>{

	PaymentInfo save(Customer customer);
	
	

}
