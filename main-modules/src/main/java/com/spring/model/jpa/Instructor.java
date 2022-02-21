package com.spring.model.jpa;

import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Date;

public class Instructor {
    @Field(name = "joined_date")
    private Date joinDate;

    @Field(name = "department")
    private String department;
}