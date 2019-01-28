package com.student.registration.grades;


import com.student.registration.domain.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/grades")
public class GradeController {

    @Autowired
    GradeRepository gradeRepository;


    @PostMapping
    @ResponseBody
    public void updateGradesForStudent(@RequestBody List<Grade> grades){
        gradeRepository.addGradesForStudent(grades);
    }

    @GetMapping("/{studentId}")
    @ResponseBody
    public List<Grade> getGradesForStudent(@PathVariable long studentId){
        List<Grade> gradeList = gradeRepository.getGradesForStudent(studentId);
        if(gradeList == null || gradeList.isEmpty())
            return null;
        return gradeList;
    }

//    @DeleteMapping("${studentId}")
//    @ResponseBody
//    public void deleteGradesForStudent(@PathVariable long studentId){
//        gradeRepository.deleteGradesForStudent(studentId);
//    }

//    @DeleteMapping("${courseId}")
//    @ResponseBody
//    public void deleteGradesForCourse(@PathVariable long courseId, List<Long> studentIds){
//        gradeRepository.deleteGradesForCourse(courseId, studentIds);
//    }
}
