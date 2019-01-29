package com.student.registration.main;


import com.student.registration.domain.Grade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
public class GradeController {

    GradeClient gradeClient;
    StudentClient studentClient;
    CourseClient courseClient;

    public GradeController(GradeClient gradeClient,
                           StudentClient studentClient,
                           CourseClient courseClient){
        this.gradeClient = gradeClient;
        this.studentClient = studentClient;
        this.courseClient = courseClient;
    }

    @GetMapping("/viewGrades/{studentId}")
    public String getGradesForStudent(ModelMap modelMap, @PathVariable long studentId){
        modelMap.put("student", studentClient.getStudentById(studentId));
        modelMap.put("grades", gradeClient.getGradesForStudent(studentId));
        return "allGrades";
    }

    @GetMapping("/updateGrade")
    public String updateGrade(ModelMap modelMap,
                              @RequestParam("studentId") long studentId,
                              @RequestParam("courseId") long courseId) {
        modelMap.put("student", studentClient.getStudentById(studentId));
        modelMap.put("course", courseClient.getCourseById(courseId));
        modelMap.put("grade", new Grade(studentId, courseId, ""));
        return "updateGrade";
    }

    @GetMapping("/insertGrade")
    public String updateGrade(ModelMap modelMap,
                              @RequestParam("studentId") long studentId,
                              @RequestParam("courseId") long courseId,
                              @RequestParam("band") String band) {
        Grade grade = new Grade(studentId, courseId, band);
        List<Grade> grades = new LinkedList<>();
        grades.add(grade);
        gradeClient.updateGradesForStudent(grades);
        modelMap.put("student", studentClient.getStudentById(studentId));
        modelMap.put("grades", gradeClient.getGradesForStudent(studentId));
        return "allGrades";
    }

    @PostMapping("/addGrades")
    public String addGradesForStudent(ModelMap modelMap, @RequestBody List<Grade> grades){
        gradeClient.updateGradesForStudent(grades);
        return "allGrades";
    }

}
