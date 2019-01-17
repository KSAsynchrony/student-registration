package com.student.registration.course;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CourseRepositoryTest {
    CourseRepository courseRepository;



    @Before
    public void setup() {
        courseRepository = new CourseRepository();
        courseRepository.insertCourse(new Course(null, "Maths"));
        courseRepository.insertCourse(new Course(null, "Science"));
        courseRepository.insertCourse(new Course(null, "Literature"));
    }

    @Test
    public void testInsert() {
        Course courseToInsert = new Course(null, "testCourse");
        Course expected = new Course(4l, "testCourse");;
        Assert.assertEquals(expected, courseRepository.insertCourse(courseToInsert));
    }

    @Test
    public void testFind() {
        Course expected = new Course(1l, "Maths");
        Course actual = courseRepository.findCourse(1l);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAll() {
        Collection<Course> courses = courseRepository.getAllCourses();
        Assert.assertTrue(courses.size() == 3);
    }

    @Test
    public void testDelete() {
        Course deletedCourse = new Course(1l, "Maths");
        Assert.assertEquals(deletedCourse, courseRepository.delete(1l));
        Assert.assertEquals(null, courseRepository.findCourse(1l));
    }

}
