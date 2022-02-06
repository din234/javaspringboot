package com.spring.service.user;

import com.spring.model.user.User;

import java.util.List;

public interface UserService {
    // Setter
    User saveUser(User user);
    User saveOrGenerateUser(User user);
    Boolean deleteUserById(String id);
    void firstTimeInsert();

    // Getter
    List<User> findUsername(String username);
    User findUserById(String id);
    List<User> findAllUser();

}
