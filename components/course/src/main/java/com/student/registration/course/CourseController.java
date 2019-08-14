package com.student.registration.course;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class CourseController {

    private CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @GetMapping("/allCourses")
    @ResponseBody
    public Collection<Course> getAllCourses() {
        return courseRepository.getAllCourses();
    }

    @GetMapping("/lookupCourse/{id}")
    @ResponseBody
    public Course lookupCourse(@PathVariable long id) throws Exception {
        Course course = courseRepository.getCourseById(id);
        if(course != null){
            return course;
        }else{
            throw new Exception("Course not found");
        }
    }

    @PostMapping("/createCourse")
    @ResponseBody
    public Course createCourse(@RequestBody Course course){
        courseRepository.addCourse(course);
        return course;
    }

    @PostMapping("/editCourse")
    @ResponseBody
    public Course editCourse(@RequestBody Course course){
        courseRepository.editCourse(course);
        return course;
    }

    @DeleteMapping("/deleteCourse/{id}")
    @ResponseBody
    public Course deleteCourse(@PathVariable long id) throws Exception {
        try {
            courseRepository.deleteCourse(id);
            return new Course();
        }catch (Exception e){
            throw e;
        }
    }
}
