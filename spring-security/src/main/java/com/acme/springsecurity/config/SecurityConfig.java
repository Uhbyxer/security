package com.acme.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableWebSecurity //switch-off out of the box security
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;
	private TokenConfig tokenConfig;
	private PasswordEncoder passwordEncoder;


	@Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Autowired
	public void setTokenConfig(TokenConfig tokenConfig) {
		this.tokenConfig = tokenConfig;
	}

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//				.antMatchers(GET, "/hello").permitAll()
//				.antMatchers(GET, "/admin/users").permitAll() //temporary
//				.anyRequest().authenticated().and().httpBasic();

		http
				.httpBasic().disable()
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers(GET, "/hello").permitAll()
				.antMatchers("/auth/login").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
//				.antMatchers("/admin/**").authenticated()
				.anyRequest().authenticated()
				.and()
				.apply(tokenConfig);

	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	//@Autowired
	//public void configureGrobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		authenticationManagerBuilder
//				.inMemoryAuthentication()
//				.withUser("user")
//				.password("{noop}password") //withDefaultPasswordEncoder
//				.roles("ROLE_USER");

	//	authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

	//}
}
