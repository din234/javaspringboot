package com.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/html")
public class Html {
    @GetMapping("/")
    public String index(){
        return "TEST MAIN INDEX";
    }

}
