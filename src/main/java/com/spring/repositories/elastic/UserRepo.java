package com.spring.repositories.elastic;

import com.spring.model.user.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends ElasticsearchRepository<User, String> {
    List<User> findByUsername(String name);
    List<User> findByUsernameLike(String keyword);
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