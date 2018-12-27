package com.student.registration.student;

import com.student.registration.repository.Student;
import com.student.registration.repository.StudentRegistration;
import com.student.registration.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller()
public class StudentController {

    StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping(value = "/createStudent")
    public String createStudent() {
        return "createStudent";
    }

    @RequestMapping(value = "/createStudent", method = RequestMethod.POST)
    public String createStudent(@ModelAttribute("com/student/registration/student") StudentRegistration studentRegistration,
                                ModelMap model) {
        model.addAttribute("id", studentRepository.addStudent(studentRegistration));
        model.addAttribute("firstName", studentRegistration.getFirstName());
        model.addAttribute("lastName", studentRegistration.getLastName());
        return "studentCreateSuccess";
    }

    @RequestMapping(value = "/getStudent")
    public String getStudentById(@RequestParam(name = "id", required = true, defaultValue = "0") Long id,
                                 ModelMap model) {
        Student returnedStudent = studentRepository.get(id);

        model.addAttribute("id", id);
        model.addAttribute("firstName", returnedStudent.getFirstName());
        model.addAttribute("lastName", returnedStudent.getLastName());
        return "studentLookup";
    }

    @RequestMapping(value = "/allStudents")
    public String getAllStudents(ModelMap model) {
        Collection<Student> students = studentRepository.values();
        model.addAttribute("students", students);
        return "allStudents";
    }
}
