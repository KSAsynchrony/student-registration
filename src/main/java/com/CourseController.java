package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/createCourse")
    public String getMapping(){
        return "createCourse";
    }

    @PostMapping("/createCourse")
    public String createCourse(@ModelAttribute("course") Course course, ModelMap modelMap){
        Course newCourse = courseRepository.createCourse(course);
        modelMap.addAttribute("courseId", newCourse.getCourseId());
        modelMap.addAttribute("courseName", newCourse.getCourseName());
        return "courseSuccess";
    }

    @GetMapping("/lookupCourse")
    public String findCourse(@RequestParam long courseId, ModelMap modelMap) throws Exception {
        Course course = courseRepository.findCourse(courseId);
        if(course != null){
            modelMap.addAttribute("courseId", course.getCourseId());
            modelMap.addAttribute("courseName", course.getCourseName());
        }else{
            throw new Exception("Course not found");
        }
        return "courseLookup";
    }

    @GetMapping("/getStudents")
    public String getStudentIdsForCourse(@RequestParam long courseId, ModelMap modelMap) throws  Exception{
        List<Long> studentIds = courseRepository.getStudentIdsForCourse(courseId);
        List<Student> students = new ArrayList<>();
        if(studentIds != null){
            for(Long studentId : studentIds){
                Student student = studentRepository.get(studentId);
                students.add(student);
            }
            modelMap.addAttribute("students", students);
        }else{
            throw new Exception("Course not found");
        }

      return "allStudents";
    }

    @GetMapping("/allCourses")
    public String getAllCourses(Model model) {
        model.addAttribute("courses", courseRepository.getAllCourses());
        return "allCourses";
    }
}
