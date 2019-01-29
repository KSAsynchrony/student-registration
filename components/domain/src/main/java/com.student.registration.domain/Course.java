package com.student.registration.domain;

import java.util.Objects;

public class Course {

    private Long id;
    private String courseName;

    public Course(){

    }

    public Course(Long id, String courseName){
        this.id = id;
        this.courseName = courseName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id.equals(course.id) &&
                courseName.equals(course.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName);
    }
}