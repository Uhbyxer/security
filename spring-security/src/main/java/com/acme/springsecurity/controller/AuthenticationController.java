package com.acme.springsecurity.controller;

import com.acme.springsecurity.domain.Login;
import com.acme.springsecurity.security.TokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;
	private final TokenProvider tokenProvider;
	private final UserDetailsService userDetailsService;

	public AuthenticationController(AuthenticationManager authenticationManager, TokenProvider tokenProvider,
			UserDetailsService userDetailsService) {
		this.authenticationManager = authenticationManager;
		this.tokenProvider = tokenProvider;
		this.userDetailsService = userDetailsService;
	}

	@PostMapping("/login")
	public Login login(@RequestBody Login loginRQ) {
//		UserDetails userDetails = userDetailsService.loadUserByUsername(loginRQ.getUsername());

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginRQ.getUsername(), loginRQ.getPassword());

		Authentication authentication = authenticationManager
				.authenticate(authenticationToken);


		String token = tokenProvider.createToken(loginRQ.getUsername(), authentication.getAuthorities());//!!!!!!

		Login loginRS = Login.builder().username(loginRQ.getUsername()).token(token).build();
		return loginRS;
	}
}
