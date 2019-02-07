package com.student.registration.main;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.student.registration.domain.Grade;
import com.student.registration.domain.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

import java.util.*;

public class StudentClient {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final RestOperations restOperations;

    private final String studentApiUrl;

    private final Map<Long, Student> studentCache = new HashMap<>();

    public StudentClient(RestOperations restOperations, String studentApiUrl) {
        this.restOperations = restOperations;
        this.studentApiUrl = studentApiUrl;
    }

    @HystrixCommand(fallbackMethod = "getStudentFromCache")
    public Student getStudentById(Long id) {
        Student student = restOperations.getForEntity(studentApiUrl + "/getStudentById/" + id, Student.class).getBody();
        return student;
    }

    public Student getStudentFromCache(Long id) {
        logger.info("Hitting getStudentFromCache()");
        return studentCache.get(id);
    }

    @HystrixCommand(fallbackMethod = "getStudentsFromCache")
    public Set<Student> getStudents(Set<Long> ids) {
        return restOperations.postForEntity(studentApiUrl + "/getStudentsForIds", ids, Set.class).getBody();
    }

    public Set<Student> getStudentsFromCache(Set<Long> ids) {
        logger.info("Hitting getStudentsFromCache()");
        Set<Student> students = new HashSet<>();
        for (Long id : ids) {
            students.add(studentCache.get(id));
        }
        return students;
    }

    @HystrixCommand(fallbackMethod = "getAllStudentsFromCache")
    public Set<Student> getAllStudents() {
        Set<Student> allStudents = restOperations.exchange(studentApiUrl + "/allStudents", HttpMethod.GET, null, new ParameterizedTypeReference<Set<Student>>() {}).getBody();
        updateCache(allStudents);
        return allStudents;
    }

    private void updateCache(Set<Student> allStudents) {
        studentCache.clear();
        Iterator<Student> studentIterator = allStudents.iterator();

        while(studentIterator.hasNext()){
            Student student = studentIterator.next();
            logger.info("going to add this student {} ", student);
            studentCache.put(student.getId(), student);
        }
    }

    public Set<Student> getAllStudentsFromCache() {
        logger.info("Hitting getAllStudentsFromCache()");
        Set<Student> students = new HashSet<>();
        students.addAll(studentCache.values());
        return students;
    }

    public Student createStudent(Student student) {
        Student newStudent = restOperations.postForEntity(studentApiUrl + "/createStudent", student, Student.class).getBody();
        if (newStudent != null) {
            studentCache.put(newStudent.getId(), newStudent);
        }
        return newStudent;
    }

    public void deleteStudent(Long id) {
        restOperations.delete(studentApiUrl + "/deleteStudent/" + id);
    }

    public void editStudent(Student student) {
        restOperations.postForEntity(studentApiUrl + "/editStudent", student, Student.class);
    }
}
