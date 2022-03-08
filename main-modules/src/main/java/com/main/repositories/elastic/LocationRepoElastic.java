package com.main.repositories.elastic;

import com.main.model.location.LocationSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepoElastic extends ElasticsearchRepository<LocationSearch,String> {
}




