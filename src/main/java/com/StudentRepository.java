package com;

import java.util.HashMap;

public class StudentRepository extends HashMap<Long, Student> {
    public Long addStudent(String firstName, String lastName) {

        Long id = nextAvailableId();
        put(id, new Student(id, firstName, lastName, null));
        return id;
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
