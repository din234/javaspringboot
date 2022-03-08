package com.main.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.service.user.UserDetailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

//import org.springframework.security.access.expression.method.MethodSecurityExpressionRoot;

// https://stackoverflow.com/questions/48628389/how-to-configure-httpsecurity-for-this-situation-spring-boot

@Configuration
@EnableWebSecurity(debug = true)
//@EnableWebSecurity()
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // Kích hoạt @PreAuthorize
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    private AuthenticationEntryPointImpl authenticationEntryPoint;
    private UserDetailServiceImpl userDetailServiceImpl;
    private JwtRequestAuthenticationFilter jwtRequestAuthenticationFilter;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurityConfig(
            AuthenticationEntryPointImpl authenticationEntryPoint,
            UserDetailServiceImpl userDetailServiceImpl,
            JwtRequestAuthenticationFilter jwtRequestAuthenticationFilter,
            PasswordEncoder passwordEncoder) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.userDetailServiceImpl = userDetailServiceImpl;
        this.jwtRequestAuthenticationFilter = jwtRequestAuthenticationFilter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authManBuilder) throws Exception{
        authManBuilder.userDetailsService(userDetailServiceImpl).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST,"/user/auth/register","/user/auth/login").permitAll()
                .antMatchers("/login","/test/**","/index/**","/webjars/**","/js/**").permitAll()
                .antMatchers("/swagger/**","/swagger/api-docs","/swagger/swagger-ui.html/**").permitAll()

                .antMatchers("/server/import").access("hasIpAddress('127.0.0.1')")
                .antMatchers("/server/add/**").access("hasIpAddress('127.0.0.1')")

                .anyRequest().authenticated()
//                .anyRequest().permitAll()
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
        http.addFilterBefore(jwtRequestAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//            http.addFilter(new JwtRequestAuthenticationFilter(authenticationManagerBean()));


//        super.configure(http);
    }



//    // TESTING
//    private void logSettings(HttpSecurity http){
//        String log = http.toString();
//        logger.info("SECURITY CONFIG: " + http);
//    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return (request, response, accessDeniedException) -> {
            Map<String,Object> res = new HashMap<>();
            res.put("error: ","Forbidden");

            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            ServletOutputStream out = response.getOutputStream();
            new ObjectMapper().writeValue(out,res);
            out.flush();
        };
    }
}