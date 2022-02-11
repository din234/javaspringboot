package com.spring.service.user;

import com.spring.model.user.User;

import java.util.List;

public interface UserService {
    // Setter
    User saveUser(User user) throws Exception;
    User saveOrGenerateUser(User user) throws Exception;
    Boolean deleteUserById(String id);

    // Getter
    List<User> findUsername(String username);
    User findUserById(String id);
    List<User> findAllUser();
    List<User> searchUser(String keyword);
}
