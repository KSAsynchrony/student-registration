package com.student.registration.main;

import com.student.registration.domain.Grade;
import org.springframework.web.client.RestOperations;

import java.util.List;

public class GradeClient {

    private final String gradesApiUrl;

    private final RestOperations restOperations;

    public GradeClient(RestOperations restOperations, String gradesApiUrl) {
        this.restOperations = restOperations;
        this.gradesApiUrl = gradesApiUrl;
    }

    public List<Grade> getGradesForStudent(long studentId){
        return this.restOperations.getForEntity(this.gradesApiUrl +"/grades/"+studentId, List.class).getBody();
    }


    public List<Grade> updateGradesForStudent(List<Grade> grades){
        return this.restOperations.postForEntity(this.gradesApiUrl +"/grades", grades, List.class).getBody();
    }

}
