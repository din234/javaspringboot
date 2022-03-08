package com.main.api;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


public class jwt {
    private static Logger logger = LoggerFactory.getLogger(jwt.class);


    @Test
    public void localThreadSecurityContext(){
        SecurityContext sec = SecurityContextHolder.getContext();
        Authentication auth = sec.getAuthentication();
//        auth.getCredentials().toString();
    }




}