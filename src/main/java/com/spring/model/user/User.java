package com.spring.model.user;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Document(indexName = "users")
@Document(indexName = "users")
public class User implements LoginForm {
    @Id
    private String id;

    @Field(name = "username")
    @NotEmpty(message = "Missing Username")
    @Length(min = 4, message = "Username must be more than 3 characters!")
    private String username;

    @Field(name = "password", type = FieldType.Text)
    @NotEmpty(message = "Missing Password")
    @Length(min = 6, message = "Password must be more than 6 characters!")
    private String password;

    @Field(name = "full_name")
    private String fullName;

    @Field(name = "email")
    private String email;

    @Field(name = "groups", type = FieldType.Text)
    private List<String> groups = new ArrayList<>();

    @Field(name = "date_created",type = FieldType.Date)
    private Date dateCreated;

    public User() {
//        groups.add("NOU");
//
//        groups.add("Yes");
    }
//    private List<AuthorityTag> authority;
//    private boolean tokenExpired;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

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

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", groups=" + groups +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
