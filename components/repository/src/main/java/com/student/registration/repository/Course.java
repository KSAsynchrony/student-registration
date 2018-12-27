package com.student.registration.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {

    private Long courseId;
    private String courseName;
    private List<Long> studentIDs = new ArrayList<>();

    public Course(Long courseId, String courseName){
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Long> getStudentIDs() {
        return studentIDs;
    }

    public void setStudentIDs(List<Long> studentIDs) {
        this.studentIDs = studentIDs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return courseId.equals(course.courseId) &&
                courseName.equals(course.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName);
    }
}
