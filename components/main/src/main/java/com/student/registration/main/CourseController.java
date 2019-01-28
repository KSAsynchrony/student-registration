package com.student.registration.main;

import com.student.registration.StudentRegistrationController;
import com.student.registration.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
public class CourseController {

    CourseClient courseClient;
    StudentClient studentClient;

    StudentRegistrationController studentRegistrationController;

    @Autowired
    public CourseController(CourseClient courseClient,
                            StudentClient studentClient,
                            StudentRegistrationController studentRegistrationController) {
        this.courseClient = courseClient;
        this.studentClient = studentClient;
        this.studentRegistrationController = studentRegistrationController;
    }

    @GetMapping("/allCourses")
    public String getAllStudents(ModelMap modelMap) {
        modelMap.put("courses", courseClient.getAllCourses());
        return "allCourses";
    }

    @GetMapping("/addCourse")
    public String addCourse(ModelMap modelMap) {
        modelMap.put("course", new Course());
        return "createCourse";
    }

    @PostMapping("/addCourse")
    public String addCourse(ModelMap modelMap, Course course) {
        courseClient.createCourse(course);
        modelMap.put("courses", courseClient.getAllCourses());
        return "allCourses";
    }

    @GetMapping("/editCourse/{id}")
    public String editStudent(ModelMap modelMap, @PathVariable long id) {
        modelMap.put("course", courseClient.getCourseById(id));
        return "editCourse";
    }

    @GetMapping("/course/{id}")
    public String viewCourse(ModelMap modelMap, @PathVariable long id) {
        Set<Long> students = studentRegistrationController.getStudentsForCourse(id);
        modelMap.put("course", courseClient.getCourseById(id));
        modelMap.put("students", studentClient.getStudents(students));
        return "course";
    }

    @PostMapping("/editCourse")
    public String editStudent(ModelMap modelMap, Course course) {
        courseClient.editCourse(course);
        modelMap.put("courses", courseClient.getAllCourses());
        return "allCourses";
    }

    @GetMapping("/deleteCourse/{id}")
    public String deleteStudent(ModelMap modelMap, @PathVariable long id) {
        courseClient.deleteCourse(id);
        modelMap.put("id", id);
        return "deleteSuccess";
    }

    @GetMapping("/enrollStudent")
    public String enrollStudent(ModelMap modelMap,
                                @RequestParam("courseId") long courseId,
                                @RequestParam("studentId") long studentId) {
        studentRegistrationController.addStudentCourseRelationShip(studentId, courseId);
        Set<Long> students = studentRegistrationController.getStudentsForCourse(courseId);
        modelMap.put("course", courseClient.getCourseById(courseId));
        modelMap.put("students", studentClient.getStudents(students));
        return "course";
    }

}
