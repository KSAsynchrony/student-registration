package com.student.registration.main;

import com.student.registration.domain.Course;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

public class CourseClient {
    @Value("${course.api.url}")
    String courseApi;

    RestTemplate restTemplate = new RestTemplate();

    public CourseClient() {}

    public Course getCourseById(Long id) {
        return restTemplate.getForEntity(courseApi + "/lookupCourse/" + id, Course.class).getBody();
    }

    public Set<Course> getAllCourses() {
        return restTemplate.getForEntity(courseApi + "/allCourses", Set.class).getBody();
    }

    public Course createCourse(Course course) {
        return restTemplate.postForEntity(courseApi + "/createCourse", course, Course.class).getBody();
    }

    public void deleteCourse(Long id) {
        restTemplate.delete(courseApi + "/deleteCourse/" + id);
    }

    public void editCourse(Course course) {
        restTemplate.postForEntity(courseApi + "/editCourse", course, Course.class);
    }

    public Set<Course> getCourses(Set<Long> ids) {
        return restTemplate.postForEntity(courseApi + "/getCoursesForIds", ids, Set.class).getBody();
    }
}
