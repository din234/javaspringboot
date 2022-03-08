package com.main.repositories.elastic;

import com.spring.model.user.UserSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepoElastic extends ElasticsearchRepository<UserSearch, String>{
}
