package com.student.registration.main;

import com.student.registration.StudentRegistrationService;
import com.student.registration.domain.Grade;
import com.student.registration.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class StudentController {

    private StudentClient studentClient;
    private CourseClient courseClient;
    private StudentRegistrationService studentRegistrationService;
    private final String cfInstanceIndex;
    private final String cfInstanceAddress;

    public StudentController (StudentClient studentClient,
                              CourseClient courseClient,
                              StudentRegistrationService studentRegistrationService,
                              @Value("${CF_INSTANCE_INDEX:NOT SET}") String cfInstanceIndex,
                              @Value("${CF_INSTANCE_ADDR:NOT SET}") String cfInstanceAddress) {
        this.studentClient = studentClient;
        this.courseClient = courseClient;
        this.studentRegistrationService = studentRegistrationService;
        this.cfInstanceIndex = cfInstanceIndex;
        this.cfInstanceAddress = cfInstanceAddress;
    }

    @GetMapping("/allStudents")
    public String getAllStudents(ModelMap modelMap) {
        modelMap.put("students", studentClient.getAllStudents());
        modelMap.put("cfInstanceIndex", cfInstanceIndex);
        modelMap.put("cfInstanceAddress", cfInstanceAddress);
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
        return "deleteSuccess";
    }

    @GetMapping("/student/{id}")
    public String getStudentOverview(ModelMap modelMap, @PathVariable long id) {
        Set<Long> courses = studentRegistrationService.getCoursesForStudent(id);
        modelMap.put("student", studentClient.getStudentById(id));
        modelMap.put("courses", courseClient.getCourses(courses));
        return "student";
    }

    @GetMapping("/enrollCourse")
    public String enrollStudent(ModelMap modelMap,
                                @RequestParam("courseId") long courseId,
                                @RequestParam("studentId") long studentId) {
        studentRegistrationService.addStudentCourseRelationShip(studentId, courseId);
        Set<Long> courses = studentRegistrationService.getCoursesForStudent(studentId);
        modelMap.put("student", studentClient.getStudentById(studentId));
        modelMap.put("courses", courseClient.getCourses(courses));
        return "student";
    }
}
