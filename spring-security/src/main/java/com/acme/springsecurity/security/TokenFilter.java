package com.acme.springsecurity.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class TokenFilter extends GenericFilterBean {

	private final TokenProvider tokenProvider;

	public TokenFilter(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {

		System.out.println("*****************************");

		String token = tokenProvider.resolveToken((HttpServletRequest) servletRequest);
		System.out.println(token);


		if (token != null && tokenProvider.validateToken(token)) {
			Authentication authentication = tokenProvider.getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			System.out.println("authentication: " + SecurityContextHolder.getContext().getAuthentication());
		}


		filterChain.doFilter(servletRequest, servletResponse);
	}
}
