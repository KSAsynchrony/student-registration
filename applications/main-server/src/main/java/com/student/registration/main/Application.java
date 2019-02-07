package com.student.registration.main;

import com.student.registration.StudentRegistrationRepository;
import com.student.registration.StudentRegistrationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class}
)
@EnableEurekaClient
@EnableCircuitBreaker
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    StudentRegistrationService getRegistrationController() {
        return new StudentRegistrationService(new StudentRegistrationRepository());
    }

    @Bean
    @LoadBalanced
    public RestOperations restOperations() {
        return new RestTemplate();
    }

    @Bean
    @RefreshScope
    StudentClient getStudentClient(RestOperations restOperations, @Value("${student.api.url}") String studentApiUrl) {
        return new StudentClient(restOperations, studentApiUrl);
    }

    @Bean
    @RefreshScope
    CourseClient getCourseClient(RestOperations restOperations, @Value("${course.api.url}")String courseApiUrl) {
        return new CourseClient(restOperations, courseApiUrl);
    }

    @Bean
    @RefreshScope
    ConfigVariables getConfigVariables(@Value("${display.variable}")String displayVariable) {
        return new ConfigVariables(displayVariable);
    }

    @Bean
    @RefreshScope
    GradeClient getGradesClient(RestOperations restOperations, @Value("${grades.api.url}")String gradesApiUrl) {
        return new GradeClient(restOperations, gradesApiUrl) ; }
}

