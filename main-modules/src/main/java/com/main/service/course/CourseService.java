package com.spring.service.course;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public interface CourseService {
    CompletableFuture<String> getCourses();
    CompletableFuture<String> getInstructorCourses();
    CompletableFuture<String> getStudentCourses();
}
