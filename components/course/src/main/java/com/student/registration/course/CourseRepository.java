package com.student.registration.course;

import com.student.registration.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class CourseRepository {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public CourseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        jdbcTemplate.execute("DROP TABLE courses IF EXISTS;");
        jdbcTemplate.execute("CREATE TABLE courses (" +
                "id SERIAL," +
                "courseName VARCHAR (255));");
    }

    public CourseRepository() {}

    public List<Course> getAllCourses() {
        return jdbcTemplate.query("SELECT * FROM courses ",
                new CourseRowMapper());
    }

    public Integer addCourse(Course course) {
        return jdbcTemplate.update("INSERT INTO courses " +
                        "(courseName) " +
                        "VALUES (?)",
                new Object[] {course.getCourseName()});
    }

    public Course getCourseById(Long id) {
        return jdbcTemplate.query("SELECT * FROM courses " +
                        "WHERE " +
                        "id = ?",
                new Object[] {id},
                new CourseRowMapper())
                .get(0);
    }

    public Integer editCourse(Course course) {
        return jdbcTemplate.update("UPDATE courses SET " +
                        "courseName = ?, " +
                        "WHERE " +
                        "id = ?",
                new Object[] { course.getCourseName(), course.getId() });
    }

    public Integer deleteCourse(Long id) {
        return jdbcTemplate.update("DELETE FROM courses " +
                        "WHERE " +
                        "id = ?",
                id);
    }

    private class CourseRowMapper implements RowMapper<Course> {

        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();
            course.setId(rs.getLong("id"));
            course.setCourseName(rs.getString("courseName"));
            return course;
        }
    }

}
