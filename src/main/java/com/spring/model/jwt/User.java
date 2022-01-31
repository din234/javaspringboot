package com.spring.model.jwt;

import com.spring.util.Role;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private Role role;
    private LocalDate dateCreated;

    // public Account(){}
    public User(
            String id,
            String name,
            String email,
            Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.dateCreated = LocalDate.now();
    }
    public User(
            String name,
            String email,
            Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.dateCreated = LocalDate.now();
    }
    public User(
            String name,
            String email) {
        this.name = name;
        this.email = email;
        this.role = Role.GUEST;
        this.dateCreated = LocalDate.now();
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
