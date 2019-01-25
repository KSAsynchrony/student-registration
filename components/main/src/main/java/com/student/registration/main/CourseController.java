package com.student.registration.main;

import com.student.registration.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CourseController {

    CourseClient courseClient;

    @Autowired
    public CourseController(CourseClient courseClient) {
        this.courseClient = courseClient;
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
}
