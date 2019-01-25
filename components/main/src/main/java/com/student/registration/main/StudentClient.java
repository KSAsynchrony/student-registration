package com.student.registration.main;

import com.student.registration.domain.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

public class StudentClient {
    @Value("${student.api.url}")
    String studentApi;

    RestTemplate restTemplate = new RestTemplate();

    public StudentClient() {}

    public Student getStudentById(Long id) {
        return restTemplate.getForEntity(studentApi + "/getStudentById/" + id, Student.class).getBody();
    }

    public Set<Student> getAllStudents() {
        return restTemplate.getForEntity(studentApi + "/allStudents", Set.class).getBody();
    }

    public Student createStudent(Student student) {
        return restTemplate.postForEntity(studentApi + "/createStudent", student, Student.class).getBody();
    }

    public void deleteStudent(Long id) {
        restTemplate.delete(studentApi + "/deleteStudent/" + id);
    }

    public void editStudent(Student student) {
        restTemplate.postForEntity(studentApi + "/editStudent", student, Student.class);
    }
}
