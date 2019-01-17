package com.student.registration.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    StudentClient getStudentClient() {
        return new StudentClient();
    }

    @Bean
    CourseClient getCourseClient() {
        return new CourseClient();
    }

}