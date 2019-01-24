package com.student.registration.main;

import com.student.registration.student.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class StudentClient {
    @Value("${student.api.url}")
    String studentApi;

    RestTemplate restTemplate = new RestTemplate();

    public StudentClient() {}

    public Student getStudentById(Long id) {
        return restTemplate.getForEntity(studentApi + "/getStudentById/" + id, Student.class).getBody();
    }

    public List<Student> getAllStudents() {
        return restTemplate.getForEntity(studentApi + "/allStudents", List.class).getBody();
    }

    public Integer createStudent(Student student) {
        return restTemplate.postForEntity(studentApi + "/createStudent", student, Integer.class).getBody();
    }

    public Integer editStudent(Student student) {
        return restTemplate.postForEntity(studentApi + "/editStudent", student, Integer.class).getBody();
    }

    public void deleteStudent(Long id) {
        restTemplate.delete(studentApi + "/deleteStudent/" + id);
    }
}
