package com.spring.upwork.controller;

import com.spring.upwork.service.UpworkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpworkController {

    private final UpworkServiceImpl upworkService;

    @Autowired
    public UpworkController(UpworkServiceImpl upworkService) {
        this.upworkService = upworkService;
    }
}
