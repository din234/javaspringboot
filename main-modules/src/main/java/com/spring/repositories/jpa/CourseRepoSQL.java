package com.spring.repositories.jpa;

import com.spring.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CourseRepoSQL extends JpaRepository<Course,Long>, JpaSpecificationExecutor {

}
