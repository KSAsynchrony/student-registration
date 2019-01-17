//package com.student.registration.student;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class StudentRepositoryTest {
//    StudentRepository studentRepository;
//    Student kerim;
//    Student john;
//    Student dave;
//
//    @Before
//    public void init() {
//        studentRepository = new StudentRepository();
//        kerim = new Student(0l, "Kerim", "Strikovic", Arrays.asList(1l, 2l, 3l));
//        john = new Student(1l, "John", "Doe", Arrays.asList(1l, 3l));
//        dave = new Student(2l, "Dave", "Blatt", Arrays.asList(3l));
//    }
//
//    @Test
//    public void testAddStudent() {
//        studentRepository.addStudent(new StudentRegistration(kerim.getFirstName(), kerim.getLastName(), "1,2,3"));
//        studentRepository.addStudent(new StudentRegistration(john.getFirstName(), john.getLastName(), "1,3"));
//
//        assert (studentRepository.get(1l).equals(john));
//    }
//
//    @Test
//    public void testGetAll() {
//        studentRepository.addStudent(new StudentRegistration(kerim.getFirstName(), kerim.getLastName(), "1,2,3"));
//        studentRepository.addStudent(new StudentRegistration(john.getFirstName(), john.getLastName(), "1,3"));
//        studentRepository.addStudent(new StudentRegistration(dave.getFirstName(), dave.getLastName(), "3"));
//
//        List<Student> expectedList = Arrays.asList(kerim, dave);
//
//        assert (studentRepository.getAll(Arrays.asList(0l, 2l)).equals(expectedList));
//    }
//}