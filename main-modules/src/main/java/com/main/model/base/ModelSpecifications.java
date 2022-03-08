package com.main.model.base;

import com.main.config.Constant;
import org.springframework.data.jpa.domain.Specification;


public class ModelSpecifications {

    // KHONG SU DUNG
//    public static Specification<User> userModified(){
//        return (root, query, cb) -> {
//            Expression<Timestamp> currentTime = cb.currentTimestamp();
//
//            // cb.literal dùng để convert từ Timestamp sang Expression
//            Expression<Timestamp> currentTimeAgo = cb.literal(
//                    new Timestamp(System.currentTimeMillis() - Constant.CHECK_MODIFIED_AFTER_MS));
//            return cb.between(root.<Date>get(
//                    Constant.MODIFIED_DATE_FIELD),
//                    currentTimeAgo,
//                    currentTime);
//        };
//    }

    public static Specification<Model> getSyncFlag(){
        return (root, query, cb) -> cb.isFalse(root.<Boolean>get(Constant.SYNC_FLAG_FIELD));
    }

    public static Specification<Model> getDeleteFlag(){
        return (root, query, cb) -> cb.isTrue(root.<Boolean>get(Constant.DELETE_FLAG_FIELD));
    }

    public static Specification<Model> test(){
        return (root, query, cb) -> null;
    }
}