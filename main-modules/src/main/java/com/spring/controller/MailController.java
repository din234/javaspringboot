package com.spring.controller;

import com.spring.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * https://shareprogramming.net/huong-dan-su-dung-pathvariable-trong-spring-boot/
 */
@RestController
@RequestMapping(value="/mail/api")
public class MailController {
    private static Logger logger = LoggerFactory.getLogger(MailController.class);

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/simplemail/{mail}", method = RequestMethod.GET)
    public ResponseEntity sendSimpleMail(@PathVariable String mail) throws MailException {
        mailService.sendSimpleMail(mail,"Test","New mail send sucessful");
        return new ResponseEntity("Please check your inbox", HttpStatus.OK);
    }

}
