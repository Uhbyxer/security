package com.acme.springsecurity.controller;

import com.acme.springsecurity.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {

	@GetMapping
	public List<Customer> getCustomers() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("Auth: {}", authentication.toString());

		return Arrays.asList(new Customer("John", "Doe"), new Customer("Laura", "Palmer"));
	}
}
