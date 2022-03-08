package com.spring;

import com.spring.service.user.UserDetailServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.spring.repositories.jpa")
@EnableTransactionManagement
@EnableScheduling
public class SpringBootIndex {

//    @Autowired
//    private ServletContext servletContext;

    public static void main(String[] args){
        SpringApplication.run(SpringBootIndex.class, args);
    }

    // Tránh circular dependency
    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserDetailServiceImpl userServ){
        return null;
    }

//    @Bean
//    public WebDriver webDriver(){
//        return new ChromeDriver();
//    }
}



//        return runExcel ? args -> {
//            final String addUserUrl = URL+":"+PORT+"/user/register";
//
//            final List<Object> users = readExcelFile.readExcel(new UserSheet());
//
//            ObjectWriter ow = new ObjectMapper().writer();
//            RestTemplate restTemplate = new RestTemplate();
//            HttpHeaders header = new HttpHeaders();
//            header.setContentType(MediaType.APPLICATION_JSON);
//
//            for (Object user : users) {
//
//                String json = ow.writeValueAsString(user);
//                try {
//                    HttpEntity<String> request = new HttpEntity<>(json, header);
//                    ResponseEntity<String> response = restTemplate.postForEntity(addUserUrl, request, String.class);
//
//                    System.out.println(response);
//                } catch (Exception e) {
////                    e.printStackTrace();
//                }
//            }
//
//        } : null;
