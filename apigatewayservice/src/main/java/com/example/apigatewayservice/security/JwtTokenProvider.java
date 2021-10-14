package com.example.apigatewayservice.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class JwtTokenProvider implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000;

	private String jwtSecret = "secret";
	
	
	

	public Claims getClaims(final String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
			return body;
		} catch (Exception e) {
			System.out.println(e.getMessage() + " => " + e);
		}
		return null;
	}


	

	

	

	


		
		public void validateToken(final String token) throws Exception {
			System.out.println("allclaims2");
			
			
			try {
				Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
			
		} catch (SignatureException ex) {
			System.out.println("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			System.out.println("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			System.out.println("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			System.out.println("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			System.out.println("JWT claims string is empty.");
		}
			

	}
		
		
		
}

