package com.student.registration.student;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentRepository extends HashMap<Long, Student> {


    public StudentRepository() {

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
