package com.main.controller.local;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyController {
    private static Logger logger = LoggerFactory.getLogger(PropertyController.class);

//    @Value("#{new Integer(${sync.min})}")
//    public void setInterval(Integer syncMin){
//        Integer error = Constant.CHECK_MODIFIED_ERROR;
//        Constant.CHECK_MODIFIED_AFTER_MS = (syncMin+error) * 60 * 1000;
//    }


}
