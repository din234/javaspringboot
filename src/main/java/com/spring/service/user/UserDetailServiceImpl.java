package com.spring.service.user;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.config.CustomException.InvalidPasswordException;
import com.spring.model.user.Group;
import com.spring.model.user.User;
import com.spring.repositories.elastic.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


//import org.springframework.security.core.userdetails.User; // FOR TESTING

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

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
//    @Caching(
//            cacheable = @Cacheable (value = "user")
//    )
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = this.findUsername(username);
        if (users.isEmpty()){
            return null;
        }

        User user = users.get(0);
//        Collection<SimpleGrantedAuthority> authorities = Arrays.stream(Group.values()).map(group -> new SimpleGrantedAuthority(group.toString())).collect(Collectors.toList());
        Collection<SimpleGrantedAuthority> authorities = user.getGroups().stream().map(
                group -> new SimpleGrantedAuthority(group)).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(),authorities
        );
    }
    private void httpOutputStream(HttpURLConnection http, byte[] out, int length) throws IOException{
        http.setFixedLengthStreamingMode(length);
        http.connect();
        OutputStream os = http.getOutputStream();
        os.write(out);
    }

    @Override
//    @Caching(
//            cacheable =  @Cacheable (value = "user")
//    )
    public User saveUser(User user) throws Exception{
        // Validate bằng phương pháp thủ công
        String password = user.getPassword();
        if (password.length() < 6){
            logger.info("TEST");
            throw new InvalidPasswordException(password.length());
        }
        List<User> users = userRepo.findByUsername(user.getUsername());
        if (users.isEmpty()){
            List<String> groups = new ArrayList<>();
//            groups.add(Group.USER.toString());
//            user.setGroups(groups);
            user.setDateCreated(new Date(System.currentTimeMillis()));
            return userRepo.save(user);
        }
        logger.info("Username already exist!");
        return null;
    }

//    @Override
//    public void addRole() {
//
//    }

    @Override
//    @Caching(
//            cacheable = @Cacheable(value = "user")
//    )
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
//    @CacheEvict(value="user")
    public Boolean deleteUserById(String id) {
        User delUser = this.findUserById(id);
        if (delUser == null){
            return false;
        };
        userRepo.delete(delUser);
        return true;
    }

    @Override
//    @Cacheable
    public List<User> findUsername(String username) {
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

    @Override
    public List<User> searchUser(String keyword){
        return userRepo.findByUsernameLike(keyword);
    }
}