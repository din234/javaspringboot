package com.spring.controller.localProvider;

import com.spring.model.user.User;
import com.spring.service.localProvider.LocalProviderService;
import com.spring.service.user.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/server")
//@PreAuthorize("hasIpAddress('127.0.0.1')") // Đã config
public class LocalProviderController {

    private final LocalProviderService localProviderService;
    private final UserDetailServiceImpl userDetailServiceImpl;

    @Autowired
    public LocalProviderController (
            LocalProviderService localProviderService,
            UserDetailServiceImpl userDetailServiceImpl){
        this.localProviderService = localProviderService;
        this.userDetailServiceImpl = userDetailServiceImpl;
    }

    // import
    @RequestMapping(value = "/import")
    public ResponseEntity getDataExcel() throws Exception{
        return localProviderService.run();
    }

    // TEST
    @RequestMapping(value = "/add")
    @ResponseBody
    public ResponseEntity redirectData(@RequestBody @Valid User user) throws Exception{
        if (userDetailServiceImpl.saveUser(user) != null){
            return new ResponseEntity(user,HttpStatus.CREATED);
        }
        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    // GET METHOD
    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public ResponseEntity getLog(){
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
