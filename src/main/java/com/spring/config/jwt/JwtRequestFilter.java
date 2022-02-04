package com.spring.config.jwt;

import com.spring.service.user.UserDetailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailServiceImpl userDetailServiceImp;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Authorization: bearer <token>
        String jwt = getBearerToken(request);

        try {
            String username = jwtTokenUtil.getUsername(jwt);
            if (StringUtils.hasText(jwt) ){

            }
        } catch (Exception e) {

        }
        logger.info("The token is:" + jwt);



         logAllHeader(request);
//        username = jwtTokenUtil.getUsername(token);
//
//        UserDetails userDetails = userDetailServiceImp.loadUserByUsername(username);
//
//        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
//                userDetails, null, userDetails.getAuthorities()
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(auth);
//        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////        try {
////        } catch (Exception e){
////            logger.error("Unable to get JWT");
////        }

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

    // TESTING
    private void logAllHeader(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            String requestHeader = "Request Header: \n";
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerBody = request.getHeader(headerName);
                requestHeader += "\t"+headerName+": "+headerBody+"\n";
            }
            logger.info(requestHeader);
        }
    }

}
