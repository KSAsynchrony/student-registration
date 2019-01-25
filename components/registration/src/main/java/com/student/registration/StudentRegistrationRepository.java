package com.student.registration;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;


@Component
public class StudentRegistrationRepository {

    private static Map<Long, List<Long>> studentCourseMap = new HashMap<>();

    private static Map<Long, List<Long>> courseStudentMap = new HashMap<>();

    public void addRegistration(long studentId, long courseId){

        createMappings(studentId, courseId, studentCourseMap);

        createMappings(courseId, studentId, courseStudentMap);
    }

    public void addRegistration(Map<Long, List<Long>> map){

        studentCourseMap.putAll(map);
        Set<Long> studentIds =  studentCourseMap.keySet();
        for(Long studentId: studentIds){
            List<Long> courseIds = studentCourseMap.get(studentId);
            for(Long courseId: courseIds){
                createMappings(courseId, studentId, courseStudentMap);
            }
        }
    }

    private void createMappings(long key, long value, Map<Long, List<Long>> mapping){
        List<Long> ids = mapping.get(key);
        if(ids == null)
            ids = new ArrayList<>();
        ids.add(value);
        mapping.put(key, ids);
    }

    public void deleteRegistration(long studentId, long courseId){

        deleteMappings(studentId, courseId, studentCourseMap);
        deleteMappings(courseId, studentId, courseStudentMap);
    }

    private void deleteMappings(long key, long value, Map<Long, List<Long>> mapping) {
        List<Long> ids = mapping.get(key);
        ids.remove(value);
        mapping.put(key, ids);
    }

    public void deleteStudentMapping(long studentId){
        List<Long> courseIds = studentCourseMap.get(studentId);
        for(Long courseId: courseIds){
            deleteMappings(courseId, studentId, courseStudentMap);
        }
        studentCourseMap.remove(studentId);
    }

    public void deleteCourseMapping(long courseId){
        List<Long> studentIds = courseStudentMap.get(courseId);
        for(Long studentId: studentIds){
            deleteMappings(studentId, courseId, studentCourseMap);
        }
        courseStudentMap.remove(courseId);
    }

    public List<Long> getCoursesStudentRegisteredFor(long studentId){
       return studentCourseMap.get(studentId);
    }

    public List<Long> getRegisteredStudentsForCourse(long courseId){
        return courseStudentMap.get(courseId);
    }
}
