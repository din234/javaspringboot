package com.spring.service.user;


import com.spring.model.security.Authority;
import com.spring.model.user.User;
import com.spring.model.user.UserSearch;
import com.spring.repositories.elastic.UserRepoElastic;
import com.spring.repositories.jpa.AuthorityRepoSQL;
import com.spring.repositories.jpa.UserRepoSQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
public class UserDetailServiceImpl implements UserService,UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    private final UserRepoSQL userRepoSQL;
    private final AuthorityRepoSQL authorityRepoSql;
    private final UserRepoElastic userRepoElastic;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailServiceImpl(
            UserRepoElastic userRepoElastic,
            AuthorityRepoSQL authorityRepoSql,
            UserRepoSQL userRepoSQL,
            PasswordEncoder passwordEncoder
    ){
        this.userRepoElastic = userRepoElastic;
        this.authorityRepoSql = authorityRepoSql;
        this.userRepoSQL = userRepoSQL;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null)return null;
        logger.debug("Loading new user and grant authority!");
        User user = userRepoSQL.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found!");
        }
        logger.info(user.toString());

//        Collection<SimpleGrantedAuthority> authorities = Arrays.stream(Group.values()).map(group -> new SimpleGrantedAuthority(group.toString())).collect(Collectors.toList());
        Collection<SimpleGrantedAuthority> authorities = user.getAuthorities().stream().map(
                authority -> new SimpleGrantedAuthority(authority.getTitle())).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(),authorities
        );
    }

    // MYSQL REPOSITORY
    @Override
    public User saveUser(User user) {
        logger.debug("Adding new user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepoSQL.save(user);
    }


    @Override
    public User saveOrGenerateUser(User user) {
        if (this.saveUser(user) != null){
            return user;
        }
        logger.debug("Username exist! Generating new username...");
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
    public Boolean addAuthority(String username,String auth) {
        logger.debug("Add "+auth+" authority to username "+username);
        User user = userRepoSQL.findByUsername(username);
        Authority authority = authorityRepoSql.findByTitle(auth);
        return user.getAuthorities().add(authority);
    }

    @Override
    public Boolean deleteAuthority(String username, String auth){
        logger.debug("Deleting "+auth+"authority");
        User user = userRepoSQL.findByUsername(username);
        Authority authority = authorityRepoSql.findByTitle(auth);
        return user.getAuthorities().remove(authority);
    }

    // RUN ELASTICSEARCH
    @Override
    public UserSearch findUsername(String username) {
        return userRepoElastic.findByUsername(username);
//        return null;
    }

    @Override
    public UserSearch findUserByEmail(String email) {
        return userRepoElastic.findByEmail(email);
    }

    @Override
    public UserSearch findUserById(String id) {
        return userRepoElastic.findById(id).orElse(null);
    }

    @Override
    public List<UserSearch> findAllUser() {
        List<UserSearch> users = new ArrayList<>();
        userRepoElastic.findAll().forEach((c) -> {
            users.add(c);
//            logger.info(c.toString());
        });
        return users;
    }

    @Override
    public List<UserSearch> searchUser(String keyword){
        return userRepoElastic.findByUsernameLike(keyword);
    }

}