package com.student.registration.course;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CourseCrudRepository extends CrudRepository<Course, Long> {
    Optional<Course> findById(Long id);
    void deleteById(Long id);
}
