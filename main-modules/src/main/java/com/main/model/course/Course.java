package com.main.model.course;

import com.main.model.base.ModelImpl;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course extends ModelImpl {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "course_code")
    private String course_code;

    @Column(name = "course_name")
    private String course_name;

    @Column(name = "course_des")
    private String course_des;

    @Column(name = "department")
    private String department;

    @Column(name = "credit")
    private Double credit;

//    @ManyToMany
//    private Instructor title;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_des() {
        return course_des;
    }

    public void setCourse_des(String course_des) {
        this.course_des = course_des;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }
}
