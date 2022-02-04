package com.spring.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.GenericFilterBean;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/test/filter")
public class Filter {

//    @Autowired
//    @Qualifier("springSecurityFilterChain")
//    private GenericFilterBean springSecurityFilterChain;

    @RequestMapping("/chainProxy")
    public ResponseEntity<?> getFilterChainProxy(){
//        FilterChainProxy filterChainProxy = (FilterChainProxy) springSecurityFilterChain;
//
//        List<SecurityFilterChain> filterChains = filterChainProxy.getFilterChains();
////        HashMap<Integer,String> map = new HashMap<Integer,String>();

        return new ResponseEntity("", HttpStatus.OK);
    }
}
