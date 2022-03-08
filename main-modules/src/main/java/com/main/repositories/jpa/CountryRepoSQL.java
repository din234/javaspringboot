package com.spring.repositories.jpa;

import com.spring.model.location.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CountryRepoSQL extends JpaRepository<Country,Long>, JpaSpecificationExecutor {
}
