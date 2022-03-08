package com.main.service.user;

import com.spring.model.user.User;
import com.spring.model.user.UserSearch;

import java.util.List;

public interface UserService {
//    // Setter
    User saveUser(User user) throws Exception;
    User saveOrGenerateUser(User user) throws Exception;
    Boolean deleteUserById(String id);

    // Getter
    UserSearch findUsername(String username);
    UserSearch findUserByEmail(String email);
    UserSearch findUserById(String id);
    List<UserSearch> findAllUser();
    List<UserSearch> searchUser(String keyword);
    Boolean addAuthority(String username, String auth);
    Boolean deleteAuthority(String username, String auth);
}
