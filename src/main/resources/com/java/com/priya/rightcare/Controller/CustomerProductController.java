package com.priya.rightcare.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priya.rightcare.DTO.CustomerOrderRequest;
import com.priya.rightcare.DTO.CustomerOrderResponse;

import com.priya.rightcare.Entity.Customer;
import com.priya.rightcare.Repository.CustomerRepository;
import com.priya.rightcare.Service.CustomerService;

@RestController
@RequestMapping("/api/v1")
public class CustomerProductController {
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerService customerService;

	@GetMapping("/Getcustomer/{id}")
	public Customer findById(@PathVariable int CustomerId) {
		return customerService.getById(CustomerId);

	}

	@GetMapping("/getcustomer/{name}")
	public Customer findByCustomerfirstName(@PathVariable String firstName) {
		return customerService.getByfirstName(firstName);

	}

	@PostMapping("/placeorder")
	public Customer placeOrder(@RequestBody CustomerOrderRequest customerorder) {

		return customerRepository.save(customerorder.getCustomer());
	}

	@GetMapping("/findallorders")
	public List<Customer> findAllOrders() {

		return customerRepository.findAll();
	}

	@GetMapping("/orderdetail")
	public List<CustomerOrderResponse> getOrderDetails() {

		return customerRepository.getOrderDetails();
	}
	
	

}
