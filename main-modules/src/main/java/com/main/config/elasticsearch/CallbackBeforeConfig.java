package com.spring.config.elasticsearch;//package com.spring.config;
//
//import com.spring.model.jpa.user.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.event.BeforeConvertCallback;
//import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CallbackBeforeConfig implements BeforeConvertCallback<User> {
//
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public CallbackBeforeConfig(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public User onBeforeConvert(User entity, IndexCoordinates index) {
////        String password = entity.getPassword();
////        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
//        return entity;
//    }
//}
