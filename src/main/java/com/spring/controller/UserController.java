package com.spring.controller;

import com.spring.config.jwt.JwtTokenUtil;
import com.spring.model.user.User;
import com.spring.model.user.JwtResponse;
import com.spring.service.user.UserDetailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping(path = "/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserDetailServiceImpl userDetailServiceImpl;

    @Autowired
    public UserController (
            AuthenticationManager authenticationManager,
            JwtTokenUtil jwtTokenUtil,
            UserDetailServiceImpl userDetailServiceImpl){
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailServiceImpl = userDetailServiceImpl;
    }
//    private void test(Students.LoginRequest loginRequest){
//        try {
//            logger.info("test");
//            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                    loginRequest.getUsername(),
//                    loginRequest.getPassword()
//            );
//            authenticationManager.authenticate(authToken);
//        } catch (Exception e){
//            logger.error("ERROR");
//            throw e;
//        }
//    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Return jwt token
        final String token = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());
        return new ResponseEntity(new JwtResponse(token),HttpStatus.OK);
//        return new ResponseEntity("",HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getUsers(@RequestBody @Valid User user) throws Exception {
        logger.info("New user added!");
        userDetailServiceImpl.saveUser(user);
        return new ResponseEntity(user,HttpStatus.CREATED);
    }

//    @RequestMapping(value = "/username", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity getUsername(){
//        return new ResponseEntity(userDetailServiceImp.findUsername("A"),HttpStatus.OK);
//    }

    @RequestMapping(value = "findAll")
    public ResponseEntity<?> findAll() throws Exception{
        List<User> users = userDetailServiceImpl.findAllUser();
        return new ResponseEntity(users,HttpStatus.OK);
    }



    // TESTING
    @PostMapping("/post")
    public ResponseEntity postUser(@RequestBody User user) throws Exception{
        logger.info("User post triggered");
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
