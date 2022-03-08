package com.spring.upwork.repo;

import com.spring.upwork.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepoSQL extends JpaRepository<Job,Long>, JpaSpecificationExecutor {
}