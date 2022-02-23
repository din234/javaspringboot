package com.spring.model.user;

import com.spring.model.user.form.LoginForm;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "users")
public class UserSearch implements LoginForm {
    @Id
    private String id;

    @Field(name = "username")
    private String username;

    @Field(name = "password", type = FieldType.Text)
    private String password;

    @Field(name = "full_name")
    private String fullName;

    @Field(name = "email")
    private String email;

//    @Field(name = "authority", type = FieldType.Text)
//    private Collection<Authority> authorities = new ArrayList<>();

    @Field(name = "date_modified", type = FieldType.Date)
    private Date date_modified;

    @Field(name = "date_created",type = FieldType.Date)
    private Date dateCreated;

//    @Field(name = "instructor", type = FieldType.Object)
//    private Instructor instructor;


    public UserSearch() {
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
                ", dateCreated=" + dateCreated +
                '}';
    }
}
