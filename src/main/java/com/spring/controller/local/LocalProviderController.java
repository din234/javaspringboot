package com.spring.controller.local;

//import com.spring.model.main.User;
import com.spring.model.jpa.User;
import com.spring.service.localProvider.LocalProviderService;
import com.spring.service.user.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    // GET METHOD
    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public ResponseEntity getLog(){
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
