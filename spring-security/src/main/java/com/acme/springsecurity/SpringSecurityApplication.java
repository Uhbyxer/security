package com.acme.springsecurity;

import com.acme.springsecurity.domain.Role;
import com.acme.springsecurity.domain.User;
import com.acme.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

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
				.password(passwordEncoder.encode("password"))
				.authorities(Collections.singletonList(Role.ROLE_USER))
				.accountNonExpired(true)
				.accountNonLocked(true)
				.credentialsNonExpired(true)
				.enabled(true)
				.build());
		userRepository.save(User.builder()
				.username("admin")
				.password(passwordEncoder.encode("Password"))
				.authorities(Collections.singletonList(Role.ROLE_ADMIN))
				.accountNonExpired(true)
				.accountNonLocked(true)
				.credentialsNonExpired(true)
				.enabled(true)
				.build());

		userRepository.save(User.builder()
				.username("power")
				.password(passwordEncoder.encode("passworD"))
				.authorities(Collections.singletonList(Role.ROLE_POWER_USER))
				.accountNonExpired(true)
				.accountNonLocked(true)
				.credentialsNonExpired(true)
				.enabled(true)
				.build());

		System.out.println("============");
		System.out.println("find by username: " + userRepository.findByUsername("user"));
		System.out.println("find by username: " + userRepository.findByUsername("admin"));
		System.out.println("find by username: " + userRepository.findByUsername("power"));
	}

	//	@Autowired
//	public void configureGrobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		authenticationManagerBuilder
//		.inMemoryAuthentication()
//		.withUser("user")
//		.password("{noop}password") //withDefaultPasswordEncoder
//		.roles("ROLE_USER");
//	}
}
