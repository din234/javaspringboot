package com.main.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.util.jwt.JwtTokenUtil;
import com.spring.model.user.form.LoginRequest;
import com.spring.model.user.form.RegistrationRequest;
import com.spring.model.user.User;
import com.main.model.security.JwtResponse;
import com.main.service.user.UserDetailServiceImpl;
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

@RestController
@RequestMapping("/user/auth")
public class AuthenticationController {
    private final ObjectMapper objectMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailServiceImpl userDetailServiceImpl;

//    private final MailService mailService;


    @Autowired
    public AuthenticationController (
            ObjectMapper objectMapper,
            AuthenticationManager authenticationManager,
            JwtTokenUtil jwtTokenUtil,
            UserDetailServiceImpl userDetailServiceImpl
//            MailService mailService
    ){
        this.objectMapper = objectMapper;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailServiceImpl = userDetailServiceImpl;
//        this.mailService = mailService;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody @Valid LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        // Return jwt token
        final String token = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());

        return new ResponseEntity(new JwtResponse(token), HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity reigisterUser(@RequestBody @Valid RegistrationRequest request) throws Exception {
//        Boolean mailVaild = mailService.mailValidator(request.getEmail());
//
//        if (userDetailServiceImpl.saveUser(convertToUser(request)) != null && mailVaild){
//            return new ResponseEntity(request,HttpStatus.CREATED);
//        }
        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    private User convertToUser(RegistrationRequest request){
        return objectMapper.convertValue(request,User.class);
    }
}
