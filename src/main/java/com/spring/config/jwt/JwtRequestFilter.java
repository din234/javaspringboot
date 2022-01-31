package com.spring.config.jwt;

import com.spring.service.user.UserDetailServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailServiceImp userDetailServiceImp;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Authorization: bearer <token>
        String token = getBearerToken(request);
        String username = null;

        logger.info("The token is:" + token);
        username = jwtTokenUtil.getUsername(token);

        UserDetails userDetails = userDetailServiceImp.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//        try {
//        } catch (Exception e){
//            logger.error("Unable to get JWT");
//        }
        filterChain.doFilter(request,response);
    }


    // Lấy token từ header "Authorization: Bearer <token>"
    private String getBearerToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){
            return authHeader.substring("Bearer ".length());
        }

        logger.warn("JWT khong bat dau tu \"Bearer \"");
        return null;
    }

}
