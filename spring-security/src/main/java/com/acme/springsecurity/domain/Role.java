package com.acme.springsecurity.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	ROLE_USER, ROLE_ADMIN, ROLE_POWER_USER;

	@Override
	public String getAuthority() {
		return name();
	}
}
