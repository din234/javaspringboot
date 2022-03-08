package com.main.service.course;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CourseServiceImpl implements CourseService {

    @Async
    @Override
    public CompletableFuture<String> getCourses(){
        return null;
    }

    @Async
    @Override
    public CompletableFuture<String> getInstructorCourses(){
        return null;
    }

    @Async
    @Override
    public CompletableFuture<String> getStudentCourses(){
        return null;
    }
}
