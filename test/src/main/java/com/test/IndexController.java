package com.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String mainIndex(){
        return "main";
    }

    @GetMapping("/login")
    public String loginIndex(){
        return "login";
    }
}