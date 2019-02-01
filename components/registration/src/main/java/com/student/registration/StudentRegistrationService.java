package com.student.registration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

public class StudentRegistrationService {

    private StudentRegistrationRepository studentRegistrationRepository;

    public StudentRegistrationService(StudentRegistrationRepository studentRegistrationRepository) {
        this.studentRegistrationRepository = studentRegistrationRepository;
    }

    public void deleteStudentMapping(long studentId){
        studentRegistrationRepository.deleteStudentMapping(studentId);
    }

    public void deleteCourseMapping(long courseId){
        studentRegistrationRepository.deleteCourseMapping(courseId);
    }

    public void addStudentCourseRelationShip(long studentId, long courseId) {
        studentRegistrationRepository.addRegistration(studentId, courseId);
    }
    public Set<Long> getCoursesForStudent(long studentId){
        return studentRegistrationRepository.getCoursesStudentRegisteredFor(studentId);
    }

    public Set<Long> getStudentsForCourse(long courseId){
        return studentRegistrationRepository.getRegisteredStudentsForCourse(courseId);
    }
}
