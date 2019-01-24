package com.student.registration.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CourseRepository {

    CourseCrudRepository courseCrudRepository;


    @Autowired
    public CourseRepository(CourseCrudRepository courseCrudRepository) {
        this.courseCrudRepository = courseCrudRepository;
    }

    public Course insertCourse(Course course){
        return courseCrudRepository.save(course);
    }

    public Course findCourse(long courseId){
        return courseCrudRepository.findById(courseId).get();
    }

    public Collection<Course> getAllCourses() {
        LinkedList<Course> courses = new LinkedList<>();
        courseCrudRepository.findAll().forEach(course -> courses.add(course));
        return courses;
    }

    public Course editCourse(Course course) {
        return courseCrudRepository.save(course);
    }

    @Transactional
    public void delete(Long courseId) {
        courseCrudRepository.deleteById(courseId);
    }
}
