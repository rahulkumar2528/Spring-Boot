package com.app.emp.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	@Value("${jwt.secret.key}")
	private String jwtSecretKey;

	public String genrateToken(UserDetails userDetails) {
		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000*60*30))
				.signWith(Keys.hmacShaKeyFor(jwtSecretKey.getBytes()))
				.compact();
	}
	
	private Claims extractClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(jwtSecretKey.getBytes())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	public String extractUserName(String token) {
		return extractClaims(token).getSubject();
	}
	
	public boolean isTokenExpired(String token) {
		return extractClaims(token).getExpiration().before(new Date());
	}
	
	public boolean validatToken(String token, UserDetails userDetails) {
		return extractUserName(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
	}
}
