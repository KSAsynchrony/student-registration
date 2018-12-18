package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.util.StringUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class StudentRepository extends HashMap<Long, Student> {

    @Autowired
    CourseRepository courseRepository;

    public Long addStudent(StudentRegistration registration) {
        Long id = nextAvailableId();
        Student student = new Student(id, registration.getFirstName(),
                registration.getLastName(),
                getCourses(registration));

        put(id, student);

        courseRepository.linkStudentWithCourse(student.getCourses(), student.getId());

        return id;
    }

    private List<Long> getCourses(StudentRegistration registration) {
        List<Long> courses = new LinkedList<>();
        for (String course : StringUtils.split(registration.getCourses(), ",")) {
            courses.add(Long.parseLong(course));
        }
        return courses;
    }

    private long nextAvailableId() {
        boolean validId = false;
        Long id = 0l;
        while (!validId) {
            if (get(id) == null) {
                validId = true;
            } else {
                id++;
            }
        }
        return id;
    }
}
