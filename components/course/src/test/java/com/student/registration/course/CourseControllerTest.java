package com.student.registration.course;

import com.student.registration.repository.Course;
import com.student.registration.course.CourseController;
import com.student.registration.repository.CourseRepository;
import com.student.registration.repository.Student;
import com.student.registration.repository.StudentRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CourseControllerTest {

    CourseRepository courseRepository;
    StudentRepository studentRepository;

    CourseController courseController;

    ModelMap modelMap;

    @Before
    public void setUp() {
        courseRepository = mock(CourseRepository.class);
        studentRepository = mock(StudentRepository.class);
        courseController = new CourseController(courseRepository, studentRepository);
        modelMap = new ModelMap();
    }

    @Test
    public void testGetMapping() {
        Assert.assertTrue("createCourse".equals(courseController.getMapping()));
    }

    @Test
    public void testCreateCourse() {
        Course course = new Course(null, "testCourse");

        when(courseRepository.createCourse(course)).thenReturn(new Course(4l, "testCourse"));

        String result = courseController.createCourse(course, modelMap);

        Assert.assertTrue("courseSuccess".equals(result));
        Assert.assertTrue("testCourse".equals(modelMap.get("courseName")));
    }

    @Test
    public void testFindCourse() throws Exception {

        when(courseRepository.findCourse(1l)).thenReturn(new Course(1l, "Maths"));

        String result = courseController.findCourse(1, modelMap);

        Assert.assertTrue("courseLookup".equals(result));
        Assert.assertTrue("Maths".equals(modelMap.get("courseName")));
        Assert.assertTrue(modelMap.get("courseId").equals(1l));
    }

    @Test
    public void testGetStudentsForCourse() throws Exception {
        List<Long> courseIds = new ArrayList<>();
        courseIds.add(1l);
        courseIds.add(2l);

        List<Long> studentIds = new ArrayList<>();
        studentIds.add(111l);

        Student student = new Student(111l, "testFirst", "testLast", courseIds);

        List<Student> students = new ArrayList<>();
        students.add(student);

        when(courseRepository.getStudentIdsForCourse(1l)).thenReturn(studentIds);
        when(studentRepository.getAll(studentIds)).thenReturn(students);

        String result = courseController.getStudentsForCourse(1l, modelMap);


        Assert.assertTrue("allStudents".equals(result));
        Assert.assertTrue(modelMap.get("students").equals(students));
    }

    @Test
    public void testGetAllCourses() {
        Course course = new Course(1l, "Maths");
        List<Course> courses = new ArrayList<>();
        courses.add(course);

        when(courseRepository.getAllCourses()).thenReturn(courses);

        String result = courseController.getAllCourses(modelMap);

        Assert.assertTrue("allCourses".equals(result));
        Assert.assertTrue(courses.equals(modelMap.get("courses")));
    }
}
