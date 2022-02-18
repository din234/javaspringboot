package com.spring.repositories.jpa;

import com.spring.model.jpa.Authority;
import com.spring.model.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepoSql extends JpaRepository<Authority,Long> {
    Authority findByTitle(String title);
}
