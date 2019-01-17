package com.student.registration.course;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CourseRepository {

    private static Map<Long, Course> courseMap = new HashMap<>();


    static{
        courseMap.put(1l, new Course(1l, "Maths"));
        courseMap.put(2l, new Course(2l, "Science"));
        courseMap.put(3l, new Course(3l, "Literature"));
    }

    public Course insertCourse(Course course){

        Course newCourse;
        newCourse = new Course(findNextId(), course.getCourseName());
        courseMap.put(newCourse.getId(), newCourse);
        return newCourse;
    }

    public Course findCourse(long courseId){
        return courseMap.get(courseId);
    }

    private Long findNextId(){
        return courseMap.size() == 0 ? 1l : courseMap.size()+1l;
    }

    public Collection<Course> getAllCourses() {
        return courseMap.values();
    }

    public Course editCourse(Course course) {
        return courseMap.put(course.getId(), course);
    }

    public Course delete(long courseId) {
        return courseMap.remove(courseId);
    }
}