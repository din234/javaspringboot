package com.spring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.Map;

@Document(indexName = "students")
public class Student {
    @Id
    private String id;

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
