//package com.spring.service;
//
//
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//// Dùng phương pháp hardcoded username
//// Sau này sẽ sử dụng DAO (Data Access Objecr
//@Service
//public class JwtUserDetailsService implements UserDetailsService {
//    private final String hardcodedUsername = "dinhnkp";
//
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if (hardcodedUsername.equals(username)) {
//            return new User(hardcodedUsername, "$2y$10$rQCT1JBHYkerH7oBb9wJdOaqY3ckw5jq0.F5lLNpTAEx26LFf3qeK",
//                    new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found");
//        }
//    }
//}
