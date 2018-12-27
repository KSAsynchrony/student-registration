package com.student.registration.repository;

import com.student.registration.course.CourseRepository;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service
public class StudentRepository extends HashMap<Long, Student> {

    CourseRepository courseRepository;

    public StudentRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Student> getAll(List<Long> studentIDs) {
        List<Student> students = new ArrayList<>();
        for (Long id : studentIDs) {
            if (get(id) != null) {
                students.add(get(id));
            }
        }
        return students;
    }

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
