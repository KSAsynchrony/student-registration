package com.student.registration.course;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Controller
public class CourseController {

    @Value("${student.api.url")
    private String studentApi;

    RestTemplate restTemplate = new RestTemplate();

    private CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @GetMapping("/createCourse")
    public String getMapping(){
        return "insertCourse";
    }

    @PostMapping("/createCourse")
    public String createCourse(@ModelAttribute("com/student/registration/course") Course course, ModelMap modelMap){
        Course newCourse = courseRepository.insertCourse(course);
        modelMap.addAttribute("courseId", newCourse.getCourseId());
        modelMap.addAttribute("courseName", newCourse.getCourseName());
        return "courseSuccess";
    }

    @GetMapping("/lookupCourse")
    public String lookupCourse(@RequestParam long courseId, ModelMap modelMap) throws Exception {
        Course course = courseRepository.findCourse(courseId);
        if(course != null){
            modelMap.addAttribute("courseId", course.getCourseId());
            modelMap.addAttribute("courseName", course.getCourseName());
        }else{
            throw new Exception("Course not found");
        }
        return "courseLookup";
    }

    @GetMapping("/allCourses")
    public String getAllCourses(ModelMap modelMap) {
        modelMap.addAttribute("courses", courseRepository.getAllCourses());
        return "allCourses";
    }

    @GetMapping(value = "/allCourseIDs")
    public ResponseEntity<Set<Long>> getAllStudentIds() {
        return new ResponseEntity(courseRepository.getAllCourseIDs(), HttpStatus.OK);
    }

    @GetMapping(value = "/getCourseObject")
    public ResponseEntity<Course> getStudentById(Long id) {
        return new ResponseEntity(courseRepository.findCourse(id), HttpStatus.OK);
    }
}
