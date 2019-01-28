package com.student.registration;


import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class StudentRegistrationController {

    private StudentRegistrationRepository studentRegistrationRepository = new StudentRegistrationRepository();

    public StudentRegistrationController() {}

//    @PostMapping
//    public void addBulkRegistration(@RequestBody Map<Long, List<Long>> map){
//       studentRegistrationRepository.addRegistration(map);
//    }

//    @PostMapping("${studentId}")
//    public void addRegistration(long studentId, @RequestParam long courseId){
//        studentRegistrationRepository.addRegistration(studentId, courseId);
//    }

//    @DeleteMapping("${studentId}")
//    public void deleteRegistration(long studentId, @RequestParam long courseId){
//        studentRegistrationRepository.deleteRegistration(studentId, courseId);
//    }

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
