package com.spring.controller.test;


import com.spring.model.test.TestUser;
import com.spring.service.test.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/test")
public class TestController {

    @Autowired
    private TestService testService;


    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

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


    @RestController
    class dbElastic {

    }

    @GetMapping({"/db"})
    public List<String> elastic() {
        testService.checkDB();
        return List.of("elastic","ber");
    }

    @PostMapping("post")
    public ResponseEntity login(@RequestBody TestUser testUser){
        LOGGER.info("Post triggered");
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}


class