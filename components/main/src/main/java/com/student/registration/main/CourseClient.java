package com.student.registration.main;

import com.student.registration.domain.Course;
import org.springframework.web.client.RestOperations;

import java.util.Set;

public class CourseClient {
    private final RestOperations restOperations;

    private final String courseApiUrl;

    public CourseClient(RestOperations restOperations, String courseApiUrl) {
        this.restOperations = restOperations;
        this.courseApiUrl = courseApiUrl;
    }

    public Course getCourseById(Long id) {
        return restOperations.getForEntity(courseApiUrl + "/lookupCourse/" + id, Course.class).getBody();
    }

    public Set<Course> getAllCourses() {
        return restOperations.getForEntity(courseApiUrl + "/allCourses", Set.class).getBody();
    }

    public Course createCourse(Course course) {
        return restOperations.postForEntity(courseApiUrl + "/createCourse", course, Course.class).getBody();
    }

    public void deleteCourse(Long id) {
        restOperations.delete(courseApiUrl + "/deleteCourse/" + id);
    }

    public void editCourse(Course course) {
        restOperations.postForEntity(courseApiUrl + "/editCourse", course, Course.class);
    }

    public Set<Course> getCourses(Set<Long> ids) {
        return restOperations.postForEntity(courseApiUrl + "/getCoursesForIds", ids, Set.class).getBody();
    }
}
