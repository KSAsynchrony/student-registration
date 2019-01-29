package com.student.registration.main;

import com.student.registration.StudentRegistrationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    StudentRegistrationService getRegistrationController() {
        return new StudentRegistrationService();
    }

    @Bean
    StudentClient getStudentClient() {
        return new StudentClient();
    }

    @Bean
    CourseClient getCourseClient() {
        return new CourseClient();
    }

    @Bean
    GradeClient getGradesClient() { return new GradeClient() ; }
}