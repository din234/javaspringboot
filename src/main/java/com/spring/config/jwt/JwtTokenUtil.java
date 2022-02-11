package com.spring.config.jwt;

import java.io.Serializable;
import java.util.Date;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.xml.bind.*;

// import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import com.spring.service.user.UserDetailServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    @Autowired
    UserDetailServiceImpl userDetailService;

    @Value("${jwt.expirationMs}")
    public long JWT_TOKEN_VALIDITY;

    // Add secret
    @Value("${jwt.secret}")
    private String secret;

    private Claims getAllClaims(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e){
            logger.error("Expired token!: "+ e.getClaims());
//            return e.getClaims();
        } catch (Exception e){
            logger.error("Could not get all claims from token");
//            e.printStackTrace();
        }
        return claims;
    }


    // get username from jwt token
    public String getSubjectFromToken(String token) {
        final Claims claims = this.getAllClaims(token);
        return claims != null ? claims.getSubject(): null;
    }
    public String getAudienceFromToken(String token) {
        final Claims claims = this.getAllClaims(token);
        return claims != null ? claims.getAudience(): null;
    }

    //retrieve expiration date from jwt
    public Date getExpirationDate(String token) {
        final Claims claims = this.getAllClaims(token);
        return claims != null ? claims.getExpiration(): null;
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDate(token);
        return expiration == null || expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        // boi vi Claim la interface extend tu hashmap
        Map<String, Object> claims = new HashMap<>();
        String subject = userDetails.getUsername();
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setAudience(userDetailService.findUsername(subject).get(0).getId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String refreshToken(String token) {
        final Claims claims = this.getAllClaims(token);
        return Jwts.builder().setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getSubjectFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
