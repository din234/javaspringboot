package com.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.ServletContext;

@SpringBootApplication
public class SpringBootIndex {

    @Autowired
    private ServletContext servletContext;

    public static void main(String[] args){
        SpringApplication.run(SpringBootIndex.class, args);
    }
}