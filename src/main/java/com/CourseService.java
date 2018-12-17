package com;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseService {

    private static Map<Long, Course> courseMap = new HashMap<>();

    static{
        courseMap.put(1l, new Course(1, "Maths"));
        courseMap.put(2l, new Course(2, "Science"));
        courseMap.put(3l, new Course(3, "Literature"));
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

    private int findNextId(){
        return courseMap.size() == 0 ? 1 : courseMap.size()+1;
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
}
