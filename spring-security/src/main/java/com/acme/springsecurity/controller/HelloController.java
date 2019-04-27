package com.acme.springsecurity.controller;

import com.acme.springsecurity.domain.Hello;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {

	@GetMapping
	public Hello sayHello() {

		return new Hello("Hi, Man");

	}

}
