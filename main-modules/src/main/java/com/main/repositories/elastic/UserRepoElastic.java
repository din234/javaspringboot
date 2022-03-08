package com.spring.repositories.elastic;

import com.spring.model.user.UserSearch;
import org.apache.lucene.search.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface UserRepoElastic extends ElasticsearchRepository<UserSearch, String> {
    UserSearch findByUsername(String username);
    UserSearch findByEmail(String email);
    List<UserSearch> findByUsernameLike(String keyword);

//    List<UserSearch> findByUsername(String name, Sort sort);
//    List<UserSearch> findByUsername(String name, Pageable pageable);
}

/*
{
    "query": {
        "bool" : {
            "must" : [
                { "query_string" : { "query" : "?", "fields" : [ "name" ] } },
            ]
        }
    }
}
* */