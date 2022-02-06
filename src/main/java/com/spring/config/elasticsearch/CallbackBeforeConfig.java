package com.spring.config.elasticsearch;

import com.spring.model.user.User;
import org.springframework.data.elasticsearch.core.event.BeforeConvertCallback;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Component;

@Component
public class CallbackBeforeConfig implements BeforeConvertCallback<User> {

    @Override
    public User onBeforeConvert(User entity, IndexCoordinates index) {
        if (entity.getId() == null){
            // Dùng setter trong user model
//             entity.setId(UUIDs.randomBase64UUID());
//            entity.setId("WAA");
        }
//        if (entity.getUsername() == null){
//            entity.setUsername("");
//        }
        return entity;
    }
}
