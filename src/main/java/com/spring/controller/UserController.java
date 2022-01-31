package com.spring.controller;

import com.spring.config.jwt.JwtTokenUtil;
import com.spring.model.jwt.LoginRequest;
import com.spring.model.jwt.User;
import com.spring.service.user.UserDetailServiceImp;
import com.spring.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
// @RequestMapping(path = "/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    // Injected beans
    @Autowired
    private UserService userService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailServiceImp userDetailServiceImp;



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest){
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUsername(),
//                        loginRequest.getPassword())
//        );

        UserDetails userDetails = userDetailServiceImp.loadUserByUsername("dinhnkp");
        String jwt = jwtTokenUtil.generateToken(userDetails);

        return new ResponseEntity(jwt,HttpStatus.OK);
    }








    // TESTING

//    @RequestMapping(value = "/auth", method = RequestMethod.POST)
//    public List<Students> getStudents() {
//        return usersService.getStudents();
//    }

    @RequestMapping("/login")
    public String getJwt(){
        UserDetails userDetails = userDetailServiceImp.loadUserByUsername("dinhnkp");
//        String jwt = jwtTokenUtil.generateToken(userDetails);
        return "";
    }

    @RequestMapping(value = "/get")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/post")
    public ResponseEntity postUser(@RequestBody User user) throws Exception{
        LOGGER.info("User post triggered");
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
