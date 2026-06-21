package com.gabriel.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class TokenProvider {
    @Value("${JWT_EXPIRATION}")
    private long expirationTime;

    @Value("${JWT_SECRET}")
    private String secretKey;


    public String generateToken(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();

        return buildToken(user.getUsername());
    }

    public String buildToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSecretKey())
                .compact();
    }
    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public   String getUsername(String token){
        return getClaims(token).getSubject();
    }

    public boolean isTokenValid(String token){
        try{
            getClaims(token);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}

