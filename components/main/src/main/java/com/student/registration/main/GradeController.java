package com.student.registration.main;


import com.student.registration.domain.Grade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GradeController {

    GradeClient gradeClient;

    public GradeController(GradeClient gradeClient){
        this.gradeClient = gradeClient;
    }

    @GetMapping("/viewGrades/{studentId}")
    public String getGradesForStudent(ModelMap modelMap, @PathVariable long studentId){
        modelMap.put("studentId", studentId);
        List<Grade> grades = new ArrayList<>();
        System.out.println("calling the api");
        List<Grade> gradesForStudent = gradeClient.getGradesForStudent(studentId);
        if( gradesForStudent == null){
            Grade grade = new Grade(111l, 1111l, "dummy");
            grades.add(grade);
        }else{
            grades.addAll(gradesForStudent);
        }
        System.out.println(grades.size());
        modelMap.put("grades", grades);
        return "allGrades";
    }


    @PostMapping("/addGrades")
    public String addGradesForStudent(ModelMap modelMap, @RequestBody List<Grade> grades){
        gradeClient.updateGradesForStudent(grades);
        //modelMap.put("grades", gradeClient.getGradesForStudent(studentId));
        return "allGrades";
    }

}
