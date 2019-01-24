package com.student.registration.course;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseCrudRepository extends CrudRepository<Course, Long> {
    List<Course> findByCourseName(String courseName);
}
