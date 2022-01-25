package com.spring.config;

import com.spring.model.User;
import org.elasticsearch.common.UUIDs;
import org.springframework.context.annotation.Primary;
import org.springframework.data.elasticsearch.core.event.BeforeConvertCallback;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Component;

/**
 * BeforeCallBack
 * https://github.com/spring-projects/spring-data-elasticsearch/issues/616
 */
@Component
@Primary
public class UserBeforeConvertCallback implements BeforeConvertCallback<User> {
    @Override
    public User onBeforeConvert(User entity, IndexCoordinates index) {
        if (entity.getId() == null){
            // Dùng setter trong user model
            entity.setId(UUIDs.randomBase64UUID());
        }
        return entity;
    }
}
