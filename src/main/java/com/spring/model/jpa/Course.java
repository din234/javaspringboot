package com.spring.model.jpa;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

@Entity @Table(name = "course") @DynamicUpdate
public class Course {
    @Id
    @GeneratedValue(strategy = AUTO)
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "credit")
    private Double credit;

    @Column(name = "department")
    private String department;
}
