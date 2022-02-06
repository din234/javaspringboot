package com.spring.model.user;

import com.spring.util.Role;
import org.elasticsearch.index.query.RankFeatureQueryBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;

//@Document(indexName = "users")
public class User extends LoginForm {
//    @Field(type = FieldType.Text, name = "name")
//    private String name;

    @Field(type = FieldType.Text, name = "email")
    private String email;


//    @Field(type = FieldType.Text, name = "role")
//    private Role role;


    public User(){
//        this.dateCreated = LocalDate.now();
    };

    public User(
            String username,
            String email,
            String password) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /*
     * Getter and setter down here
     * Api sẽ tự độc chạy cá dòng lệnh ở dưới
     * Qua annotation
     * */
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
                ", email='" + email + '\'' +
                '}';
    }
}
