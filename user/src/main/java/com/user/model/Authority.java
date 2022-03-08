package com.user.model;

//import com.spring.model.base.ModelImpl;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity @Table(name = "authority") @DynamicUpdate
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", unique = true)
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return " " + title + " ";
    }
}
