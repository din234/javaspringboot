package com.spring.model.user;

import org.elasticsearch.index.query.RankFeatureQueryBuilder;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;


//@Document(indexName = "users")
public class User extends LoginForm {
    @Field(name = "full_name")
    private String fullName;

    @Field(name = "email")
    private String email;

    private List<AuthorityTag> roles;
//    private boolean tokenExpired;
//    @Field(type = FieldType.Text, name = "role")
//    private Role role;


    /*
     * Getter and setter down here
     * Api sẽ tự độc chạy cá dòng lệnh ở dưới
     * Qua annotation
     * */

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dateCreated=" + dateCreated +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
