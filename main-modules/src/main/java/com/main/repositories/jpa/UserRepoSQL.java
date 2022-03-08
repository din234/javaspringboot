package com.spring.repositories.jpa;

import com.spring.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Transactional(readOnly = true)
public interface UserRepoSQL extends JpaRepository<User,Long>, JpaSpecificationExecutor {
    User findByUsername(String name);
    List<User> findByUsernameLike(String keyword);
    User findByEmail(String email);
}