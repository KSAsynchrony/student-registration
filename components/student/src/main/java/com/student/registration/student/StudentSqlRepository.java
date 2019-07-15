package com.student.registration.student;

import com.student.registration.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class StudentSqlRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    StudentSqlRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        jdbcTemplate.execute("DROP TABLE students IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE students(" +
                "id SERIAL, firstName VARCHAR(255), lastName VARCHAR(255))");

        addStudent(new Student(0l, "Kerim", "Strikovic"));
        addStudent(new Student(0l, "Arun", "Kumar"));
        addStudent(new Student(0l, "Dave", "Blatt"));
    }

    public Set<Student> getAll() {
        return toSet(jdbcTemplate.query("SELECT id, firstName, lastName FROM students", studentRowMapper));
    }

    public Student get(Long id) {
        return (Student) jdbcTemplate.query("SELECT id, firstName, lastName FROM students WHERE id = ?",
                new Object[] {id},
                studentRowMapper)
                .stream().findAny().get();
    }

    public Set<Student> getAll(Set<Long> ids) {
        Set<Student> foundStudents = new HashSet<>();
        for (Long id : ids) {
            foundStudents.add(get(id));
        }

        return foundStudents;
    }

    public Student addStudent(Student student) {
        jdbcTemplate.update("INSERT INTO students (firstName, lastName) VALUES (?,?)",
                student.getFirstName(),
                student.getLastName());
        return student;
    }

    public Student edit(Long id, Student student) {
        jdbcTemplate.update("UPDATE students SET firstName = ?, lastName = ? WHERE id = ?;",
                student.getFirstName(),
                student.getLastName(),
                id);
        return student;
    }

    public Student remove(long id) {
        Student deletedStudent = get(id);
        jdbcTemplate.update("DELETE FROM students WHERE id = ?", new Object[] {id});
        return deletedStudent;
    }

    private RowMapper studentRowMapper = new RowMapper<Student>() {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Student(rs.getLong("id"),
                    rs.getString("firstName"),
                    rs.getString("lastName"));
        }
    };

    private static Set<Student> toSet(List<Student> studentList) {
        Set<Student> students = new HashSet<>();
        for (Student student : studentList) {
            students.add(student);
        }
        return students;
    }
}


