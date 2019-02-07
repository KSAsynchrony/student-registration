package com.student.registration.main;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.student.registration.domain.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestOperations;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CourseClient {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final RestOperations restOperations;

    private final String courseApiUrl;

    private final Map<Long, Course> courseCache = new HashMap<>();

    public CourseClient(RestOperations restOperations, String courseApiUrl) {
        this.restOperations = restOperations;
        this.courseApiUrl = courseApiUrl;
    }

    @HystrixCommand(fallbackMethod = "getCourseFromCache")
    public Course getCourseById(Long id) {
        Course course = restOperations.getForEntity(courseApiUrl + "/lookupCourse/" + id, Course.class).getBody();
        return course;
    }

    public Course getCourseFromCache(Long id){
        logger.info("Getting course with id {} from cache", id);
        return courseCache.get(id);
    }

    @HystrixCommand(fallbackMethod = "getAllCoursesFromCache")
    public Set<Course> getAllCourses() {
        return restOperations.getForEntity(courseApiUrl + "/allCourses", Set.class).getBody();
    }

    public Set<Course> getAllCoursesFromCache(){
        Set<Course> courses = new HashSet<>();
        courses.addAll(courseCache.values());
        return courses;
    }

    public Course createCourse(Course course) {
        Course newCourse = restOperations.postForEntity(courseApiUrl + "/createCourse", course, Course.class).getBody();
        if(newCourse != null){
            courseCache.put(newCourse.getId(), newCourse);
        }
        return newCourse;
    }

    public void deleteCourse(Long id) {
        restOperations.delete(courseApiUrl + "/deleteCourse/" + id);
    }

    public void editCourse(Course course) {
        restOperations.postForEntity(courseApiUrl + "/editCourse", course, Course.class);
    }

    @HystrixCommand(fallbackMethod = "getCoursesFromCache")
    public Set<Course> getCourses(Set<Long> ids) {
        return restOperations.postForEntity(courseApiUrl + "/getCoursesForIds", ids, Set.class).getBody();
    }

    public Set<Course> getCoursesFromCache(Set<Long> ids){
        Set<Course> courses = new HashSet<>();
        for(Long id: ids){
            courses.add(courseCache.get(id));
        }
        return courses;
    }
}
