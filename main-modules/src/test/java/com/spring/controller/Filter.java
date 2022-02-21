package com.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
