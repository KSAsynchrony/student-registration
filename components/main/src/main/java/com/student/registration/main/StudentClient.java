package com.student.registration.main;

import com.student.registration.domain.Student;
import org.springframework.web.client.RestOperations;

import java.util.Set;

public class StudentClient {
    private final RestOperations restOperations;

    private final String studentApiUrl;

    public StudentClient(RestOperations restOperations, String studentApiUrl){
        this.restOperations = restOperations;
        this.studentApiUrl = studentApiUrl;
    }

    public Student getStudentById(Long id) {
        return restOperations.getForEntity(studentApiUrl + "/getStudentById/" + id, Student.class).getBody();
    }

    public Set<Student> getStudents(Set<Long> ids) {
        return restOperations.postForEntity(studentApiUrl + "/getStudentsForIds", ids, Set.class).getBody();
    }

    public Set<Student> getAllStudents() {
        return restOperations.getForEntity(studentApiUrl + "/allStudents", Set.class).getBody();
    }

    public Student createStudent(Student student) {
        return restOperations.postForEntity(studentApiUrl + "/createStudent", student, Student.class).getBody();
    }

    public void deleteStudent(Long id) {
        restOperations.delete(studentApiUrl + "/deleteStudent/" + id);
    }

    public void editStudent(Student student) {
        restOperations.postForEntity(studentApiUrl + "/editStudent", student, Student.class);
    }
}
