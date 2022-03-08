package com.main.service.localProvider;

import com.main.model.course.Course;
import com.main.model.location.Country;
import com.main.model.location.Region;
import com.main.repositories.jpa.AuthorityRepoSQL;
import com.main.repositories.jpa.CourseRepoSQL;
import com.spring.model.security.Authority;
import com.main.repositories.jpa.CountryRepoSQL;
import com.main.repositories.jpa.RegionRepoSQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LocalProviderPostService {
    Logger logger = LoggerFactory.getLogger(LocalProviderService.class);

    private final AuthorityRepoSQL authorityRepoSql;
    private final RegionRepoSQL regionRepoSQL;
    private final CountryRepoSQL countryRepoSQL;
    private final CourseRepoSQL courseRepoSQL;

    public LocalProviderPostService(
            AuthorityRepoSQL authorityRepoSQL,
            RegionRepoSQL regionRepoSQL,
            CountryRepoSQL countryRepoSQL,
            CourseRepoSQL courseRepoSQL
    ) {
        this.authorityRepoSql = authorityRepoSQL;
        this.regionRepoSQL = regionRepoSQL;
        this.countryRepoSQL = countryRepoSQL;
        this.courseRepoSQL = courseRepoSQL;
    }

    public Authority addAuthority(Authority authority){return authorityRepoSql.save(authority);}
    public Region addRegion(Region region){
//        regionRepoSQL.getById(1);
        return regionRepoSQL.save(region);
    }
    public Country addCountry(Country country){
        logger.info(country.getRegion().toString());
        return countryRepoSQL.save(country);
//        return null;
    }

    public Country addLocation(Country country){
        String regionTitle = country.getRegion().getRegion_title();
        Region result = regionRepoSQL.findByRegionTitle(regionTitle);
        if (result == null){
            regionRepoSQL.save(country.getRegion());
            result = regionRepoSQL.findByRegionTitle(regionTitle);
        }
        country.setRegion(result);
        return countryRepoSQL.save(country);
    }


    public Course addCourse(Course course) {return courseRepoSQL.save(course);}
}
