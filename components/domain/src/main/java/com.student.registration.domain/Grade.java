package com.student.registration.domain;

import java.util.Objects;

public class Grade {

    private long studentId;
    private long courseId;
    private String band;

    public Grade() {}

    public Grade(long studentId, long courseId, String band) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.band = band;
    }

    public long getStudentId() {
        return studentId;
    }

    public long getCourseId() {
        return courseId;
    }

    public String getBand() {
        return band;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return studentId == grade.studentId &&
                courseId == grade.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}
