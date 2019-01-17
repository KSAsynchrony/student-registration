package com.student.registration.main;

import com.student.registration.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller("/students")
public class StudentController {

    StudentClient studentClient;

    @Autowired
    public StudentController (StudentClient studentClient) {
        this.studentClient = studentClient;
    }

    @GetMapping("/allStudents")
    public String getAllStudents(ModelMap modelMap) {
        modelMap.put("students", studentClient.getAllStudents());
        return "allStudents";
    }

    @GetMapping("/addStudent")
    public String getAddStudent(ModelMap modelMap) {
        modelMap.put("student", new Student());
        return "createStudent";
    }

    @PostMapping("/addStudent")
    public String addStudentAction(ModelMap modelMap, Student student) {
        studentClient.createStudent(student);
        modelMap.put("students", studentClient.getAllStudents());
        return "allStudents";
    }

    @GetMapping("/editStudent/{id}")
    public String editStudent(ModelMap modelMap, @PathVariable long id) {
        modelMap.put("student", studentClient.getStudentById(id));
        return "editStudent";
    }

    @PostMapping("/editStudent")
    public String editStudent(ModelMap modelMap, Student student) {
        studentClient.editStudent(student);
        modelMap.put("students", studentClient.getAllStudents());
        return "allStudents";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(ModelMap modelMap, @PathVariable long id) {
        studentClient.deleteStudent(id);
        modelMap.put("id", id);
        return "deleteStudentSuccess";
    }
}
