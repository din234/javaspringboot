package com.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class TestService {
    private static final Logger logger = LoggerFactory.getLogger(TestService.class);

    private final RestTemplate restTemplate;

    public TestService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    public ResponseEntity sleepFor() throws InterruptedException{
        Thread.sleep(10000L);
//        logger.info("DONE SLEEEP");
        return new ResponseEntity("Done sleep!", HttpStatus.OK);
    }



    @Async
    public CompletableFuture<User> findUser(String user) throws InterruptedException {
        logger.info("Looking up " + user);
        String url = String.format("https://api.github.com/users/%s", user);
        User results = restTemplate.getForObject(url, User.class);
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(10000L);
        return CompletableFuture.completedFuture(results);
    }
}
