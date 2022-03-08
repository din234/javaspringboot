package com.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/index")
public class IndexController {
    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/")
    public String index(){
        return "main";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/test")
    public String test(){
        return "manyReq";
    }
}
