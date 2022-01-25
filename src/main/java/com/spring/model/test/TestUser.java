package com.spring.model.test;

import com.spring.util.Role;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class TestUser {
    @Id
    private String id;
    private String name;
    private String email;
    private LocalDate dateCreated;

    // public Account(){}
    public TestUser(
            String id,
            String name,
            String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateCreated = LocalDate.now();
    }
    public TestUser(
            String name,
            String email) {
        this.name = name;
        this.email = email;
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

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }
}
