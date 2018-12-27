package com.student.registration.repository;

import java.util.Objects;

public class StudentRegistration {
    private String firstName;
    private String lastName;
    private String courses;

    public StudentRegistration(){

    }

    public StudentRegistration(String firstName, String lastName, String courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.courses = courses;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentRegistration that = (StudentRegistration) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(courses, that.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, courses);
    }
}
