package com.spring.controller.local;

import com.spring.model.course.Course;
import com.spring.model.location.Country;
import com.spring.model.location.Region;
import com.spring.model.security.Authority;
import com.spring.model.user.User;
import com.spring.service.localProvider.LocalProviderPostService;
import com.spring.service.user.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/server/add")
//@PreAuthorize("hasIpAddress('127.0.0.1')") // Đã config
public class LocalProviderPostController {

    private final LocalProviderPostService localProviderPostService;
    private final UserDetailServiceImpl userDetailServiceImpl;

    @Autowired
    public LocalProviderPostController(
            LocalProviderPostService localProviderAddService,
            UserDetailServiceImpl userDetailServiceImpl){
        this.localProviderPostService = localProviderAddService;
        this.userDetailServiceImpl = userDetailServiceImpl;
    }
    // TEST
    @PostMapping(value = "/user")
    @ResponseBody
    public ResponseEntity localAddUser(@RequestBody @Valid User user) {
        return new ResponseEntity(userDetailServiceImpl.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping(value = "/authority")
    @ResponseBody
    public ResponseEntity localAddAuthority(@RequestBody @Valid Authority authority) {
        return new ResponseEntity(localProviderPostService.addAuthority(authority), HttpStatus.CREATED);
    }

    @PostMapping(value = "/region")
    public ResponseEntity localAddRegion(@RequestBody @Valid Region region){
        return new ResponseEntity(localProviderPostService.addRegion(region),HttpStatus.CREATED);
    }

    @PostMapping(value = "/country")
    @ResponseBody
    public ResponseEntity localAddCountry(@RequestBody @Valid Country country) {
        return new ResponseEntity(localProviderPostService.addCountry(country), HttpStatus.CREATED);
    }


    @PostMapping(value = "/location")
    @ResponseBody
    public ResponseEntity localAddLocation(@RequestBody @Valid Country country) {
        return new ResponseEntity(localProviderPostService.addLocation(country), HttpStatus.CREATED);
    }

    @PostMapping(value = "/course")
    @ResponseBody
    public ResponseEntity localAddCourse(@RequestBody @Valid Course course) {
        return new ResponseEntity(localProviderPostService.addCourse(course), HttpStatus.CREATED);
    }
}
