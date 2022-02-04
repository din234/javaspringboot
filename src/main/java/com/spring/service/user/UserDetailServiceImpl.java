package com.spring.service.user;


import com.spring.model.jwt.User;
import com.spring.repositories.UserRepo;
import org.elasticsearch.common.UUIDs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


//import org.springframework.security.core.userdetails.User; // FOR TESTING

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.elasticsearch.core.event.BeforeConvertCallback;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
public class UserDetailServiceImpl implements UserService,UserDetailsService,BeforeConvertCallback<User> {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private UserRepo userRepo;

//    // For Advanced elasticsearch operation using HighLevelRESTapi
//    private RestHighLevelClient restHighLevelClient;
//    private ObjectMapper objectMapper;

    private final String hardcodedUsername = "dinhnkp";

//    @Autowired
//    public UserDetailServiceImpl(UserRepo userRepo){
//        this.userRepo = userRepo;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (hardcodedUsername.equals(username)) {
            return null;
//            return new User(hardcodedUsername, "$2y$10$rQCT1JBHYkerH7oBb9wJdOaqY3ckw5jq0.F5lLNpTAEx26LFf3qeK",
//                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Override
    public User onBeforeConvert(User entity, IndexCoordinates index) {
//        if (entity.getId() == null){
//            // Dùng setter trong user model
//             entity.setId(UUIDs.randomBase64UUID());
//        }
//        if (entity.getUsername() == null){
//            entity.setUsername("");
//        }
        return entity;
    }

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
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

class Generator {
    public static String generateRandom(){
        return null;
    }
}
