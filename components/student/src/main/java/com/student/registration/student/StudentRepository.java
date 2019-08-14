package com.student.registration.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class StudentRepository {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        jdbcTemplate.execute("DROP TABLE students IF EXISTS;");
        jdbcTemplate.execute("CREATE TABLE students (" +
                "id SERIAL," +
                "firstName VARCHAR(255)," +
                "lastName VARCHAR (255));");
    }

    public StudentRepository() {}

    public List<Student> getAllStudents() {
        return jdbcTemplate.query("SELECT * FROM students ",
                new StudentRowMapper());
    }

    public Integer addStudent(Student student) {
        return jdbcTemplate.update("INSERT INTO students " +
                "(firstName, " +
                "lastName) " +
                "VALUES (?,?)",
                new Object[] {student.getFirstName(), student.getLastName()});
    }

    public Student getStudentById(Long id) {
        return jdbcTemplate.query("SELECT * FROM students " +
                        "WHERE " +
                        "id = ?",
                new Object[] {id},
                new StudentRowMapper())
                .get(0);
    }

    public Integer editStudent(Student student) {
        return jdbcTemplate.update("UPDATE students SET " +
                        "firstName = ?, " +
                        "lastName = ? " +
                        "WHERE " +
                        "id = ?",
                new Object[] { student.getFirstName(), student.getLastName(), student.getId() });
    }

    public Integer deleteStudent(Long id) {
        return jdbcTemplate.update("DELETE FROM students " +
                        "WHERE " +
                        "id = ?",
                id);
    }

    private class StudentRowMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setId(rs.getLong("id"));
            student.setFirstName(rs.getString("firstName"));
            student.setLastName(rs.getString("lastName"));
            return student;
        }
    }

}
