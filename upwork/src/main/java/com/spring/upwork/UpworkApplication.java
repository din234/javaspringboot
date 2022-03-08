package com.spring.upwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories(basePackages = "com.upwork.repo")
public class UpworkApplication {
    public static void main(String[] args){
        SpringApplication.run(UpworkApplication.class, args);
    }
}