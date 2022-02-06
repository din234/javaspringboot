package com.spring.config.jwt;

import com.spring.service.user.UserDetailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;


// https://stackoverflow.com/questions/48628389/how-to-configure-httpsecurity-for-this-situation-spring-boot

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    private AuthenticationEntryPointImpl authenticationEntryPoint;
    private UserDetailServiceImpl userDetailServiceImpl;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurityConfig(
            AuthenticationEntryPointImpl authenticationEntryPoint,
            UserDetailServiceImpl userDetailServiceImpl,
            PasswordEncoder passwordEncoder) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.userDetailServiceImpl = userDetailServiceImpl;
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
//        http.httpBasic().disable();
//        http.authorizeRequests((requests) -> {
//            ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)requests.anyRequest()).authenticated();
//        });
//        http.formLogin();
        http.csrf().disable().authorizeRequests()
//                .antMatchers("/auth").permitAll()
//                .antMatchers("/add").permitAll()        // FOR TESTING
                .anyRequest().permitAll()
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.addFilterBefore(new JwtRequestAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
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
//        logger.error("STUCK HERE");
        return super.authenticationManagerBean();
    }
}