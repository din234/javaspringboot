package com.spring.controller;

import com.spring.config.jwt.JwtTokenUtil;
import com.spring.model.user.Login;
import com.spring.model.user.User;
import com.spring.model.user.JwtResponse;
//import com.spring.service.test.CacheService;
import com.spring.service.user.UserDetailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailServiceImpl userDetailServiceImpl;

    @Autowired
    public UserController (
            AuthenticationManager authenticationManager,
            JwtTokenUtil jwtTokenUtil,
            UserDetailServiceImpl userDetailServiceImpl){
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailServiceImpl = userDetailServiceImpl;
    }

    // POST MAPPING
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody Login login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Return jwt token
        final String token = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());

        return new ResponseEntity(new JwtResponse(token),HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity reigisterUser(@RequestBody @Valid User user) throws Exception {
        if (userDetailServiceImpl.saveUser(user) != null){
            logger.info("New user added!");
            return new ResponseEntity(user,HttpStatus.CREATED);
        }
        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    public ResponseEntity addRoleToUser(){
        logger.info("LOG");
        return new ResponseEntity(null, HttpStatus.OK);
    }

    // GET MAPPING
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public ResponseEntity<?> searchUsername(@RequestParam String keyword){
        List<User> users = userDetailServiceImpl.searchUser(keyword);
        return new ResponseEntity(users,HttpStatus.OK);
    }

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        List<User> users = userDetailServiceImpl.findAllUser();
        return new ResponseEntity(users,HttpStatus.OK);
    }

}
