package com.main.model.course;

import com.main.model.base.RecordTemplateImpl;

import java.util.List;
import java.util.function.BiConsumer;

public class CourseSheet extends RecordTemplateImpl<Course> {
//    private final List<BiConsumer<Course,Object>> authorityRecord = List.of (
//            (authority,val) -> authority.setCourse_name((String) val),
//            (authority,val) -> authority.setCourse_des(((Double) val).intValue())
//    );

    public CourseSheet() {
        super(Course::new);
    }

    @Override
    public List<BiConsumer<Course, Object>> setMapping() {
        return null;
    }
}