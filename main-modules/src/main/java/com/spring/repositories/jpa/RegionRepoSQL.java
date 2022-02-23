package com.spring.repositories.jpa;

import com.spring.model.location.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface RegionRepoSQL extends JpaRepository<Region,Long>, JpaSpecificationExecutor {
    @Query(value = "SELECT * FROM region WHERE region_title=?1", nativeQuery = true)
    Region findByRegionTitle(String title);
}
