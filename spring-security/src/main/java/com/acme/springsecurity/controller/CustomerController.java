package com.acme.springsecurity.controller;

import com.acme.springsecurity.domain.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@GetMapping
	public List<Customer> getCustomers() {
		return Arrays.asList(new Customer("John", "Doe"), new Customer("Laura", "Palmer"));
	}
}
