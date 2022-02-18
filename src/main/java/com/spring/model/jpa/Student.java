package com.spring.model.jpa;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

public class Student {
    @Field(name = "user_id", type = FieldType.Text)
    private String user_id;

    @Field(name = "enrollment_date", type = FieldType.Date)
    private Date enrollmentDate;

    @Field(name = "description")
    private String description;

    @Field(name = "gpa")
    private long gpa;

//    private Map<>

}
