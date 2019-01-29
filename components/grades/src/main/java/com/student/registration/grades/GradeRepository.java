package com.student.registration.grades;

import com.student.registration.domain.Grade;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GradeRepository {

    private static Map<Long, List<Grade>> gradesMap = new HashMap<>();

    public void addGradesForStudent(List<Grade> grades){
        List<Grade> gradeList;
        for(Grade grade: grades){
            gradeList = gradesMap.get(grade.getStudentId());
            if(gradeList != null){
                if(!gradeList.contains(grade)) {
                    gradeList.add(grade);
                } else if (isCourseGradeDifferent(gradeList, grade)) {
                    gradeList.remove(grade);
                    gradeList.add(grade);
                }
            }else{
                gradeList = new ArrayList<>();
                gradeList.add(grade);
                gradesMap.put(grade.getStudentId(), gradeList);
            }
        }
    }

    private boolean isCourseGradeDifferent(List<Grade> gradeList, Grade grade) {
        return !gradeList.get(gradeList.indexOf(grade)).getBand().equalsIgnoreCase(grade.getBand());
    }

    public List<Grade> getGradesForStudent(long studentId) {
        return gradesMap.get(studentId);
    }

    public void deleteGradesForStudent(long studentId) {
        gradesMap.remove(studentId);
    }

    public void deleteGradesForCourse(long courseId, List<Long> studentIds){
        for(Long studentId: studentIds){
            List<Grade> grades = gradesMap.get(studentId);
            grades.remove(new Grade(studentId, courseId, null));
        }
    }
}
