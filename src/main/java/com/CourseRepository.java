package com;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseRepository {

    private static Map<Long, Course> courseMap = new HashMap<>();


    static{
        courseMap.put(1l, new Course(1l, "Maths"));
        courseMap.put(2l, new Course(2l, "Science"));
        courseMap.put(3l, new Course(3l, "Literature"));
    }

    public Course createCourse(Course course){

        Course newCourse;
        newCourse = new Course(findNextId(), course.getCourseName());
        courseMap.put(newCourse.getCourseId(), newCourse);
        return newCourse;
    }

    public Course findCourse(long courseId){
        return courseMap.get(courseId);
    }

    private Long findNextId(){
        return courseMap.size() == 0 ? 1l : courseMap.size()+1l;
    }

    public void linkStudentWithCourse(List<Long> courseIds, long studentId){
        for(Long courseId: courseIds){
            Course course = courseMap.get(courseId);
            course.getStudentIDs().add(studentId);
            courseMap.put(courseId, course);
        }
    }

    public List<Long> getStudentIdsForCourse(long courseId){
        Course course = courseMap.get(courseId);
        return course != null ? course.getStudentIDs() : null;
    }

    public Collection<Course> getAllCourses() {
        return courseMap.values();
    }
}
