package com.spring.controller;

import com.spring.model.User;
import com.spring.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    // Injected beans
    @Autowired
    private UserService userService;



    @RequestMapping(value = "/get")
    public List<User> getUsers() {
        return userService.getUsers();
    }



//    @PostMapping("post")
//    public ResponseEntity postUser(@RequestBody User user) throws Exception{
//        LOGGER.info("User post triggered");
//        return new ResponseEntity(HttpStatus.ACCEPTED);
//    }


    // TESTING

//    @RequestMapping(value = "/auth", method = RequestMethod.POST)
//    public List<Students> getStudents() {
//        return usersService.getStudents();
//    }


}
