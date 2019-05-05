package com.acme.springsecurity.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Configuration
public class TokenProperties {
	private String secret;
	private long expired;

	@Value("${jwt.token.secret}")
	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Value("${jwt.token.expired}")
	public void setExpired(long expired) {
		this.expired = expired;
	}

	@Bean
	public String jwtSecret() {
		return Base64.getEncoder().encodeToString(secret.getBytes());
	}

	@Bean
	public long jwtTTL() {
		return expired;
	}
}
