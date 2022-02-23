package com.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    Logger logger = LoggerFactory.getLogger(TestService.class);

    @Async
    public void asyncService(){
        try {
            Thread.sleep(2000);
            logger.info("Goi truoc");
        } catch (Exception e){}
    }
}
