package com.spring.service.user;

import com.spring.model.jwt.User;

import java.util.List;

public interface UserService {
    // Setter
    User saveUser(User user);
    Boolean deleteUserById(String id);

    // Getter
    List<User> findUsername(String username);
    User findUserById(String id);
    List<User> findAllUser();

}
