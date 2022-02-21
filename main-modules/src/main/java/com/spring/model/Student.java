package com.spring.model;

import com.spring.model.base.ModelImpl;
import com.spring.model.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "students")
public class Student extends ModelImpl {
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "enrollment_date")
    private Date enrollmentDate;

    @Column(name = "des")
    private String des;

    @Column(name = "gpa")
    private Double gpa;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

}
