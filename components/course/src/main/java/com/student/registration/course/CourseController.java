package com.student.registration.course;

import com.student.registration.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CourseController {

    private CourseRepository courseRepository;

    private StudentRepository studentRepository;

    public CourseController(CourseRepository courseRepository, StudentRepository studentRepository){
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/createCourse")
    public String getMapping(){
        return "createCourse";
    }

    @PostMapping("/createCourse")
    public String createCourse(@ModelAttribute("com/student/registration/course") Course course, ModelMap modelMap){
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
    public String getStudentsForCourse(@RequestParam long courseId, ModelMap modelMap) throws  Exception{
        List<Long> studentIds = courseRepository.getStudentIdsForCourse(courseId);
        if(studentIds != null){
            modelMap.addAttribute("students", studentRepository.getAll(studentIds));
        }else{
            throw new Exception("Course not found");
        }

      return "allStudents";
    }

    @GetMapping("/allCourses")
    public String getAllCourses(ModelMap modelMap) {
        modelMap.addAttribute("courses", courseRepository.getAllCourses());
        return "allCourses";
    }
}
