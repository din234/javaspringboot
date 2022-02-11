package com.spring.controller.test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/test/hello")
public class Hello {
    @RequestMapping({"/hello1","/hello2"})
    public String Test(){
        return "Hello world";
    }

    // Embedded HTML
    @GetMapping({"/html"})
    public String temp() {
        return "<h3>INDEX</h3>";
    }

    @GetMapping({"/json"})
    public List<String> json() {
        return List.of("A","B");
    }
}
