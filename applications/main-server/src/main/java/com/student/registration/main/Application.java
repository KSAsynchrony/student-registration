package com.student.registration.main;

import com.student.registration.StudentRegistrationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @LoadBalanced
    public RestOperations restOperations() {
        return new RestTemplate();
    }

    @Bean
    StudentClient getStudentClient(RestOperations restOperations, @Value("${student.api.url}") String studentApiUrl) {
        return new StudentClient(restOperations, studentApiUrl);
    }

    @Bean
    CourseClient getCourseClient(RestOperations restOperations, @Value("${course.api.url}")String courseApiUrl) {
        return new CourseClient(restOperations, courseApiUrl);
    }

    @Bean
    GradeClient getGradesClient(RestOperations restOperations, @Value("${grades.api.url}")String gradesApiUrl) {
        return new GradeClient(restOperations, gradesApiUrl) ; }

}

