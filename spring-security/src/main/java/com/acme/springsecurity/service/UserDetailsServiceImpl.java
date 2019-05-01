package com.acme.springsecurity.service;

import com.acme.springsecurity.domain.Role;
import com.acme.springsecurity.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return User.builder()
				.username(username)
				.password("{noop}password")
				.authorities(Collections.singletonList(Role.USER))
				.accountNonExpired(true)
				.accountNonLocked(true)
				.credentialsNonExpired(true)
				.enabled(true)
				.build();
	}
}
