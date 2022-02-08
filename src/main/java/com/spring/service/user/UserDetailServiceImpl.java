package com.spring.service.user;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.config.CustomException.InvalidPasswordException;
import com.spring.model.user.User;
import com.spring.repositories.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


//import org.springframework.security.core.userdetails.User; // FOR TESTING

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
public class UserDetailServiceImpl implements UserService,UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    //    // For Advanced elasticsearch operation using HighLevelRESTapi
    private final UserRepo userRepo;
//    private final RestHighLevelClient restHighLevelClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserDetailServiceImpl(
            UserRepo userRepo,
//            RestHighLevelClient restHighLevelClient,
            ObjectMapper objectMapper){
        this.userRepo = userRepo;
//        this.restHighLevelClient = restHighLevelClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.findUsername(username).get(0);
        // Add them role cho tung user
//        Collection<SimpleGrantedAuthority>(new SimpleGrantedAuthority(
//
//        ));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(),new ArrayList<>()
        );
    }

    private void httpOutputStream(HttpURLConnection http, byte[] out, int length) throws IOException{
        http.setFixedLengthStreamingMode(length);
        http.connect();
        OutputStream os = http.getOutputStream();
        os.write(out);
    }

    @Override
    public User saveUser(User user) throws Exception{
        String password = user.getPassword();

        // Validate bằng phương pháp thủ công
        if (password.isEmpty() || password.length() < 6){
            throw new InvalidPasswordException(password.length());
        }
        List<User> users = userRepo.findByUsername(user.getUsername());
        if (users.isEmpty()){
            return userRepo.save(user);
        }
        logger.info("Username already exist!");
        return null;
    }

    @Override
    public User saveOrGenerateUser(User user) throws Exception {
        if (this.saveUser(user) != null){
            return user;
        }
        logger.info("Process to generate new username");
        user.setUsername(user.getUsername() + generateRandomNumber(3));
        return this.saveOrGenerateUser(user);
    }
    private int generateRandomNumber(double maxlength){
        Random rand = new Random();
        return rand.nextInt((int) Math.pow(10,maxlength+1));
    }

    @Override
    public Boolean deleteUserById(String id) {
        User delUser = this.findUserById(id);
        if (delUser == null){
            return false;
        };
        userRepo.delete(delUser);
        return true;
    }

    @Override
    public List<User> findUsername(String username) {
//        List<User> users = new ArrayList<>();
        return userRepo.findByUsername(username);
    }

    @Override
    public User findUserById(String id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public List<User> findAllUser() {
//        logger.info("Find All User");
        List<User> users = new ArrayList<>();
        userRepo.findAll().forEach((c) -> {
            users.add(c);
            logger.info(c.toString());
        });
        return users;
    }


}