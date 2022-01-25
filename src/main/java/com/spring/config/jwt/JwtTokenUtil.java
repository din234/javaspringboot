//package com.spring.config.jwt;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Component
//public class JwtTokenUtil implements Serializable {
//
//    @Value("${jwt.expirationMs}")
//    public static long JWT_TOKEN_VALIDITY;
//
//    // Add secret
//    @Value("${jwt.secret}")
//    private String secret;
//
//    private Claims getAllClaims (String token){
//        return Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody();
//    }
//
//    // get Claims with set secret, token and paste to return to claims format
//
//    private <T> T getClaim(String token, Function<Claims,T> resolver){
//        final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//        return resolver.apply(claims);
//    }
//
//    // get username from jwt token
//    public String getUsername(String token){
//        return getClaim(token,Claims::getSubject);
//    }
//
//    //retrieve expiration date from jwt
//    public Date getExpirationDate(String token){
//        return getClaim(token, Claims::getExpiration);
//    }
//
//    private Boolean isTokenExpired(String token) {
//        final Date expiration = getExpirationDate(token);
//        return expiration.before(new Date());
//    }
//
//    public String generateToken(UserDetails userDetails){
//        // boi vi Claim la interface extend tu hashmap
//        Map<String, Object> claims = new HashMap<>();
//        String subject = userDetails.getUsername();
//        return Jwts.builder().setClaims(claims).setSubject(subject)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
//                .signWith(SignatureAlgorithm.HS512,secret).compact();
//    }
//
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = getUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//}
