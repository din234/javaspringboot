package com.spring.controller.test;

import com.spring.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test/elastic")
public class Elastic {

    @Autowired
    private TestService testService;

    @GetMapping({"/connect"})
    public ResponseEntity elastic() {
        return testService.connect();
    }
}
