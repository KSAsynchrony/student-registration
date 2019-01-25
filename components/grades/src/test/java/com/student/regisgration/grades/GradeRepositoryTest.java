package com.student.regisgration.grades;

import com.student.registration.domain.Grade;
import com.student.registration.grades.GradeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GradeRepositoryTest {

    GradeRepository gradeRepository;

    List<Grade> grades;

    @Before
    public void setUp(){
        gradeRepository = new GradeRepository();
        grades = new ArrayList<>();
        Grade grade1 = new Grade(1,1, "A");
        Grade grade2 = new Grade(2,2, "B");
        grades.add(grade1);
        grades.add(grade2);
    }

   /* @Test
    public void testAddGradesForStudentWhenGradesListEmpty() throws Exception{
        grades = new ArrayList<>();
        List<Grade> result  = gradeRepository.addGradesForStudent(grades);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testAddGradesForStudentWhenGradesListNotEmpty() throws Exception{
        List<Grade> result  = gradeRepository.addGradesForStudent(1, grades);
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(grades, result);
    }

    @Test
    public void testGetGradesForStudentWhenStudentIdNotFound() throws  Exception{
        List<Grade> result = gradeRepository.getGradesForStudent(1);
        Assert.assertNull(result);
    }

    @Test
    public void testGetGradesForStudentWhenStudentIdIsFound() throws  Exception{
        gradeRepository.addGradesForStudent(1, grades);
        List<Grade> result = gradeRepository.getGradesForStudent(1);
        Assert.assertNotNull(result);
    }*/
}
