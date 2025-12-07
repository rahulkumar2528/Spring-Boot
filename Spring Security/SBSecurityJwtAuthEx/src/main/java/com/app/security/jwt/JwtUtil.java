package com.app.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String jwtSecret;
	
	private SecretKey getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String getUserName(String token) {
		return getClaims(token).getSubject();
	}
	
	/**
	 * old version
	private Claims getClaims(String token) {
		return Jwts.parser()
		.setSigningKey(getKey())
		.parseClaimsJws(token)
		.getBody();
	}
	**/
	
	private Claims getClaims(String token) {
	    return Jwts.parserBuilder()
	            .setSigningKey(getKey())
	            .build()
	            .parseClaimsJws(token)
	            .getBody();
	}
	
	public String genrateToken(String subject) {
		Map<String, Object> claims = new HashMap<>();
		return genrateToken(claims, subject);
	}
	
	private String genrateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder()
		.setClaims(claims)
		.setSubject(subject)
		.setIssuer("SAMPLE TEST")
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10)))
		.signWith(getKey(), SignatureAlgorithm.HS256)
		.compact();
	}
}
