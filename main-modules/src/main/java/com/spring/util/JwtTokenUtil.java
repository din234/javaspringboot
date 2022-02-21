package com.spring.util;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
    @Value("${jwt.expirationMs}")
    public long JWT_TOKEN_VALIDITY;

    // Add secret
    @Value("${jwt.secret}")
    private String secret;

    private Claims getAllClaims(String token) throws Exception {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    // get username from jwt token
    public String getSubjectFromToken(String token) throws Exception {
        final Claims claims = this.getAllClaims(token);
        return claims != null ? claims.getSubject(): null;
    }
    public String getAudienceFromToken(String token) throws Exception {
        final Claims claims = this.getAllClaims(token);
        return claims != null ? claims.getAudience(): null;
    }

    //retrieve expiration date from jwt
    public Date getExpirationDate(String token) throws Exception {
        final Claims claims = this.getAllClaims(token);
        return claims != null ? claims.getExpiration(): null;
    }

    private Boolean isTokenExpired(String token) throws Exception {
        final Date expiration = getExpirationDate(token);
        return expiration == null || expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        // boi vi Claim la interface extend tu hashmap
        Map<String, Object> claims = new HashMap<>();
        String subject = userDetails.getUsername();
        return Jwts.builder().setClaims(claims).setSubject(subject)
//                .setAudience(userDetailService.findUsername(subject).getId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String refreshToken(String token) throws Exception {
        final Claims claims = this.getAllClaims(token);
        return Jwts.builder().setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) throws Exception {
        final String username = getSubjectFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}