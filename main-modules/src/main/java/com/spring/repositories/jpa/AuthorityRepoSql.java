package com.spring.repositories.jpa;

import com.spring.model.jpa.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AuthorityRepoSql extends JpaRepository<Authority,Long> {
    Authority findByTitle(String title);
}
