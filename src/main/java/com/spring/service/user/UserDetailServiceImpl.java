package com.spring.service.user;


import com.spring.config.CustomException.InvalidPasswordException;
import com.spring.model.jpa.Authority;
import com.spring.model.jpa.User;
import com.spring.model.search.UserSearch;
import com.spring.repositories.elastic.UserRepoElastic;
import com.spring.repositories.jpa.AuthorityRepoSql;
import com.spring.repositories.jpa.UserRepoSQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.*;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PrePersist;
import javax.persistence.Transient;

@Service @Transactional
public class UserDetailServiceImpl implements UserService,UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    private final UserRepoSQL userRepoSQL;
    private final AuthorityRepoSql authorityRepoSql;
    private final UserRepoElastic userRepoElastic;
    private final PasswordEncoder passwordEncoder;

//      // For Advanced elasticsearch operation using HighLevelRESTapi
//    private final RestHighLevelClient restHighLevelClient;
//    private final ObjectMapper objectMapper;

    @Autowired
    public UserDetailServiceImpl(
            UserRepoElastic userRepoElastic,
            AuthorityRepoSql authorityRepoSql,
            UserRepoSQL userRepoSQL,
            PasswordEncoder passwordEncoder
//            RestHighLevelClient restHighLevelClient,
//            ObjectMapper objectMapper
    ){
        this.userRepoElastic = userRepoElastic;
        this.authorityRepoSql = authorityRepoSql;
        this.userRepoSQL = userRepoSQL;
        this.passwordEncoder = passwordEncoder;
//        this.restHighLevelClient = restHighLevelClient;
//        this.objectMapper = objectMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserSearch> users = this.findUsername(username);
        if (users.isEmpty()){
            return null;
        }

        UserSearch user = users.get(0);
//        Collection<SimpleGrantedAuthority> authorities = Arrays.stream(Group.values()).map(group -> new SimpleGrantedAuthority(group.toString())).collect(Collectors.toList());
//        Collection<SimpleGrantedAuthority> authorities = user.getGroups().stream().map(
//                group -> new SimpleGrantedAuthority(group)).collect(Collectors.toList());
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

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


    // MYSQL REPOSITORY
    @Override
    public User saveUser(User user) throws Exception{
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepoSQL.save(user);
    }
//
////    @Override
////    public void addRole() {
////
////    }
//
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
//    @CacheEvict(value="user")
    public Boolean deleteUserById(String id) {
//        UserSearch delUser = this.findUserById(id);
//        if (delUser == null){
//            return false;
//        };
//        userRepo.delete(delUser);
//        return true;

        return true;
    }


    @Override
    public Authority addAuthority(String username,String authTitle) {
        User user = userRepoSQL.findByUsername(username);
        Authority authority = authorityRepoSql.findByTitle(authTitle);
        user.getAuthorities().add(authority);
        return null;
    }


    // RUN ELASTICSEARCH

    @Override
//    @Cacheable
    public List<UserSearch> findUsername(String username) {
        return userRepoElastic.findByUsername(username);
    }

    @Override
    public UserSearch findUserById(String id) {
        return userRepoElastic.findById(id).orElse(null);
    }

    @Override
    public List<UserSearch> findAllUser() {
//        logger.info("Find All User");
        List<UserSearch> users = new ArrayList<>();
        userRepoElastic.findAll().forEach((c) -> {
            users.add(c);
            logger.info(c.toString());
        });
        return users;
    }

    @Override
    public List<UserSearch> searchUser(String keyword){
        return userRepoElastic.findByUsernameLike(keyword);
    }

}