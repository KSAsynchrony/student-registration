package com;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.springframework.ui.ModelMap;
import com.student.registration.student.Student;
import com.student.registration.student.StudentController;
import com.student.registration.student.StudentRegistration;
import com.student.registration.student.StudentRepository;

import java.util.Arrays;
import java.util.List;

public class StudentControllerTest {
    StudentRepository studentRepositoryMock = mock(StudentRepository.class);
    ModelMap model;

    Student kerim;
    Student john;
    Student dave;

    StudentController studentController;

    @Before
    public void init() {
        studentController = new StudentController(studentRepositoryMock);
        model = new ModelMap();

        kerim = new Student(0l, "Kerim", "Strikovic", Arrays.asList(1l, 2l, 3l));
        john = new Student(1l, "John", "Doe", Arrays.asList(1l, 3l));
        dave = new Student(2l, "Dave", "Blatt", Arrays.asList(3l));

    }

    @Test
    public void testGreeting() {
        assertEquals("createStudent", studentController.createStudent());
    }

    @Test
    public void testPostCreateStudent() {
        StudentRegistration registration = new StudentRegistration("Kerim", "Strikovic", "1,2");

        when(studentRepositoryMock.addStudent(registration)).thenReturn(0l);

        String returnedPage = studentController.createStudent(registration, model);

        assertEquals("studentCreateSuccess", returnedPage);
        assertEquals(0l, model.get("id"));
        assertEquals("Kerim", model.get("firstName"));
        assertEquals("Strikovic", model.get("lastName"));
    }

    @Test
    public void testGetStudentById() {
        when(studentRepositoryMock.get(0l)).thenReturn(kerim);

        String returnedPage = studentController.getStudentById(0l, model);

        assertEquals("studentLookup", returnedPage);
        assertEquals(0l, model.get("id"));
        assertEquals("Kerim", model.get("firstName"));
        assertEquals("Strikovic", model.get("lastName"));
    }

    @Test
    public void testGetAllStudents() {
        List<Student> expectedList = Arrays.asList(kerim, dave);
        when(studentRepositoryMock.values()).thenReturn(expectedList);

        String returnedPage = studentController.getAllStudents(model);

        assertEquals("allStudents", returnedPage);
        assertEquals(Arrays.asList(kerim, dave), model.get("students"));
    }
}
