package com.spring.service.user;


// import com.spring.model.jwt.User; // TRUE IMPLEMENTATION
import org.springframework.security.core.userdetails.User; // FOR TESTING
import java.util.ArrayList;

import com.spring.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImp implements UserDetailsService {
//    @Autowired
//    private UserRepository userRepository;

    private final String hardcodedUsername = "dinhnkp";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (hardcodedUsername.equals(username)) {
            return new User(hardcodedUsername, "$2y$10$rQCT1JBHYkerH7oBb9wJdOaqY3ckw5jq0.F5lLNpTAEx26LFf3qeK",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }


}
