package com.spring.repositories.jpa;

import com.spring.model.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepoSQL extends JpaRepository<User,Long>, JpaSpecificationExecutor {
    User findByUsername(String name);
    List<User> findByUsernameLike(String keyword);
}