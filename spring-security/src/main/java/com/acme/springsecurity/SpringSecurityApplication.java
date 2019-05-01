package com.acme.springsecurity;

import com.acme.springsecurity.domain.Role;
import com.acme.springsecurity.domain.User;
import com.acme.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import java.util.Collections;

@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {

	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		userRepository.deleteAll();
		userRepository.save(User.builder()
				.username("user")
				.password("{noop}password")
				.authorities(Collections.singletonList(Role.USER))
				.accountNonExpired(true)
				.accountNonLocked(true)
				.credentialsNonExpired(true)
				.enabled(true)
				.build());

		System.out.println("============");
		System.out.println("find by username: " + userRepository.findByUsername("user"));
	}

	//	@Autowired
//	public void configureGrobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		authenticationManagerBuilder
//		.inMemoryAuthentication()
//		.withUser("user")
//		.password("{noop}password") //withDefaultPasswordEncoder
//		.roles("USER");
//	}
}
