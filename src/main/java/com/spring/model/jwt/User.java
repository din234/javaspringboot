package com.spring.model.jwt;

import com.spring.util.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;

@Document(indexName = "users")
public class User {
    @Id
    private String id;

    @Field(type = FieldType.Text, name = "username")
    private String username;

//    @Field(type = FieldType.Text, name = "name")
//    private String name;

    @Field(type = FieldType.Text, name = "email")
    private String email;

    @Field(type = FieldType.Text, name = "password")
    private String password;

//    @Field(type = FieldType.Text, name = "role")
//    private Role role;

//    @Field(type = FieldType.Text, name = "dateCreated")
//    private LocalDate dateCreated;

    public User(){
//        this.dateCreated = LocalDate.now();
    };

    public User(
            String id,
            String username,
            String email,
            String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
//        this.role = role;
//        this.dateCreated = LocalDate.now();
    }
//    public User(
//            String name,
//            String email,
//            Role role) {
//        this.name = name;
//        this.email = email;
//        this.role = role;
////        this.dateCreated = LocalDate.now();
//    }
//    public User(
//            String name,
//            String email) {
//        this.name = name;
//        this.email = email;
//        this.role = Role.GUEST;
////        this.dateCreated = LocalDate.now();
//    }

    /*
     * Getter and setter down here
     * Api sẽ tự độc chạy cá dòng lệnh ở dưới
     * Qua annotation
     * */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
