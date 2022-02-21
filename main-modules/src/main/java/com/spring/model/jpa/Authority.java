package com.spring.model.jpa;

import com.spring.model.base.ModelImpl;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity @Table(name = "authority") @DynamicUpdate
public class Authority extends ModelImpl {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", unique = true)
    private String title;

    @Column(name = "number")
    private Integer number;

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
