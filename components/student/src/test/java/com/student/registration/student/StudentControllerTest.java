package com.student.registration.student;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

public class StudentControllerTest {

    StudentController studentController;
    List<Student> students;

    //Mocks
    StudentRepository studentRepositoryMock = mock(StudentRepository.class);

    @Before
    public void init() {
        students = new LinkedList<>();
        students.add(new Student(0l, "Kerim", "Strikovic"));
        students.add(new Student(1l, "John", "Doe"));
        students.add(new Student(2l, "Dave", "Blatt"));

        studentController = new StudentController(studentRepositoryMock);
    }

    @After
    public void asssertNoMoreInteractions() {
        verifyNoMoreInteractions(studentRepositoryMock);
    }

    @Test
    public void testAllStudents() {
        ResponseEntity<Set<Student>> expectedResponse;
        expectedResponse = new ResponseEntity(students, HttpStatus.OK);

        when(studentRepositoryMock.values()).thenReturn(students);
        Assert.assertEquals(expectedResponse, studentController.getAllStudents());
        verify(studentRepositoryMock).values();
    }

    @Test
    public void testGetStudentById() {
        ResponseEntity<Student> expectedResponse;
        expectedResponse = new ResponseEntity(new Student(0l, "Kerim", "Strikovic"), HttpStatus.OK);

        when(studentRepositoryMock.get(0l)).thenReturn(new Student(0l, "Kerim", "Strikovic"));
        Assert.assertEquals(expectedResponse, studentController.getStudentById(0l));
        verify(studentRepositoryMock).get(0l);
    }

    @Test
    public void testCreateStudent() {
        ResponseEntity<Student> expectedResponse;
        expectedResponse = new ResponseEntity(new Student(3l, "John", "Lemon"), HttpStatus.OK);

        Student studentToCreate = new Student(3l, "John", "Lemon");
        when(studentRepositoryMock.addStudent(new Student(3l, "John", "Lemon"))).thenReturn(studentToCreate);
        Assert.assertEquals(expectedResponse, studentController.createStudent(studentToCreate));
        verify(studentRepositoryMock).addStudent(studentToCreate);
    }

    @Test
    public void testEditStudent() {
        ResponseEntity<Student> expectedResponse;
        expectedResponse = new ResponseEntity(new Student(1l, "Johnson", "Doe"), HttpStatus.OK);

        Student studentToEdit = new Student(1l, "Johnson", "Doe");
        when(studentRepositoryMock.put(1l, new Student(1l, "Johnson", "Doe"))).thenReturn(studentToEdit);
        Assert.assertEquals(expectedResponse, studentController.editStudent(studentToEdit));
        verify(studentRepositoryMock).put(1l, studentToEdit);
    }

    @Test
    public void testDeleteStudent() {
        ResponseEntity<Student> expectedResponse;
        expectedResponse = new ResponseEntity(new Student(1l, "John", "Doe"), HttpStatus.OK);

        Student studentToDelete = new Student(1l, "John", "Doe");
        when(studentRepositoryMock.remove(1l)).thenReturn(studentToDelete);
        Assert.assertEquals(expectedResponse, studentController.deleteStudent(1l));
        verify(studentRepositoryMock).remove(1l);
    }
}
