package com.priya.rightcare.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.priya.rightcare.DTO.PaymentReq;

import com.priya.rightcare.Entity.Customer;
import com.priya.rightcare.Entity.PaymentInfo;
import com.priya.rightcare.Repository.CustomerRepository;
import com.priya.rightcare.Repository.PaymentInfoRepo;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {
	@Autowired
	private PaymentInfoRepo repo;
	@Autowired
	private CustomerRepository customerRepository;
	
	// add customer with paymentdetails(one or more products)
		@PostMapping("/addpayment")
		public PaymentInfo  add(@RequestBody PaymentReq request) {

			return repo.save(request.getPaymentInfo());
		}
	@GetMapping("/getpayment")
	   public List<PaymentInfo> findAllOrders(){
		   
		   return repo.findAll();
	   }
	

}
