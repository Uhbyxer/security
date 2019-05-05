package com.acme.springsecurity.controller;

import com.acme.springsecurity.domain.Hello;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/hello")
public class HelloController {

	@GetMapping
	public Hello sayHello() {

		return new Hello("Hi, Man");

	}

	@Secured({ "ROLE_POWER_USER" })
	@GetMapping("/power")
	public Hello sayHelloPower(Principal principal) {

		return new Hello("Hi, " + principal.getName());

	}

}
