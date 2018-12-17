package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller()
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping(value = "/createStudent")
    public String greeting() {
        return "createStudent";
    }

    @RequestMapping(value = "/createStudent", method = RequestMethod.POST)
    public String greeting(@Valid @ModelAttribute("student") Student student,
                           ModelMap model) {
        model.addAttribute("id", studentRepository.addStudent(student.getFirstName(), student.getLastName()));
        model.addAttribute("firstName", student.getFirstName());
        model.addAttribute("lastName", student.getLastName());
        return "studentCreateSuccess";
    }

    @RequestMapping(value = "/getStudent")
    public String getStudent(@RequestParam(name = "id", required = true, defaultValue = "0") String id,
                             Model model) {
        Student returnedStudent = studentRepository.get(Long.parseLong(id));

        model.addAttribute("id", id);
        model.addAttribute("firstName", returnedStudent.getFirstName());
        model.addAttribute("lastName", returnedStudent.getLastName());
        return "studentLookup";
    }

    @RequestMapping(value = "/allStudents")
    public String getStudent(Model model) {
        Collection<Student> students = studentRepository.values();
        model.addAttribute("students", students);
        return "allStudents";
    }
}
