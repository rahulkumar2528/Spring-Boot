package com.app;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Test {

	public static void main(String[] args) {
		String secret = "B#2jhgb@(&12B#v#n#%Khh6gfdd";
		
		String token = Jwts.builder()
		.setId("76355") //user id
		.setSubject("Rahul Kumar") // user name
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(2)))
		.setIssuer("RTech")
		.signWith(SignatureAlgorithm.HS256, secret.getBytes())
		.compact();
		
		System.out.println(token);

		Claims c = Jwts
		.parser()
		.setSigningKey(secret.getBytes())
		.parseClaimsJws(token)
		.getBody();
		
		System.out.println(c.getSubject());
		System.out.println(c.getIssuer());
		System.out.println(c.getExpiration());
	}

}
