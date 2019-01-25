package com.student.registration;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class StudentRegistrationController {

    private StudentRegistrationRepository studentRegistrationRepository;

    @PostMapping
    public void addBulkRegistration(@RequestBody Map<Long, List<Long>> map){
       studentRegistrationRepository.addRegistration(map);
    }

    @PostMapping("${studentId}")
    public void addRegistration(long studentId, @RequestParam long courseId){
        studentRegistrationRepository.addRegistration(studentId, courseId);
    }

    @DeleteMapping("${studentId}")
    public void deleteRegistration(long studentId, @RequestParam long courseId){
        studentRegistrationRepository.deleteRegistration(studentId, courseId);
    }

    public void deleteStudentMapping(long studentId){
        studentRegistrationRepository.deleteStudentMapping(studentId);
    }

    public void deleteCourseMapping(long courseId){
        studentRegistrationRepository.deleteCourseMapping(courseId);
    }


    public List<Long> getCoursesStudentRegisteredFor(long studentId){
        return studentRegistrationRepository.getCoursesStudentRegisteredFor(studentId);
    }

    public List<Long> getRegisteredStudentsForCourse(long courseId){
        return studentRegistrationRepository.getRegisteredStudentsForCourse(courseId);
    }
}
