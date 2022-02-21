package com.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@SpringBootApplication
public class TestIndex {
    private static Logger logger = LoggerFactory.getLogger(TestIndex.class);

    public static void main(String[] args){
        SpringApplication.run(TestIndex.class);
    }

    @Autowired
    private TestService lookupService;
    

//    @Bean
//    CommandLineRunner run(){
//        return args -> {
//            long start = System.currentTimeMillis();
//
//            CompletableFuture<User> t1 = lookupService.findUser("PivotalSoftware");
//            CompletableFuture<User> t2 = lookupService.findUser("CloudFoundry");
//            CompletableFuture<User> t3 = lookupService.findUser("Spring-Projects");
//
//            CompletableFuture.allOf(t1,t2,t3);
//            logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
//            logger.info("--> " + t1.get());
//            logger.info("--> " + t2.get());
//            logger.info("--> " + t3.get());
//        };
//    }
}
