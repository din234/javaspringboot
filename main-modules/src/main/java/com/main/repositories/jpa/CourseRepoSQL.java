package com.main.repositories.jpa;

import com.main.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CourseRepoSQL extends JpaRepository<Course,Long>, JpaSpecificationExecutor {

}
