package com.spring.repositories.elastic;

import com.spring.model.location.LocationSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepoElastic extends ElasticsearchRepository<LocationSearch,String> {
}




