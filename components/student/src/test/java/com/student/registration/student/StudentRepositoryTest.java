package com.student.registration.student;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentRepositoryTest {
    StudentRepository studentRepository;

    @Before
    public void init() {
        studentRepository = new StudentRepository();
        studentRepository.addStudent(new Student(0l, "Kerim", "Strikovic"));
        studentRepository.addStudent(new Student(1l, "John", "Doe"));
        studentRepository.addStudent(new Student(2l, "Dave", "Blatt"));
    }

    @Test
    public void testAddStudent() {
        Student newStudent = new Student(null, "Jason", "Statham");
        studentRepository.addStudent(newStudent);

        newStudent.setId(3l);
        Assert.assertEquals(newStudent, studentRepository.get(3l));
    }
}