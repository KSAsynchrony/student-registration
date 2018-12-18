package com;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CourseRepositoryTest 
{
    CourseRepository courseRepository;

    @Before
    public void setup(){
        courseRepository = new CourseRepository();
    }


    @Test
    public void testCreateCourse(){
        Course course = new Course(null, "testCourse");
        Course expected = new Course(4l, "testCourse");
        Course actual = courseRepository.createCourse(course);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindCourse(){
        Course expected = new Course(1l, "Maths");
        Course actual = courseRepository.findCourse(1l);
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testLinkSutdentWithCourse(){
        List<Long> courseIds = new ArrayList<>();
        courseIds.add(1l);
        courseIds.add(2l);

        courseRepository.linkStudentWithCourse(courseIds, 111l);

        List<Long> studentIdsForCourse1 = courseRepository.getStudentIdsForCourse(1l);
        List<Long> studentIdsForCourse2 = courseRepository.getStudentIdsForCourse(2l);

        Assert.assertTrue(studentIdsForCourse1.contains(111l));
        Assert.assertTrue(studentIdsForCourse2.contains(111l));
    }

    @Test
    public void testGetAllCourses(){

        Collection<Course> courses = courseRepository.getAllCourses();

        Assert.assertTrue(courses.size() == 3);
    }
    
}
