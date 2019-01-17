package com.student.registration.course;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class CourseControllerTest {

    CourseRepository courseRepository;

    CourseController courseController;

    ModelMap modelMap;

    @Before
    public void setUp() {
        courseRepository = mock(CourseRepository.class);
        courseController = new CourseController(courseRepository);
        modelMap = new ModelMap();
    }

    @Test
    public void testGetMapping() {
        Assert.assertTrue("insertCourse".equals(courseController.getMapping()));
    }

    @Test
    public void testCreateCourse() {
        Course course = new Course(null, "testCourse");

        when(courseRepository.insertCourse(course)).thenReturn(new Course(4l, "testCourse"));

        String result = courseController.createCourse(course, modelMap);

        Assert.assertTrue("courseSuccess".equals(result));
        Assert.assertTrue("testCourse".equals(modelMap.get("courseName")));
    }

    @Test
    public void testFindCourse() throws Exception {

        when(courseRepository.findCourse(1l)).thenReturn(new Course(1l, "Maths"));

        String result = courseController.lookupCourse(1, modelMap);

        Assert.assertTrue("courseLookup".equals(result));
        Assert.assertTrue("Maths".equals(modelMap.get("courseName")));
        Assert.assertTrue(modelMap.get("courseId").equals(1l));
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
