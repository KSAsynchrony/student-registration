package com.student.registration.student;

import com.student.registration.domain.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class StudentController {

    StudentSqlRepository studentRepository;
    public StudentController(StudentSqlRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping(value = "/allStudents")
    public ResponseEntity<Set<Student>> getAllStudents() {
        return new ResponseEntity(studentRepository.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/getStudentById/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return new ResponseEntity(studentRepository.get(id), HttpStatus.OK);
    }

    @PostMapping(value = "/getStudentsForIds")
    public ResponseEntity<Set<Student>> getStudents(@RequestBody Set<Long> ids) {
        return new ResponseEntity(studentRepository.getAll(ids), HttpStatus.OK);
    }

    @PostMapping(value = "/createStudent")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return new ResponseEntity(studentRepository.addStudent(student), HttpStatus.OK);
    }

    @PostMapping(value = "/editStudent")
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        return new ResponseEntity(studentRepository.edit(student.getId(), student), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteStudent/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable long id) {
        return new ResponseEntity(studentRepository.remove(id), HttpStatus.OK);
    }
}
