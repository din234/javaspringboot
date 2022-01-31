package com.spring.controller.test;

import com.spring.model.test.TestUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/test/post")
public class Post {
    private static final Logger LOGGER = LoggerFactory.getLogger(Post.class);

    @PostMapping("/request")
    public ResponseEntity request(@RequestBody TestUser testUser){
        LOGGER.info("Post triggered");
        return new ResponseEntity(testUser,HttpStatus.ACCEPTED);
    }


    @PostMapping("/response")
    @ResponseBody
    public ResponseEntity response(@RequestBody TestUser testUser){
        return new ResponseEntity(testUser,HttpStatus.CREATED);
    }


}