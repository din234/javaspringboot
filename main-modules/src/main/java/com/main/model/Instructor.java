package com.spring.model;

import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Date;

public class Instructor {
    @Field(name = "hire_date")
    private Date hire_date;

//    @Field(name = "department")
//    private String department;
}