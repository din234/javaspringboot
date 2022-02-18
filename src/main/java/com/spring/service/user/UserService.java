package com.spring.service.user;

import com.spring.model.jpa.Authority;
import com.spring.model.jpa.User;
import com.spring.model.search.UserSearch;

import java.util.List;

public interface UserService {
//    // Setter
    User saveUser(User user) throws Exception;
    User saveOrGenerateUser(User user) throws Exception;
    Boolean deleteUserById(String id);

    // Getter
    List<UserSearch> findUsername(String username);
    UserSearch findUserById(String id);
    List<UserSearch> findAllUser();
    List<UserSearch> searchUser(String keyword);
    Authority addAuthority(String username, String authority);
}
