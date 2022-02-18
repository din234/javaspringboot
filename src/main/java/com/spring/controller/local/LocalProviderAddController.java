package com.spring.controller.local;


import com.spring.model.jpa.Authority;
import com.spring.model.jpa.User;
import com.spring.service.localProvider.LocalProviderService;
import com.spring.service.user.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/server/add")
//@PreAuthorize("hasIpAddress('127.0.0.1')") // Đã config
public class LocalProviderAddController {

    private final LocalProviderService localProviderService;
    private final UserDetailServiceImpl userDetailServiceImpl;

    @Autowired
    public LocalProviderAddController (
            LocalProviderService localProviderService,
            UserDetailServiceImpl userDetailServiceImpl){
        this.localProviderService = localProviderService;
        this.userDetailServiceImpl = userDetailServiceImpl;
    }
    // TEST
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity localAddUser(@RequestBody @Valid User user) throws Exception{
        if (userDetailServiceImpl.saveUser(user) != null){
            return new ResponseEntity(user, HttpStatus.CREATED);
        }
        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/authority",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity localAddAuthority(@RequestBody @Valid Authority authority) throws Exception{
        return localProviderService.addAuthority(authority);
    }
}
