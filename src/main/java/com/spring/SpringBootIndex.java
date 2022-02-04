package com.spring;

import com.spring.model.jwt.User;
import com.spring.service.user.UserDetailServiceImpl;
import com.spring.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletContext;

@SpringBootApplication
public class SpringBootIndex {

//    @Autowired
//    private ServletContext servletContext;

    public static void main(String[] args){
        SpringApplication.run(SpringBootIndex.class, args);
    }
//
//    @Bean
//    CommandLineRunner run(UserDetailServiceImpl userServ){
//        return args -> {
//            userServ.saveUser(new User(
//                    null,
//                    "A",
//                    "mail",
//                    "123"
//            ));
//
//            userServ.saveUser(new User(
//                    null,
//                    "B",
//                    "mail",
//                    "123"
//            ));
//
//            System.out.println(userServ.findUsername("A"));
//        };
//    }
}