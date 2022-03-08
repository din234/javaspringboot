package com.main.repositories.jpa;

import com.main.model.location.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CountryRepoSQL extends JpaRepository<Country,Long>, JpaSpecificationExecutor {
}
