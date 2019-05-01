package com.acme.springsecurity.service;

import com.acme.springsecurity.domain.User;
import com.acme.springsecurity.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		return User.builder()
//				.username(username)
//				.password("{noop}password")
//				.authorities(Collections.singletonList(Role.USER))
//				.accountNonExpired(true)
//				.accountNonLocked(true)
//				.credentialsNonExpired(true)
//				.enabled(true)
//				.build();

		User user = userRepository.findByUsername(username);
		log.info("User is: {}", user);

		if (user == null) {
			throw new UsernameNotFoundException("Bad user: " + username);
		}
		return user;
	}
}
