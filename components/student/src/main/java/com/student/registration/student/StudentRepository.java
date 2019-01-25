package com.student.registration.student;

import com.student.registration.domain.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentRepository extends HashMap<Long, Student> {

    public StudentRepository() {

    }

    public Student addStudent(Student student) {
        Long id = nextAvailableId();
        student.setId(id);
        put(id, student);

        return student;
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
