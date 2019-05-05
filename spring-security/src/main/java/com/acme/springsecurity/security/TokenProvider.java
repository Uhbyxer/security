package com.acme.springsecurity.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TokenProvider {

	private final UserDetailsService userDetailsService;
	private final String jwtSecret;
	private final long jwtTTL;

	public TokenProvider(UserDetailsService userDetailsService, String jwtSecret, long jwtTTL) {
		this.userDetailsService = userDetailsService;
		this.jwtSecret = jwtSecret;
		this.jwtTTL = jwtTTL;
	}

	public String createToken(String username, Collection<? extends GrantedAuthority> roles) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("roles", getRoleNames(roles));

		Date now = new Date();
		Date expiry = new Date(now.getTime() + jwtTTL);

		String token = Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(expiry)
				.signWith(SignatureAlgorithm.HS256, jwtSecret).compact();

		return token;
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));

		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}


	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

	private String getUsername(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateToken(String token) {
		Jws<Claims> claimsJws = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
		try {
			boolean valid = !claimsJws.getBody().getExpiration().before(new Date());
			log.info("Token is valid: {}", valid);

			return valid;
		} catch (Exception e) {
			log.error("Fail to parse token", e);
			return false;
			//throw new TokenException("Fail to parse token");
		}
	}

	private List<String> getRoleNames(Collection<? extends GrantedAuthority> userRoles) {
		return userRoles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
	}
}
