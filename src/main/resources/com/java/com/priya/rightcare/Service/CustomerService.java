package com.priya.rightcare.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.priya.rightcare.Entity.Customer;
import com.priya.rightcare.Repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer getByCustomerId(int customerId) {

		return customerRepository.findById(customerId);

	}

	public Customer getByfirstName(String firstName) {

		return customerRepository.findByfirstName(firstName);
	}

	public Customer getById(int customerId) {
		// TODO Auto-generated method stub
		return customerRepository.findById(customerId);
	}

}
