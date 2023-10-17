package com.example.securityjwtpractice.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public String extractUsername(String token) {
        return null;
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())  // need to generate or decode token
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
