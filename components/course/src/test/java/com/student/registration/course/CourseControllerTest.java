package com.student.registration.course;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

public class CourseControllerTest {

    CourseController courseController;
    List<Course> courseList;

    //Mocks
    CourseRepository courseRepository = mock(CourseRepository.class);

    @Before
    public void setUp() {
        courseList = new LinkedList<>();
        courseList.add(new Course(null, "Maths"));
        courseList.add(new Course(null, "Science"));
        courseList.add(new Course(null, "Literature"));

        courseController = new CourseController(courseRepository);
    }

    @After
    public void assertNoMoreInteractions() {
        verifyNoMoreInteractions(courseRepository);
    }

    @Test
    public void testAllCourses() {
        when(courseRepository.getAllCourses()).thenReturn(courseList);
        Assert.assertEquals(courseList, courseController.getAllCourses());
        verify(courseRepository).getAllCourses();
    }

    @Test
    public void lookupCourse() throws Exception {
        Course expectedCourse = new Course(1l, "Maths");
        when(courseRepository.findCourse(1l)).thenReturn(expectedCourse);

        Assert.assertEquals(expectedCourse, courseController.lookupCourse(1l));
        verify(courseRepository).findCourse(1l);
    }

    @Test
    public void testCreateCourse() {
        Course course = new Course(null, "testCourse");
        Course returnedCourse = new Course(4l, "testCourse");
        when(courseRepository.insertCourse(course)).thenReturn(returnedCourse);

        Assert.assertEquals(returnedCourse, courseController.createCourse(course));
        verify(courseRepository).insertCourse(course);
    }

    @Test
    public void testEditCourse() {
        Course editedCourse = new Course(1l, "testCourse");
        when(courseRepository.editCourse(editedCourse)).thenReturn(editedCourse);

        Assert.assertEquals(editedCourse, courseController.editCourse(editedCourse));
        verify(courseRepository).editCourse(editedCourse);
    }

    @Test
    public void testDeleteCourse() throws Exception{
        Course deletedCourse = new Course(1l, "Maths");
        when(courseRepository.delete(1l)).thenReturn(deletedCourse);

        Assert.assertEquals(deletedCourse, courseController.deleteCourse(1l));
        verify(courseRepository).delete(1l);
    }

}
