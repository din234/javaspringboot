package com.spring.config.jwt;

import com.spring.service.user.UserDetailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
public class JwtRequestAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtRequestAuthenticationFilter.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

//    private AuthenticationManager authenticationManager;
//    public JwtRequestAuthenticationFilter(
//            AuthenticationManager authenticationManager){
//        this.authenticationManager = authenticationManager;
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Authorization: bearer <token>
        String token = getBearerToken(request);
        String username = jwtTokenUtil.getSubjectFromToken(token);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(username);

        if (username != null && securityContext.getAuthentication() == null && userDetails != null) {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            securityContext.setAuthentication(auth);
            logger.info("Logged in as: " + username);
        }
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



//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//        logger.info("Test: do fdlfjei");
//        String jwt = getBearerToken((HttpServletRequest) request);
//        try {
//            String username = jwtTokenUtil.getUsername(jwt);
//            if (StringUtils.hasText(jwt) ){
//
//            }
//        } catch (Exception e) {
//
//        }
//        logger.info("The token is:" + jwt);
////        filterChain.doFilter(request,response);
//        super.doFilter(request, response,filterChain);
//
//    }


//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        logger.info("Authenticateing");
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("A","123",new ArrayList<>());
////        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        return authenticationManager.authenticate(authenticationToken);
//    }
