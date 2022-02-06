package com.spring;

import com.spring.model.user.User;
import com.spring.service.user.UserDetailServiceImpl;
import org.apache.http.client.methods.HttpPost;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.InputStream;

@SpringBootApplication
public class SpringBootIndex {

//    @Autowired
//    private ServletContext servletContext;

    public static void main(String[] args){
        SpringApplication.run(SpringBootIndex.class, args);
    }


    // Setup PasswordEncoder đây thay vì ở WebSecurityConfig để tránh circular dependency giữa
    // WebSecurityConfig và UserDetailServiceImpl
    // Bởi vì:
    // WebSecurityConfig @Bean PasswordEncoder và @Autowired UserDetailServiceImpl
    // UserDetailServiceImpl @Autowired PasswordEncoder cho elasticsearch
    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }


    // Insert data ban đầu khi server chạy
    @Bean
    CommandLineRunner run(UserDetailServiceImpl userServ){
        return args -> {
            userServ.saveUser(new User(
                    "A",
                    "mail",
                    "123"
            ));

            userServ.saveUser(new User(
                    "B",
                    "mail",
                    "123"
            ));

//            HttpPost post = new HttpPost("http://jakarata.apache.org/");
//            User[] data = {
//                    new User("dim", "joe"),
//            };
//            post.setRequestBody(data);
//            InputStream in = post.getResponseBodyAsStream();

//            System.out.println(userServ.findUsername("A"));
        };
    }
}