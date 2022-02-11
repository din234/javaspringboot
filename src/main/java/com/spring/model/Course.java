package com.spring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Date;

@Document(indexName = "courses")
public class Course {
    @Id
    private String id;

    @Field(name = "course_name")
    private String courseName;

    @Field(name = "course_des")
    private String courseDes;

    @Field(name = "credit")
    private long credit;

    @Field(name = "year")
    private Date year;


}
