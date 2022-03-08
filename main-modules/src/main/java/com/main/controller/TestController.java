package com.spring.controller;

import com.spring.service.test.TestServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")

//@CrossOrigin(origins = "http://localhost:8081") // Access-Control-Allow-Origin
public class TestController {
    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestServiceImpl testService;

    @PostMapping("/non-blocking")
    public DeferredResult<ResponseEntity<?>> sleepDefer(){
        DeferredResult<ResponseEntity<?>> output = new DeferredResult<>();
        Map<String,Object> response = new HashMap<>();
        try {
            Thread.sleep(1000);
            response.put("msg","Done Sleeping");
            output.setResult(new ResponseEntity(response,HttpStatus.OK));
        } catch (Exception e) {
            response.put("msg","Something wrong");
            output.setErrorResult(new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR));
        }
        return output;
    }

    @PostMapping("/blocking")
    public ResponseEntity blocking(){
        try {Thread.sleep(1000);} catch (Exception e) {};
        Map<String,Object> response = new HashMap<>();
        response.put("mgs","Done Sleeping");
        return new ResponseEntity(response,HttpStatus.OK);
    }


    @GetMapping({"/elastic"})
    public ResponseEntity elastic() {
        return testService.connect();
    }

    @PostMapping("/async")
    public void asyncTest(){
        testService.asyncService();
        logger.info("Goi sau");
    }
}

class Visitor {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}