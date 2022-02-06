package com.spring.model.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Document(indexName = "users")
public class LoginForm {
    @Id
    protected String id;

    @Field(type = FieldType.Text, name = "username")
    @NotEmpty
    protected String username;

    @Field(type = FieldType.Text, name = "password")
    @NotEmpty(message = "Missing Password")
    @Min(value = 6, message = "Password must be at least 6 characters")
    protected String password;

    protected Date dateCreated;


    public LoginForm(){
        this.dateCreated = new Date(System.currentTimeMillis());
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}