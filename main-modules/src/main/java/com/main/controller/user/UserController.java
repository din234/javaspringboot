package com.main.controller.user;

import com.spring.model.user.UserSearch;
import com.main.service.user.UserDetailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserDetailServiceImpl userDetailServiceImpl;

    @Autowired
    public UserController (
            UserDetailServiceImpl userDetailServiceImpl){
        this.userDetailServiceImpl = userDetailServiceImpl;
    }

    // POST MAPPING

    @PostMapping(value = "/addAuthority")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    public ResponseEntity addAuthority(@RequestBody AuthorityForm auth){
        Boolean result = userDetailServiceImpl.addAuthority(auth.getUsername(),auth.getAuth());
        return new ResponseEntity(result, HttpStatus.CREATED);
    }

    @PostMapping(value = "/deleteAuthority")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity deleteAuthority(@RequestBody AuthorityForm auth){
        Boolean result = userDetailServiceImpl.deleteAuthority(auth.getUsername(),auth.getAuth());
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('STAFF')")
    public ResponseEntity<?> findAll() {
        List<UserSearch> users = userDetailServiceImpl.findAllUser();
        return new ResponseEntity(users,HttpStatus.OK);
    }

    // GET MAPPING
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public ResponseEntity<?> searchUsername(@RequestParam String keyword){
//        List<UserSearch> users = userDetailServiceImpl.searchUser(keyword);
        UserSearch users = userDetailServiceImpl.findUsername(keyword);
        return new ResponseEntity(users,HttpStatus.OK);
    }

}


class AuthorityForm {
    private String username;
    private String auth;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}