package com.spring.controller;

import com.spring.config.jwt.JwtTokenUtil;
import com.spring.model.Students;
import com.spring.model.jwt.User;
import com.spring.service.user.UserDetailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailServiceImpl userDetailServiceImp;


    @PostMapping("/auth")
    public ResponseEntity login(@RequestBody Students.LoginRequest loginRequest){
        // Xac thuc username va password
        test(loginRequest);

//        logger.info("CONTROLLER TRIGGERED" + authToken);
        final UserDetails userDetails = userDetailServiceImp.loadUserByUsername(loginRequest.getUsername());


//        UserDetails userDetails = userDetailServiceImp.loadUserByUsername("dinhnkp");
        String jwt = jwtTokenUtil.generateToken(userDetails);

        return new ResponseEntity(jwt,HttpStatus.OK);
    }

    private void test(Students.LoginRequest loginRequest){
        try {
            logger.info("test");
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            );
            authenticationManager.authenticate(authToken);
        } catch (Exception e){
            logger.error("ERROR");
            throw e;
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getUsers(@RequestBody User user) throws Exception {
        logger.info("New user added!");
        userDetailServiceImp.saveUser(user);
        return new ResponseEntity(user,HttpStatus.CREATED);
    }

//    @RequestMapping(value = "/username", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity getUsername(){
//        return new ResponseEntity(userDetailServiceImp.findUsername("A"),HttpStatus.OK);
//    }

    @RequestMapping(value = "findAll")
    public ResponseEntity<?> findAll() throws Exception{
        List<User> users = userDetailServiceImp.findAllUser();
        return new ResponseEntity(users,HttpStatus.OK);
    }



    // TESTING

//    @RequestMapping(value = "/auth", method = RequestMethod.POST)
//    public List<Students> getStudents() {
//        return usersService.getStudents();
//    }

//    @RequestMapping("/login")
//    public String getJwt(){
//        UserDetails userDetails = userDetailServiceImp.loadUserByUsername("dinhnkp");
//        String jwt = jwtTokenUtil.generateToken(userDetails);
//        return "";
//    }


    @PostMapping("/post")
    public ResponseEntity postUser(@RequestBody User user) throws Exception{
        logger.info("User post triggered");
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
