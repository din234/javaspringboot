package com.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.spring.model.user.User;
import com.spring.service.user.UserDetailServiceImpl;
import com.spring.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class SpringBootIndex {

//    @Autowired
//    private ServletContext servletContext;
    @Autowired
    private ExcelUtil readExcelFile;

    @Value("${server.url}")
    private String URL;
    @Value("${server.port}")
    private String PORT;

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
            final String addUserUrl = URL+":"+PORT+"/user/register";
            final List<User> users = readExcelFile.readExcel();

            ObjectWriter ow = new ObjectMapper().writer();
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);

            for (User user : users) {

                String json = ow.writeValueAsString(user);
                try {
                    HttpEntity<String> request = new HttpEntity<>(json, header);
                    ResponseEntity<String> response = restTemplate.postForEntity(addUserUrl, request, String.class);

                    System.out.println(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
    }
}