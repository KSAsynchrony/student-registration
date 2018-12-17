package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/createCourse")
    public String getMapping(){
        return "createCourse";
    }

    @PostMapping("/createcourse")
    public String createCourse(@ModelAttribute("course") Course course, ModelMap modelMap){
        Course newCourse = courseService.createCourse(course);
        modelMap.addAttribute("courseId", newCourse.getCourseId());
        modelMap.addAttribute("courseName", newCourse.getCourseName());
        return "courseSuccess";
    }

    @GetMapping("/lookupcourse")
    public String findCourse(@RequestParam long courseId, ModelMap modelMap) throws Exception {
        Course course = courseService.findCourse(courseId);
        if(course != null){
            modelMap.addAttribute("courseId", course.getCourseId());
            modelMap.addAttribute("courseName", course.getCourseName());
        }else{
            throw new Exception("Course not found");
        }
        return "courseLookup";
    }

    @GetMapping("/getstudents")
    public String getStudentIdsForCourse(@RequestParam long courseId, ModelMap modelMap) throws  Exception{
        List<Long> studentIds = courseService.getStudentIdsForCourse(courseId);
        if(studentIds != null){
            modelMap.addAttribute("studentIdList", studentIds);
        }else{
            throw new Exception("Course not found");
        }

      return "getStudentsSuccess";
    }
}
