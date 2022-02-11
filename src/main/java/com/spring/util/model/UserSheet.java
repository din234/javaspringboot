package com.spring.util.model;
import com.spring.model.user.User;

import java.util.List;
import java.util.function.BiConsumer;

public class UserSheet extends RecordTemplateImpl<User> {
    private final List<BiConsumer<User,Object>> userRecord = List.of (
            (user,val) -> user.setId((String) val),
            (user,val) -> user.setUsername((String) val),
            (user,val) -> user.setPassword((String) val),
            (user,val) -> user.setFullName((String) val),
            (user,val) -> user.setEmail((String) val)
    );
    public UserSheet () {
        super(User::new);
    }

    @Override
     public List<BiConsumer<User, Object>> setMapping() {
        return this.userRecord;
    }
}