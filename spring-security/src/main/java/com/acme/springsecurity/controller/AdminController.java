package com.acme.springsecurity.controller;

import com.acme.springsecurity.domain.User;
import com.acme.springsecurity.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {

	private final UserRepository userRepository;

	public AdminController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/users")
	public Iterable<User> getUsers() {
		List<User> users = userRepository.findAll();
		log.info("users: {}", users);
		return users;
	}

}
