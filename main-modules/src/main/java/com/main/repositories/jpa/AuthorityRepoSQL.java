package com.main.repositories.jpa;

import com.spring.model.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AuthorityRepoSQL extends JpaRepository<Authority,Long> , JpaSpecificationExecutor {
    Authority findByTitle(String title);
}
